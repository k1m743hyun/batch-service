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
import com.k1m743hyun.batchservice.batch.reader.JobRepositoryItemReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RepositoryItemReaderJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final JobRepositoryItemReader jobReader;

    @Bean
    public Job repositoryItemReaderJob(@Qualifier("repositoryItemReaderStep") Step repositoryItemReaderStep) {
        return new JobBuilder("repositoryItemReaderJob", jobRepository)
            .start(repositoryItemReaderStep)
            .build();
    }

    @JobScope
    @Bean
    public Step repositoryItemReaderStep() throws Exception {
        return new StepBuilder("repositoryItemReaderStep", jobRepository)
            .<Product, Product>chunk(10, platformTransactionManager)
            .reader(jobReader.getAll())
            .writer(items -> {
                log.info(">>>>>>> items: " + items);
                for (Product item : items) {
                    log.info(">>>>>> item: " + item.getProductName());
                }
            })
            .build();
    }
}
