package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import com.itp.factory.management.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.factory.management.enums.CommonStatus;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional <UserRole> findByName(String name);

    List <UserRole> findByStatus(CommonStatus status);

    Optional <UserRole> findByNameAndId(String name, Long id);
}
