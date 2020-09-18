package com.itp.factory.management.repository;

import com.itp.factory.management.domain.FactoryDetails;
import com.itp.factory.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
