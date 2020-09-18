package com.itp.factory.management.service;

import com.itp.factory.management.domain.FactoryDetails;
import com.itp.factory.management.domain.User;
import com.itp.factory.management.resource.FactoryDetailsAddResource;
import com.itp.factory.management.resource.UserAddResource;

import java.util.List;

public interface UserService {

    public List<User> getAll();

    public User addUser(UserAddResource UserAddResource);
}
