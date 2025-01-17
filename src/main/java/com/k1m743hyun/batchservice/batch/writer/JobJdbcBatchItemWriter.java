package com.k1m743hyun.batchservice.batch.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.k1m743hyun.batchservice.batch.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobJdbcBatchItemWriter {

    private final DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<Product> insertAll() {
        JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriterBuilder<Product>()
            .dataSource(dataSource)
            .sql("insert into tb_new_product(product_id, product_name, product_price) values (:productId, :productName, :productPrice)")
            //.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .beanMapped()
            .build();

        writer.afterPropertiesSet();

        return writer;
    }
}
