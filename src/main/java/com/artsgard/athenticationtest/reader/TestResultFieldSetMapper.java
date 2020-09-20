package com.artsgard.athenticationtest.reader;

import com.artsgard.athenticationtest.dto.TestConfigDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */
@Component
public class TestResultFieldSetMapper implements FieldSetMapper {

    @Override
    public TestConfigDTO mapFieldSet(FieldSet fieldSet) {
        TestConfigDTO conf = new TestConfigDTO();
        conf.setUrl(fieldSet.readString("url"));
        conf.setContentType(fieldSet.readString("contentType"));
        conf.setFieldName1(fieldSet.readString("fieldName1"));
        conf.setFieldContent1(fieldSet.readString("fieldContent1"));
        conf.setFieldName2(fieldSet.readString("fieldName2"));
        conf.setFieldContent2(fieldSet.readString("fieldContent2"));
        
        return conf;
    }
}
