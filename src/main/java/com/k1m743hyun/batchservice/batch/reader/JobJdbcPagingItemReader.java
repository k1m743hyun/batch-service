package com.k1m743hyun.batchservice.batch.reader;

import com.k1m743hyun.batchservice.batch.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobJdbcPagingItemReader {

    private final DataSource dataSource;

    public JdbcPagingItemReader<Product> getAll() throws Exception {
        JdbcPagingItemReader<Product> jdbcPagingItemReader = new JdbcPagingItemReaderBuilder<Product>()
                .name("jobJdbcPagingItemReader")
                .pageSize(3)
                .fetchSize(20)
                .dataSource(dataSource)
                .beanRowMapper(Product.class)
                .maxItemCount(10)
                .currentItemCount(0)
                .queryProvider(createQueryProvider())
                .build();
        jdbcPagingItemReader.afterPropertiesSet();
        return jdbcPagingItemReader;
    }

    @Bean
    public PagingQueryProvider createQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("product_id, product_name, product_price");
        queryProvider.setFromClause("from product");
        //queryProvider.setWhereClause("where amount >= :amount");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("product_id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        return queryProvider.getObject();
    }
}
