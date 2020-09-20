package com.artsgard.athenticationtest.config;

import javax.batch.api.listener.StepListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */
@StepScope
@Component
public class TestResultStepListener implements StepListener {

    @Override
    public void beforeStep() throws Exception {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<account step started!");
    }

    @Override
    public void afterStep() throws Exception {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<account step done!");
    }
    
}
