package com.itp.factory.management.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.itp.factory.management.domain.FactoryDetails;
import com.itp.factory.management.repository.FactoryDetailsRepository;
import com.itp.factory.management.resource.FactoryDetailsAddResource;
import com.itp.factory.management.service.FactoryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.exception.ValidateRecordException;

/**
 * Insurance Type Service Impl
 *
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   09-07-2020       						  MenukaJ        Created
 *
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class FactoryDetailsServiceImpl extends MessagePropertyBase implements FactoryDetailsService {

    @Autowired
    private FactoryDetailsRepository factoryDetailsRepository;

    public  List<FactoryDetails> getAll(){
        return factoryDetailsRepository.findAll();
    }

    public FactoryDetails addFactoryDetails(FactoryDetailsAddResource factoryDetailsAddResource)
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

        FactoryDetails factoryDetails = new FactoryDetails();
        factoryDetails.setCompany_name(factoryDetailsAddResource.getName());;
        factoryDetails.setAddress(factoryDetailsAddResource.getStatus());
        factoryDetails.setCreatedDate(currentTimestamp);
        factoryDetails.setCreatedUser(LogginAuthentcation.getInstance().getUserName());
        factoryDetails = factoryDetailsRepository.save(factoryDetails);
        return factoryDetails;
    }
}
