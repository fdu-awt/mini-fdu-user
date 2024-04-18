package org.fdu.awt.minifduuser.dao;

import org.fdu.awt.minifduuser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByNameAndPassword(String name, String password);

}
