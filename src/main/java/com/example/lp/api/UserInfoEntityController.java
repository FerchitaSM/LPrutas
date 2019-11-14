package com.example.lp.api;


import com.example.lp.bl.CustomerBl;
import com.example.lp.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/person")
public class UserInfoEntityController {

    private CustomerBl customerBl;

    @Autowired
    public UserInfoEntityController(CustomerBl customerBl) {
        this.customerBl = customerBl;
    }


    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserInfoDto> all(@RequestParam(name = "includeAddress") boolean includeAddress) { // El valor por defecto sera false
        return customerBl.findAllUsers();
       /* if (includeAddress) {
            return customerBl.findAllPeopleWithAddress();
        } else {
            return customerBl.findAllPeople();
        }*/
    }


}
