package com.k1m743hyun.batchservice.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.k1m743hyun.batchservice.batch.entity.NewProduct;
import com.k1m743hyun.batchservice.batch.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobJpaItemProcessor {

    public ItemProcessor<Product, NewProduct> processAll() {
        return product -> new NewProduct(product.getProductId(), product.getProductName(), product.getProductPrice());
    }
}
