package com.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product {
    private String name;
    private Double price;

}
