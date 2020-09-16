package com.itp.factory.management.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import com.itp.factory.management.domain.FactoryDetails;
import com.itp.factory.management.resource.*;
import com.itp.factory.management.service.FactoryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itp.factory.management.base.MessagePropertyBase;

/**
 * Category Controller
 *
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020                             MenukaJ        Created
 *
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/factory-details")
@CrossOrigin(origins = "*")
public class FactoryDetailsController extends MessagePropertyBase {

    @Autowired
    private FactoryDetailsService FactoryDetailsService;

    @GetMapping("/all")
    public ResponseEntity<Object> getFactoryDetails(){
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
        List<FactoryDetails>isPresentCategory = FactoryDetailsService.getAll();
        if(!isPresentCategory.isEmpty()) {
            return new ResponseEntity<>((Collection<FactoryDetails>)isPresentCategory,HttpStatus.OK);
        }
        else {
            responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addFactoryDetails(@Valid @RequestBody FactoryDetailsAddResource factoryDetailsAddResource){
        FactoryDetails factoryDetails = FactoryDetailsService.addFactoryDetails(factoryDetailsAddResource);
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_CREATED), Long.toString(factoryDetails.getId()));
        return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
    }


}
