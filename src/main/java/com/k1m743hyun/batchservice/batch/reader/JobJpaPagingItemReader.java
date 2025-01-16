package com.k1m743hyun.batchservice.batch.reader;

import jakarta.persistence.EntityManagerFactory;

import com.k1m743hyun.batchservice.batch.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobJpaPagingItemReader {

    private final EntityManagerFactory entityManagerFactory;

    public JpaPagingItemReader<Product> getAll() {
        return new JpaPagingItemReaderBuilder<Product>()
            .name("jobJpaPagingItemReader")
            .entityManagerFactory(entityManagerFactory)
            .pageSize(10)
            .queryString("SELECT productId, productName, productPrice FROM Product")
            .build();
    }
}
