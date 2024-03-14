package com.example.webshop.controller;

import com.example.webshop.DTO.CatalogMenuDTO;
import com.example.webshop.DTO.MenuDTO;
import com.example.webshop.Model.Menu;
import com.example.webshop.service.menuService;
import com.example.webshop.utill.MenuErrorResponse;
import com.example.webshop.utill.MenuNotCreatedException;
import com.example.webshop.utill.MenuNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuController {
    private final menuService menuService;


    @Autowired
    public MenuController(menuService menuService) {
        this.menuService = menuService;
    }


    @GetMapping()
    public List<CatalogMenuDTO> getMenu() {
        return Collections.singletonList(menuService.findAll());
    }


    @GetMapping("/{id}")
    public Menu getProductMenuById(@PathVariable("id") int id) {
        return menuService.findOne(id); // Jackson конвертирует в JSON
    }



    @ExceptionHandler
    private ResponseEntity<MenuErrorResponse> handleException(MenuNotFoundException e){
        MenuErrorResponse response = new MenuErrorResponse(
                "продукт с таким id найден",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }




    // ошибка при создании нового блюда в меню
    @ExceptionHandler
    private ResponseEntity<MenuErrorResponse> handleException(MenuNotCreatedException e){
        MenuErrorResponse response = new MenuErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}