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
import com.k1m743hyun.batchservice.batch.writer.JobJdbcBatchItemWriter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JdbcBatchItemWriterJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final JobJdbcPagingItemReader reader;
    private final JobJdbcBatchItemWriter writer;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job jdbcBatchItemWriterJob(@Qualifier("jdbcBatchItemWriterStep") Step jdbcBatchItemWriterStep) {
        return new JobBuilder("jdbcBatchItemWriterJob", jobRepository)
            .start(jdbcBatchItemWriterStep)
            .build();
    }

    @JobScope
    @Bean
    public Step jdbcBatchItemWriterStep() throws Exception {
        return new StepBuilder("jdbcBatchItemWriterStep", jobRepository)
            .<Product, Product>chunk(CHUNK_SIZE, platformTransactionManager)
            .reader(reader.getAll())
            .writer(writer.insertAll())
            .build();
    }
}
