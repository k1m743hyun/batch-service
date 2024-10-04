package com.k1m743hyun.batchservice.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static com.k1m743hyun.batchservice.config.TransactionManagerConfig.META_TRANSACTION_MANAGER;

@Configuration
public class BatchConfig extends DefaultBatchConfiguration {

    private final DataSource mainDataSource;
    private final PlatformTransactionManager metaTransactionManager;

    public BatchConfig(@Qualifier(DataSourceConfig.META_DATASOURCE) DataSource metaDataSource, @Qualifier(META_TRANSACTION_MANAGER) PlatformTransactionManager metaTransactionManager) {
        this.mainDataSource = metaDataSource;
        this.metaTransactionManager = metaTransactionManager;
    }

    @Override
    protected DataSource getDataSource() {
        return mainDataSource;
    }

    @Override
    protected PlatformTransactionManager getTransactionManager() {
        return metaTransactionManager;
    }
}
