package com.k1m743hyun.batchservice.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ChunkJobConfiguration {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    private static int value = 0;
    private static int chunkCount = 1;

    @Bean
    public Job job3(Step step3) {
        return new JobBuilder("job3", jobRepository)
                .start(step3)
                .build();
    }

    @JobScope
    @Bean
    public Step step3() {
        return new StepBuilder("step3", jobRepository)
                .chunk(10, platformTransactionManager)
                .reader(() -> {
                    if (value == 11) {
                        return null;
                    }
                    log.info(">>>>> reader: " + value);
                    return value++;
                })
                .processor(item -> {
                    log.info(">>>>> processor: " + item);
                    return item;
                })
                .writer(it -> {
                    log.info(">>>>> writer: [" + chunkCount++ + "번째 chunk] " + it.getItems());
                })
                .build();
    }
}
