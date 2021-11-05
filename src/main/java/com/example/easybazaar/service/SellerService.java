package com.example.easybazaar.service;

import com.example.easybazaar.dto.*;
import com.example.easybazaar.dto.search.SearchDto;
import com.example.easybazaar.encryption.AES;
import com.example.easybazaar.enums.UserType;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.*;
import com.example.easybazaar.repository.*;
import com.example.easybazaar.utilities.ValidationUtility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;



import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class SellerService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final SizeRepository sizeRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ColorRepository colorRepository;
    public User addSeller(SellerDto sellerDto) throws ResourceNotFoundException {





        if(sellerDto.getEmail()==null)
            throw new ResourceNotFoundException("Email is Mandatory");

        if (sellerDto.getCnic()==null)
            throw new ResourceNotFoundException("NIC is Mandatory");

        if (sellerDto.getPassword()==null)
            throw new ResourceNotFoundException("Password is Mandatory");

        if (!ValidationUtility.isValidNIC(sellerDto.getCnic()))
            throw new ResourceNotFoundException("NIC is Not Valid");

        if (userRepository.findByEmail(sellerDto.getEmail())!=null)
            throw new ResourceNotFoundException("Email Already Exists");
        if(userRepository.findByCnic(sellerDto.getCnic())!=null)
            throw new ResourceNotFoundException("CNIC is already Exists");

        User user = new User();
        addSellerInformation(sellerDto, user);
        user.setUserType(UserType.SELLER.toString());
        user.setIsActive(true);

        return  userRepository.save(user);
    }

    public User updateSellerInfo(SellerDto sellerDto) throws ResourceNotFoundException {

        User user = userRepository.findById(sellerDto.getId()).orElseThrow(()->
                new ResourceNotFoundException("User with ID "+sellerDto.getId()+" Not Found"));

        addSellerInformation(sellerDto, user);
        user.setIsActive(true);

        return  userRepository.save(user);
    }

    private void addSellerInformation(SellerDto sellerDto, User user) throws ResourceNotFoundException {

        if (sellerDto.getName()!=null)
            user.setName(sellerDto.getName());
        if (sellerDto.getAddress()!=null)
            user.setAddress(sellerDto.getAddress());
        if (sellerDto.getCnic()!=null){
            if(!ValidationUtility.isValidNIC(sellerDto.getCnic()))
                throw new ResourceNotFoundException("CNIC Format is Incorrect");
            user.setCnic(sellerDto.getCnic());
        }

        if (sellerDto.getCompanyName()!=null)
            user.setCompanyName(sellerDto.getCompanyName());
        if (sellerDto.getEmail()!=null)
            user.setEmail(sellerDto.getEmail());
        if (sellerDto.getContactNumber()!=null)
            user.setContactNumber(sellerDto.getContactNumber());
        if (sellerDto.getPassword()!=null)
            user.setPassword(AES.encrypt(sellerDto.getPassword(),"EASYBAZ"));
        if (sellerDto.getRegistrationDate()!=null)
            user.setRegistrationDate(sellerDto.getRegistrationDate());
        if (sellerDto.getCityId()!=null){

            City city = cityRepository.findById(sellerDto.getCityId()).orElseThrow(()-> new ResourceNotFoundException
                    ("City with ID "+sellerDto.getCityId()+" Not Found"));
            user.setCity(city);

        }
    }

    public User deleteSeller(Long sellerId) throws ResourceNotFoundException {

        User user = userRepository.findById(sellerId).orElseThrow
                (()-> new ResourceNotFoundException("User with ID "+sellerId+" Not Found"));

        user.setIsActive(false);

        return userRepository.save(user);
    }

    public List<AllSellersDto> allSellersInfo(SearchDto searchDto){


        Pageable pageable = PageRequest.of(searchDto.getPageNo(), searchDto.getPageSize());
        List<AllSellersDto> allSellers =  userRepository.allSellers(pageable);


        return allSellers;
    }


    public ProductVariant addProduct(CreateProductDto createProductDto) throws ResourceNotFoundException{

        if (createProductDto.getSellerId()==null)
            throw new ResourceNotFoundException("Seller Id is Must");
        try{
            User seller = userRepository.findByIdAndUserTypeAndIsActive(createProductDto.getSellerId(), "SELLER",true );
            if (seller == null)
                throw new ResourceNotFoundException("Seller Not Found");

            ProductVariant newProduct = new ProductVariant();

            if(createProductDto.getName()!=null)
                newProduct.setName(createProductDto.getName());
            if(createProductDto.getDescription()!=null)
                newProduct.setDescription(createProductDto.getDescription());
            if (createProductDto.getExpiryDate()!=null)
                newProduct.setExpiryDate(createProductDto.getExpiryDate());

            if(createProductDto.getAvailableColors()!=null){
                List<Color> colors = new ArrayList<>();

                for (Long colorId: createProductDto.getAvailableColors()) {
                    Color color = colorRepository.findById(colorId).orElseThrow(()->
                            new ResourceAccessException("Color with ID "+colorId+" Not Found"));
                    colors.add(color);
                }
                Set<Color> colorSet = new HashSet<>(colors);
//                newProduct.setAvailableColors(colors);
                newProduct.setAvailableColors(colorSet);
            }
            if(createProductDto.getRegularPrice()!=null)
                newProduct.setSellPrice((long) (createProductDto.getRegularPrice() + (createProductDto.getRegularPrice() * 0.15)));
            if (createProductDto.getAvailableQuantity()!=null)
                newProduct.setAvailableQuantity(createProductDto.getAvailableQuantity());

            newProduct.setSellerId(seller.getId());
            newProduct.setCreatedAt(LocalDate.now());
            newProduct.setRating(0L);

            return productVariantRepository.save(newProduct);

        }catch (Exception e){
            throw new ResourceNotFoundException("Seller not Found");
        }


    }

    public List<AllSellerProductsDto> allSellerProductsDto(SearchDto searchDto ,Long sellerId) throws ResourceNotFoundException {

        User user = userRepository.findByIdAndUserTypeAndIsActive(sellerId,"SELLER",true);
        if (user == null)
            throw new ResourceNotFoundException("Seller with ID "+sellerId+" Not Found");

        Pageable pageable = PageRequest.of(searchDto.getPageNo(), searchDto.getPageSize());

        return productVariantRepository.allSellerProducts(pageable,sellerId);
    }


    public SignUpDto singUpSeller(SignUpDto signUp) throws ResourceNotFoundException {

        User user = userRepository.findByEmail(signUp.getEmail());

        if (signUp.getFirstName()==null || signUp.getPassword()==null || signUp.getEmail()==null || signUp.getLastName()==null )
            throw new ResourceNotFoundException("Enter All Details");

        if (user!=null)
            throw new ResourceNotFoundException("User With This Email is Already Exists");

        User newUser = new User();
        newUser.setName(signUp.getFirstName()+" "+signUp.getLastName());
        newUser.setEmail(signUp.getEmail());
        String encryptedPassword = AES.encrypt(signUp.getPassword(),"EASYBAZ");
        newUser.setPassword(encryptedPassword);
        newUser.setUserType(UserType.SELLER.toString());
        userRepository.save(newUser);
        signUp.setPassword(encryptedPassword);

        return signUp;
    }

    public ProductVariant editProduct(CreateProductDto createProductDto) throws ResourceNotFoundException {

        if (createProductDto.getProductId()==null)
            throw new ResourceNotFoundException(" Product ID is Missing");
        if (createProductDto.getSellerId()==null)
            throw new ResourceNotFoundException("Seller Id is Missing");
        ProductVariant existingProduct = productVariantRepository.findByIdAndSellerId(createProductDto.getProductId(),
                createProductDto.getSellerId());
        if (existingProduct==null)
            throw new ResourceNotFoundException("Not Find Product");

        if (createProductDto.getRegularPrice()!=null)
            existingProduct.setSellPrice((long) (createProductDto.getRegularPrice() +
                    (createProductDto.getRegularPrice()) * 0.15));

        if (createProductDto.getName()!=null)
            existingProduct.setName(createProductDto.getName());

        if(createProductDto.getDescription()!=null)
            existingProduct.setDescription(createProductDto.getDescription());

        if (createProductDto.getAvailableQuantity()!=null)
            existingProduct.setAvailableQuantity(createProductDto.getAvailableQuantity());

        return productVariantRepository.save(existingProduct);
    }
}
