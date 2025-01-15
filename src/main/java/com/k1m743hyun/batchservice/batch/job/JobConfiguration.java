package com.k1m743hyun.batchservice.batch.job;

import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.Size;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    private static int size = 0;
    private static int chunk = 1;

    @Bean
    public Job simpleJob1(Step simpleStep1, Step simpleStep2) {
        return new JobBuilder("simpleJob", jobRepository)
                .start(simpleStep1)
                .next(simpleStep2)
                .build();
    }

    @JobScope
    @Bean
    public Step simpleStep1(Tasklet testTasklet) {
        return new StepBuilder("simpleStep1", jobRepository)
                .tasklet(testTasklet, platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet testTasklet() {
        return (contribution, chunkContext) -> {
            log.info(">>>>> This is Step1");
            return RepeatStatus.FINISHED;
        };
    }

    @JobScope
    @Bean
    public Step simpleStep2() {
        return new StepBuilder("simpleStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step2");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }

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
                    if (size == 11) {
                        return null;
                    }
                    log.info(">>>>> reader: " + size);
                    return size++;
                })
                .processor(item -> {
                    log.info(">>>>> processor: " + item);
                    return item;
                })
                .writer(it -> {
                    log.info(">>>>> writer: [" + chunk++ + "번째 chunk] " + it.getItems());
                })
                .build();
    }
}
