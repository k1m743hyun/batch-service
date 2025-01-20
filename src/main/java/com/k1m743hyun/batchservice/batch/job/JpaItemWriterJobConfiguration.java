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

import com.k1m743hyun.batchservice.batch.entity.NewProduct;
import com.k1m743hyun.batchservice.batch.entity.Product;
import com.k1m743hyun.batchservice.batch.processor.JobJpaItemProcessor;
import com.k1m743hyun.batchservice.batch.reader.JobJpaPagingItemReader;
import com.k1m743hyun.batchservice.batch.writer.JobJpaItemWriter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JpaItemWriterJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final JobJpaPagingItemReader reader;
    private final JobJpaItemProcessor processor;
    private final JobJpaItemWriter writer;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job jpaItemWriterJob(@Qualifier("jpaItemWriterStep") Step jpaItemWriterStep) {
        return new JobBuilder("jpaItemWriterJob", jobRepository)
            .start(jpaItemWriterStep)
            .build();
    }

    @JobScope
    @Bean
    public Step jpaItemWriterStep() throws Exception {
        return new StepBuilder("jpaItemWriterStep", jobRepository)
            .<Product, NewProduct>chunk(CHUNK_SIZE, platformTransactionManager)
            .reader(reader.getAll())
            .processor(processor.processAll())
            .writer(writer.insertAll())
            .build();
    }
}
