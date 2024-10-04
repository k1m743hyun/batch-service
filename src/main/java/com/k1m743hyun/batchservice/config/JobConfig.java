package com.k1m743hyun.batchservice.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.k1m743hyun.batchservice.vo.Member;
import lombok.extern.slf4j.Slf4j;

import static com.k1m743hyun.batchservice.config.TransactionManagerConfig.DOMAIN_TRANSACTION_MANAGER;

@Slf4j
@Configuration
public class JobConfig {

    public static final String AUTOMATIC_PAYMENT_JOB = "automaticPaymentExecutionJob";
    public static final String AUTOMATIC_PAYMENT_STEP = "executeAutomaticPaymentStep";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager domainTransactionManager;

    public JobConfig(JobRepository jobRepository, @Qualifier(DOMAIN_TRANSACTION_MANAGER) PlatformTransactionManager domainTransactionManager) {
        this.jobRepository = jobRepository;
        this.domainTransactionManager = domainTransactionManager;
    }

    @Bean(AUTOMATIC_PAYMENT_JOB)
    public Job automaticPaymentExecutionJob(@Qualifier(AUTOMATIC_PAYMENT_STEP) Step executeAutomaticPaymentStep) {
        return new JobBuilder("AutomaticPaymentExecutionJob", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(executeAutomaticPaymentStep)
            .build();
    }

    //@Bean(AUTOMATIC_PAYMENT_STEP)
    //public Step executeAutomaticPaymentStep(@Qualifier(AUTOMATIC_PAYMENT_READER) QuerydslNoOffsetPagingItemReader<Member> memberItemReaderForAutomaticPayment,
    //                                        @Qualifier(AUTOMATIC_PAYMENT_PROCESSOR) ItemProcessor<Member, Payment> paymentProcessorForAutomaticPayment,
    //                                        @Qualifier(AUTOMATIC_PAYMENT_WRITER) ItemWriter<Payment> paymentItemWriterForAutomaticPayment) {
    //    return new StepBuilder("executeAutomaticPaymentStep", jobRepository)
    //        .<Member, Payment>chunk(10, domainTransactionManager)
    //        .reader(memberItemReaderForAutomaticPayment)
    //        .processor(paymentProcessorForAutomaticPayment)
    //        .writer(paymentItemWriterForAutomaticPayment)
    //        .build();
    //}
}
