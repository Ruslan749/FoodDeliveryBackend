package com.example.webshop.Model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;


@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_food")
    @NotEmpty(message = "имя не должно быть пустым")
    @Size(min = 5,message = "имя не должно быть меньше 2 символов и больше 30")
    private String nameFood;

    @Column(name = "price")
    @Min(value = 10,message = "цена не должна быть пустой")
    private int price;

    @Column(name = "description")
    @NotEmpty(message = "описание не должено быть пустым")
    @Size(min = 5,message = "в описание не соответствие колличества символов")
    private String description;

}