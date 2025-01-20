package com.k1m743hyun.batchservice.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.k1m743hyun.batchservice.batch.entity.Product;
import com.k1m743hyun.batchservice.batch.reader.JobJdbcPagingItemReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JdbcPagingItemReaderJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final JobJdbcPagingItemReader jobReader;

    @Bean
    public Job jdbcPagingItemReaderJob(@Qualifier("jdbcPagingItemReaderStep") Step jdbcPagingItemReaderStep) {
        return new JobBuilder("jdbcPagingItemReaderJob", jobRepository)
                .start(jdbcPagingItemReaderStep)
                .build();
    }

    @JobScope
    @Bean
    public Step jdbcPagingItemReaderStep() throws Exception {
        return new StepBuilder("jdbcPagingItemReaderStep", jobRepository)
                .<Product, Product>chunk(10, platformTransactionManager)
                .reader(jobReader.getAll())
                .writer(list -> {
                    for (Product entity: list) {
                        log.info("Current Product: " + entity.getProductName());
                    }
                })
                .build();
    }
}
