package com.ecoservicios.rest_eco_servicios.repository;

import com.ecoservicios.rest_eco_servicios.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}