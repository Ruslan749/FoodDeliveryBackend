package com.example.webshop.repository;

import com.example.webshop.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface menuRepository extends JpaRepository<Menu, Integer> {

}
