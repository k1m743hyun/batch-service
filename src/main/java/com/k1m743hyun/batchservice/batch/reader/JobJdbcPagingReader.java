package com.k1m743hyun.batchservice.batch.reader;

import com.k1m743hyun.batchservice.batch.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobJdbcPagingReader {

    private final DataSource dataSource;

    public JdbcPagingItemReader<Product> getAll() throws Exception {
        JdbcPagingItemReader<Product> jdbcPagingItemReader = new JdbcPagingItemReaderBuilder<Product>()
                .name("jobJdbcPagingReader")
                .pageSize(3)
                .fetchSize(20)
                .dataSource(dataSource)
                .beanRowMapper(Product.class)
                .maxItemCount(10)
                .currentItemCount(0)
//                .queryProvider()
                .build();
        jdbcPagingItemReader.afterPropertiesSet();
        return jdbcPagingItemReader;
    }
}
