package com.k1m743hyun.batchservice.batch.writer;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.k1m743hyun.batchservice.batch.entity.NewProduct;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobJpaItemWriter {

    private final EntityManagerFactory entityManagerFactory;

    public JpaItemWriter<NewProduct> insertAll() {
        JpaItemWriter<NewProduct> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
}
