package com.itp.factory.management.controller;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.domain.FactoryDetails;
import com.itp.factory.management.domain.User;
import com.itp.factory.management.resource.FactoryDetailsAddResource;
import com.itp.factory.management.resource.SuccessAndErrorDetailsResource;
import com.itp.factory.management.resource.UserAddResource;
import com.itp.factory.management.service.FactoryDetailsService;
import com.itp.factory.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*")
public class UserController extends MessagePropertyBase {

    @Autowired
    private UserService UserService;

    @GetMapping("/all")
    public ResponseEntity<Object> getFactoryDetails(){
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
        List<User> isPresentCategory = UserService.getAll();
        if(!isPresentCategory.isEmpty()) {
            return new ResponseEntity<>((Collection<User>)isPresentCategory, HttpStatus.OK);
        }
        else {
            responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addFactoryDetails(@Valid @RequestBody UserAddResource UserAddResource){
        User user = UserService.addUser(UserAddResource);
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_CREATED), Long.toString(user.getId()));
        return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
    }

}
