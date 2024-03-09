package com.example.webshop.service;

import com.example.webshop.Model.Menu;
import com.example.webshop.repository.menuRepository;

import com.example.webshop.utill.MenuNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshop.Model.Catalog;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class menuService {

    private final menuRepository menuRepository;

    @Autowired
    public menuService(menuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Catalog findAll() {
        var result = menuRepository.findAll();
        return new Catalog(menuRepository.findAll());
    }

    public Menu findOne(int id) {
        Optional<Menu> foundMenu = menuRepository.findById(id);
        return foundMenu.orElseThrow(MenuNotFoundException::new);
    }

    @Transactional
    public void save (Menu menu){
        menuRepository.save(menu);
    }
}
