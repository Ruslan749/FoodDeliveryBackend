package com.example.webshop.Model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
public class Catalog {
     List<Menu> products;

     public Catalog(List<Menu> products) {
          this.products = products;
     }
}
