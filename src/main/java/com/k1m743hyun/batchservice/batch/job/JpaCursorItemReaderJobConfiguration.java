package com.k1m743hyun.batchservice.batch.job;

import com.k1m743hyun.batchservice.batch.entity.Product;
import com.k1m743hyun.batchservice.batch.reader.JobJdbcCursorItemReader;
import com.k1m743hyun.batchservice.batch.reader.JobJpaCursorItemReader;
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

import java.util.Iterator;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JpaCursorItemReaderJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final JobJpaCursorItemReader jobReader;

    @Bean
    public Job jpaCursorItemReaderJob(Step jpaCursorItemReaderStep) {
        return new JobBuilder("jpaCursorItemReaderJob", jobRepository)
                .start(jpaCursorItemReaderStep)
                .build();
    }

    @Bean
    public Step jpaCursorItemReaderStep() {
        return new StepBuilder("jpaCursorItemReaderStep", jobRepository)
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
