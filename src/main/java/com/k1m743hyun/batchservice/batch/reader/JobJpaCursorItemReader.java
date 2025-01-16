package com.k1m743hyun.batchservice.batch.reader;

import com.k1m743hyun.batchservice.batch.entity.Product;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobJpaCursorItemReader {

    private final EntityManagerFactory entityManagerFactory;

    public JpaCursorItemReader<Product> getAll() {
        return new JpaCursorItemReaderBuilder<Product>()
                .name("jobJpaCursorReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT productId, productName, productPrice FROM Product")
                .currentItemCount(0)
                .maxItemCount(100)
                .build();
    }
}
