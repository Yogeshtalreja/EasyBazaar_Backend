package com.example.easybazaar.controller;

import com.example.easybazaar.FeignClient;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.User;
import com.example.easybazaar.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/userWithIds")
public class OpenFeignController {

    private final FeignClient feignClient;
    private final UserRepository userRepository;

    @GetMapping
    public List<User> getUsersByIds() throws ResourceNotFoundException {

        List<Long> ids = feignClient.getIds();

        List<User> usersWithIds = new ArrayList<>();

        for (Long id:ids) {

            User user = userRepository.findById(id).orElseThrow(()->
                    new ResourceNotFoundException("Not Founds"));

            usersWithIds.add(user);
        }

        return usersWithIds;

    }


}
