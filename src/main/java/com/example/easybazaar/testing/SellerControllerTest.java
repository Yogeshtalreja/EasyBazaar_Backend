package com.example.easybazaar.testing;

import com.example.easybazaar.controller.SellerController;
import com.example.easybazaar.dto.SellerDto;
import com.example.easybazaar.model.User;
import com.example.easybazaar.service.SellerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = SellerController.class)
public class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SellerService sellerService;

    @Test
    public void createSellerTest() throws Exception{

        User mockSeller = new User();
        mockSeller.setUserType("SELLER");
        mockSeller.setEmail("seller@gmail.com");
        mockSeller.setIsActive(true);
        mockSeller.setAddress("Sukkur IBA University");
        mockSeller.setPassword("seller@123");
        mockSeller.setContactNumber("0332-1234567");
        mockSeller.setName("Seller");
        mockSeller.setCnic("12345-1234567-1");
//        mockSeller.setRegistrationDate(LocalDate.now());


        String inputInJson = this.mapToJson(mockSeller);

        String URI = "http://localhost:9090/seller/addSeller";

        Mockito.when(sellerService.addSeller(Mockito.any(SellerDto.class))).thenReturn(mockSeller);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString();
        Assertions.assertThat(outputJson).isEqualTo(inputInJson);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    private String mapToJson(Object object) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
