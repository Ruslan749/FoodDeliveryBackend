package com.example.webshop.controller;

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

import com.example.webshop.Model.Catalog;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private menuService menuService;
    private final ModelMapper modelMapper;


    @Autowired
    public MenuController(menuService menuService, ModelMapper modelMapper) {
        this.menuService = menuService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public Catalog getMenu() {
        return menuService.findAll();
    }

    @GetMapping("/{id}")
    public Menu getProductMenuById(@PathVariable("id") int id) {
        return menuService.findOne(id); // Jackson конвертирует в JSON
    }



    @ExceptionHandler
    private ResponseEntity<MenuErrorResponse> handleException(MenuNotFoundException e){
        MenuErrorResponse response = new MenuErrorResponse(
                "продук с таким id найден",
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