package com.artsgard.athenticationtest.reader;

import com.artsgard.athenticationtest.model.TestResultEntity;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */
@Component
public class TestResultReader {

    @Autowired
    private TestResultFieldSetMapper resultMapper;

    public FlatFileItemReader read() {
        return new FlatFileItemReaderBuilder<TestResultEntity>()
                .name("test-result-reader")
                .resource(new FileSystemResource("test-config.csv"))
                .strict(true)
                .linesToSkip(0)
                .delimited().delimiter("||")
                .names(new String[]{"url", "contentType", "fieldName1", "fieldContent1", "fieldName2", "fieldContent2"})
                .fieldSetMapper(resultMapper)
                //.targetType(TestResultEntity.class)
                .build();
    }
}
