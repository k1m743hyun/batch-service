package com.k1m743hyun.batchservice.batch.reader;

import com.k1m743hyun.batchservice.batch.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobJdbcCursorItemReader {

    private final DataSource dataSource;

    public JdbcCursorItemReader<Product> getAll() {
        return new JdbcCursorItemReaderBuilder<Product>()
                .name("jobJdbcCursorItemReader")
                .fetchSize(20)
                .dataSource(dataSource)
                .beanRowMapper(Product.class)
                .sql("SELECT product_id, product_name, product_price FROM tb_product")
                .maxItemCount(20)
                .currentItemCount(0)
                .build();
    }
}
