package com.example.webshop.service;

import com.example.webshop.DTO.CatalogMenuDTO;
import com.example.webshop.DTO.MenuDTO;
import com.example.webshop.Model.Menu;
import com.example.webshop.repository.CategoriesRepository;
import com.example.webshop.repository.menuRepository;
import com.example.webshop.utill.MenuNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class menuService {

    private final menuRepository menuRepository;
    private final CategoriesRepository categoriesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public menuService(menuRepository menuRepository, CategoriesRepository categoriesRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.categoriesRepository = categoriesRepository;
        this.modelMapper = modelMapper;
    }

    public CatalogMenuDTO findAll() {
        var categories = categoriesRepository.findAll();
        var menu = menuRepository.findAll().stream().map(this::convertToMenuDTO).toList();

        return new CatalogMenuDTO(menu,categories);
    }


    //перекладываем данные из Menu в MenuDTO
    private MenuDTO convertToMenuDTO(Menu menu){
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setDescription(menu.getDescription());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setNameFood(menu.getNameFood());
        menuDTO.setCategories_id(menu.getCategories().getId());

        return menuDTO;
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
