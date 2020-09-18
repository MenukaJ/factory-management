package com.itp.factory.management.service.impl;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.domain.FactoryDetails;
import com.itp.factory.management.domain.User;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.repository.FactoryDetailsRepository;
import com.itp.factory.management.repository.UserRepository;
import com.itp.factory.management.resource.FactoryDetailsAddResource;
import com.itp.factory.management.resource.UserAddResource;
import com.itp.factory.management.service.FactoryDetailsService;
import com.itp.factory.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Component
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl extends MessagePropertyBase implements UserService {

    @Autowired
    private UserRepository UserRepository;

    public List<User> getAll(){
        return UserRepository.findAll();
    }

    public User addUser(UserAddResource UserAddResource)
    {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        if(LogginAuthentcation.getInstance().getUserName() == null || LogginAuthentcation.getInstance().getUserName().isEmpty())
            throw new ValidateRecordException(environment.getProperty("common.not-null"), "username");

//        Optional<FactoryDetails> isPresentCategory = factoryDetailsRepository.findByCompany_name(factoryDetailsAddResource.getName());
//        if (isPresentCategory.isPresent()) {
//            throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
//        }

        User user = new User();
        user.setType(UserAddResource.getType());
        user.setPassword(UserAddResource.getPassword());
        user.setName(UserAddResource.getName());
        user.setDob(user.getDob());
        user.setDob(user.getDob());
        user.setUser_name(UserAddResource.getUser_name());
        user.setUser_name(UserAddResource.getUser_name());
        user.setStatus(UserAddResource.getStatus());
        user.setCreatedDate(currentTimestamp);
        user.setCreatedUser(LogginAuthentcation.getInstance().getUserName());
        user = UserRepository.save(user);
        return user;
    }
}
