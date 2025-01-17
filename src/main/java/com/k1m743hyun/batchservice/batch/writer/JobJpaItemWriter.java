package com.k1m743hyun.batchservice.batch.writer;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;
import com.k1m743hyun.batchservice.batch.entity.NewProduct;
import com.k1m743hyun.batchservice.batch.entity.Product;
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
