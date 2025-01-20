package com.k1m743hyun.batchservice.batch.job;

import java.util.Iterator;

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
import com.k1m743hyun.batchservice.batch.reader.JobJpaPagingItemReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JpaPagingItemReaderJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final JobJpaPagingItemReader jobReader;

    @Bean
    public Job jpaPagingItemReaderJob(@Qualifier("jpaPagingItemReaderStep") Step jpaPagingItemReaderStep) {
        return new JobBuilder("jpaPagingItemReaderJob", jobRepository)
                .start(jpaPagingItemReaderStep)
                .build();
    }

    @JobScope
    @Bean
    public Step jpaPagingItemReaderStep() throws Exception {
        return new StepBuilder("jpaPagingItemReaderStep", jobRepository)
                .<Product, Product>chunk(10, platformTransactionManager)
                .reader(jobReader.getAll())
                .writer(items -> {
                    log.info(">>>>>>> items: " + items);
                    Iterator iterator = items.iterator();
                    while (iterator.hasNext()) {
                        Object[] objects = (Object[]) iterator.next();
                        log.info(">>>>>> " + objects[1]);
                    }
                })
                .build();
    }
}
