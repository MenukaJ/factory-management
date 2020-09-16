package com.itp.factory.management.service;

import java.util.List;

import com.itp.factory.management.domain.FactoryDetails;
import com.itp.factory.management.resource.FactoryDetailsAddResource;


/**
 * FactoryDetails Service
 *
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020  						   CHathuraJ        Created
 *
 ********************************************************************************************************
 */


public interface FactoryDetailsService {

    public  List<FactoryDetails> getAll();

    public FactoryDetails addFactoryDetails(FactoryDetailsAddResource factoryDetailsAddResource);
}
