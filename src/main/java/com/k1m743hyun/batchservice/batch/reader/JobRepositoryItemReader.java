package com.k1m743hyun.batchservice.batch.reader;

import jakarta.persistence.EntityManagerFactory;

import java.awt.print.Pageable;
import java.util.Collections;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.k1m743hyun.batchservice.batch.entity.Product;
import com.k1m743hyun.batchservice.batch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobRepositoryItemReader {

    private final ProductRepository repository;

    public RepositoryItemReader<Product> getAll() {
        return new RepositoryItemReaderBuilder<Product>()
            .name("jobRepositoryItemReader")
            .repository(repository)
            .methodName("findAll")
            .pageSize(10)
            //.maxItemCount(10)
            .arguments()
            .sorts(Collections.singletonMap("productId", Sort.Direction.ASC))
            .build();
    }
}
