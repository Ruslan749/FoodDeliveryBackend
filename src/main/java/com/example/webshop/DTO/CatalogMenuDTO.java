package com.example.webshop.DTO;

import com.example.webshop.Model.Categories;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CatalogMenuDTO {
    List<MenuDTO> MenuList;
    List<Categories> categoriesList;

    public CatalogMenuDTO(List<MenuDTO> menuList, List<Categories> categoriesList) {
        MenuList = menuList;
        this.categoriesList = categoriesList;
    }
}
