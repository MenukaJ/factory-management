package com.itp.factory.management.service;

import java.util.List;
import java.util.Optional;

import com.itp.factory.management.domain.UserRole;
import com.itp.factory.management.resource.UserRoleAddResource;
import com.itp.factory.management.resource.UserRoleUpdateResource;
import org.springframework.stereotype.Service;


/**
 * UserRole Service
 *
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020  						   CHathuraJ        Created
 *
 ********************************************************************************************************
 */
public interface UserRoleService {

    /**
     *
     * Find all userRole
     * @author chathuraj
     * @return -JSON array of all Category
     *
     * */
    public  List<UserRole> getAll();

    /**
     *
     * Find Category by ID
     * @author chathuraj
     * @return -JSON array of Category
     *
     * */
    public Optional<UserRole> getById(Long id);

    /**
     *
     * Find Category by name
     * @author chathuraj
     * @return -JSON array of Category
     *
     * */
    public Optional<UserRole> getByName(String name);

    /**
     *
     * Find Category by status
     * @author chathuraj
     * @return -JSON array of Category
     *
     * */
    public List<UserRole> getByStatus(String status);

    /**
     *
     * Insert Category
     * @author chathuraj
     * @param  - CategoryAddResource
     * @return - Successfully saved
     *
     * */
    public UserRole addUserRole(UserRoleAddResource UserRoleAddResource);

    /**
     *
     * Update Category
     * @author chathuraj
     * @param  - CategoryUpdateResource
     * @return - Successfully saved
     *
     * */
    public UserRole updateUserRole(UserRoleUpdateResource UserRoleUpdateResource);

    /**
     *
     * Delete Category
     * @author chathuraj
     *
     * */
    public void deleteUserRole(Long id);
}
