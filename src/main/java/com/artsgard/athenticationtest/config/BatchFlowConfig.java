package com.artsgard.athenticationtest.config;

import com.artsgard.athenticationtest.dto.TestConfigDTO;
import com.artsgard.athenticationtest.processor.TestResultProcessor;
import com.artsgard.athenticationtest.reader.TestResultReader;
import com.artsgard.athenticationtest.writer.TestResultWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author artsgard
 */
@Configuration
@EnableBatchProcessing
public class BatchFlowConfig {
    
    @Autowired
    @Qualifier("dbTransactionManager") 
    private PlatformTransactionManager transactionManager;
    
    @Autowired
    private JobBuilderFactory jobBuilders;
    
    @Autowired
    TestResultStepListener testResultStepListener;

    @Autowired
    private StepBuilderFactory stepBuilders;
    
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private TestResultReader testResultReader;

    @Autowired
    private TestResultWriter testResultWriter;
    
    @Autowired
    private TestResultProcessor testResultProcessor;

    @Bean(name = "authentiaction-test-job")
    public Job accountJob() throws Exception {
        return jobBuilders.get("batch-testresult-job")
                .repository(jobRepository)
                .start(testResultStep())
                .build();
    }

    @Bean
    public Step testResultStep() throws Exception {
        return stepBuilders.get("batch-testresult-step")
                .transactionManager(transactionManager)
                .<TestConfigDTO, TestConfigDTO>chunk(20)
                .reader(testResultReader.read())
                .processor(testResultProcessor)
                .writer(testResultWriter)
                .listener(testResultStepListener)
                .build();
    }
}
