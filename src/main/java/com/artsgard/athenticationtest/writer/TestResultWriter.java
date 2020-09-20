package com.artsgard.athenticationtest.writer;

import com.artsgard.athenticationtest.model.TestResultEntity;
import com.artsgard.athenticationtest.repository.TestResultRepository;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */

@Component
//@Transactional
public class TestResultWriter implements ItemWriter<TestResultEntity> {
    
    @Autowired
    private TestResultRepository repo;
    
    @Override
    public void write(List<? extends TestResultEntity> results) throws Exception {
        
        repo.saveAll(results);
    }
}
