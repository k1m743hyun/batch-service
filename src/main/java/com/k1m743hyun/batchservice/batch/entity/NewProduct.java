package com.k1m743hyun.batchservice.batch.entity;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_new_product")
@Entity
public class NewProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    public NewProduct(String productName, int productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public NewProduct(Long productId, String productName, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
