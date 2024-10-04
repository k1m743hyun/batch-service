package com.k1m743hyun.batchservice.vo;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;
}