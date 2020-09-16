package com.itp.factory.management.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.itp.factory.management.domain.UserRole;
import com.itp.factory.management.repository.UserRoleRepository;
import com.itp.factory.management.resource.*;
import com.itp.factory.management.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.domain.Category;
import com.itp.factory.management.enums.CommonStatus;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.service.CategoryService;

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
@RequestMapping(value = "/user-role")
@CrossOrigin(origins = "*")
public class UserRoleController extends MessagePropertyBase{

    @Autowired
    private UserRoleService UserRoleService;

    /*
     * get all category
     * @param @PathVariable{tenantId}
     * @param @PathVariable{all}
     * @return List
     **/
    @GetMapping("/all")
    public ResponseEntity<Object> getAllUserRole(){
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
        List<UserRole>isPresentCategory = UserRoleService.getAll();
        if(!isPresentCategory.isEmpty()) {
            return new ResponseEntity<>((Collection<UserRole>)isPresentCategory,HttpStatus.OK);
        }
        else {
            responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable(value = "id", required = true) Long id){
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
        Optional<UserRole> isPresentCategory = UserRoleService.getById(id);
        if(isPresentCategory.isPresent()) {
            return new ResponseEntity<>(isPresentCategory.get(), HttpStatus.OK);
        }
        else {
            responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Object> getCategoryByName(@PathVariable(value = "name", required = true) String name){
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
        Optional<UserRole> isPresentCategory = UserRoleService.getByName(name);
        if(isPresentCategory.isPresent()) {
            return new ResponseEntity<>(isPresentCategory.get(), HttpStatus.OK);
        }
        else {
            responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<Object> getCategoryByStatus(@PathVariable(value = "status", required = true) String status){
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
        if(status.equals(CommonStatus.ACTIVE.toString()) || status.equals(CommonStatus.INACTIVE.toString())) {
            List<UserRole> isPresentCategory = UserRoleService.getByStatus(status);
            if(!isPresentCategory.isEmpty()) {
                return new ResponseEntity<>(isPresentCategory, HttpStatus.OK);
            }
            else {
                responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
                return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
            }
        }
        else {
            responseMessage.setMessages(RECORD_NOT_FOUND);
            throw new ValidateRecordException(environment.getProperty("common-status.pattern"), "message");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCategory(@Valid @RequestBody UserRoleAddResource userRoleAddResource){
        UserRole userRole = UserRoleService.addUserRole(userRoleAddResource);
        SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_CREATED), Long.toString(userRole.getId()));
        return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable(value = "id", required = true) Long id,
                                                 @Valid @RequestBody UserRoleUpdateResource userRoleUpdateResource){
        SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
        Optional<UserRole>isPresentCategory = UserRoleService.getById(id);
        if(isPresentCategory.isPresent()) {
            userRoleUpdateResource.setId(id.toString());
            UserRole userRole = UserRoleService.updateUserRole(userRoleUpdateResource);
            successAndErrorDetailsResource = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_UPDATED), userRole.getId().toString());
            return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
        }
        else {
            successAndErrorDetailsResource.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
            return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable(value = "id", required = true) Long id){
        SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
        Optional<UserRole>isPresentCategory = UserRoleService.getById(id);
        if(isPresentCategory.isPresent()) {
            UserRoleService.deleteUserRole(id);
            successAndErrorDetailsResource = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_DELETED), id.toString());
            return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
        }
        else {
            successAndErrorDetailsResource.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
            return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
