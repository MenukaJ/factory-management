package com.itp.factory.management.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.itp.factory.management.domain.UserRole;
import com.itp.factory.management.repository.UserRoleRepository;
import com.itp.factory.management.resource.UserRoleAddResource;
import com.itp.factory.management.resource.UserRoleUpdateResource;
import com.itp.factory.management.service.FactoryDetailsService;
import com.itp.factory.management.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.enums.CommonStatus;
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
public class UserRoleServiceImpl extends MessagePropertyBase implements UserRoleService {

    @Autowired
    private UserRoleRepository UserRoleRepository;

    @Override
    public List<UserRole> getAll() {
        return UserRoleRepository.findAll();
    }

    @Override
    public Optional<UserRole> getById(Long id) {
        Optional<UserRole> isPresentCategory = UserRoleRepository.findById(id);
        if (isPresentCategory.isPresent()) {
            return Optional.ofNullable(isPresentCategory.get());
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserRole> getByName(String name) {
        Optional<UserRole> isPresentCategory = UserRoleRepository.findByName(name);
        if (isPresentCategory.isPresent()) {
            return Optional.ofNullable(isPresentCategory.get());
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public List<UserRole> getByStatus(String status) {
        return UserRoleRepository.findByStatus(CommonStatus.valueOf(status));
    }

    @Override
    public UserRole addUserRole(UserRoleAddResource UserRoleAddResource) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        if(LogginAuthentcation.getInstance().getUserName() == null || LogginAuthentcation.getInstance().getUserName().isEmpty())
            throw new ValidateRecordException(environment.getProperty("common.not-null"), "username");

        Optional<UserRole> isPresentCategory = UserRoleRepository.findByName(UserRoleAddResource.getName());
        if (isPresentCategory.isPresent()) {
            throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
        }

        UserRole userRole = new UserRole();
        userRole.setName(UserRoleAddResource.getName());;
        userRole.setStatus(CommonStatus.valueOf(UserRoleAddResource.getStatus()));
        userRole.setCreatedDate(currentTimestamp);
        userRole.setCreatedUser(LogginAuthentcation.getInstance().getUserName());
        userRole = UserRoleRepository.save(userRole);
        return userRole;
    }

    @Override
    public UserRole updateUserRole(UserRoleUpdateResource UserRoleUpdateResource) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
            throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");

        Optional<UserRole> isPresentCategory = UserRoleRepository.findById(Long.parseLong(UserRoleUpdateResource.getId()));
        if (!isPresentCategory.isPresent())
            throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");

        if(!isPresentCategory.get().getVersion().equals(Long.parseLong(UserRoleUpdateResource.getVersion())))
            throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "version");

        Optional<UserRole> isPresentCategoryName = UserRoleRepository.findByName(UserRoleUpdateResource.getName());
        if (isPresentCategoryName.isPresent() && isPresentCategoryName.get().getId() != isPresentCategory.get().getId())
            throw new ValidateRecordException(environment.getProperty("common.unique"), "name");

        UserRole userRole = isPresentCategory.get();
        userRole.setName(UserRoleUpdateResource.getName());
        userRole.setStatus(CommonStatus.valueOf(UserRoleUpdateResource.getStatus()));
        userRole.setModifiedDate(currentTimestamp);
        userRole.setModifiedUser(LogginAuthentcation.getInstance().getUserName());
        userRole = UserRoleRepository.saveAndFlush(userRole);
        return userRole;
    }

    @Override
    public void deleteUserRole(Long id) {
        Optional<UserRole> isPresentCategory = UserRoleRepository.findById(id);
        if (!isPresentCategory.isPresent())
            throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
        else
            UserRoleRepository.deleteById(id);
    }
}
