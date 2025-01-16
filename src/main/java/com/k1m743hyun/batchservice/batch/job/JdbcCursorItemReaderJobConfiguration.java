package com.k1m743hyun.batchservice.batch.job;

import com.k1m743hyun.batchservice.batch.entity.Product;
import com.k1m743hyun.batchservice.batch.reader.JobJdbcCursorItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JdbcCursorItemReaderJobConfiguration {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    private final JobJdbcCursorItemReader jobReader;

    @Bean
    public Job jdbcCursorItemReaderJob(Step jdbcCursorItemReaderStep) {
        return new JobBuilder("jdbcCursorItemReaderJob", jobRepository)
                .start(jdbcCursorItemReaderStep)
                .build();
    }

    @Bean
    public Step jdbcCursorItemReaderStep() {
        return new StepBuilder("jdbcCursorItemReaderStep", jobRepository)
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
