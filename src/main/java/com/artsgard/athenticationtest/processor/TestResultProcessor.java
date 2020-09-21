package com.artsgard.athenticationtest.processor;

import com.artsgard.athenticationtest.dto.ResultDTO;
import com.artsgard.athenticationtest.dto.TestConfigDTO;
import com.artsgard.athenticationtest.externalservice.AuthenticationService;
import com.artsgard.athenticationtest.model.TestResultEntity;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author artsgard
 */
@Component
//@Transactional
public class TestResultProcessor implements ItemProcessor<TestConfigDTO, TestResultEntity> {

    @Override
    public TestResultEntity process(TestConfigDTO config) throws Exception {
       TestResultEntity result = new TestResultEntity();
       result.setUrl(config.getUrl());
       result.setContentType(config.getContentType());
       result.setFieldName1(config.getFieldName1());
       result.setFieldContent1(config.getFieldContent1());
       result.setFieldName2(config.getFieldName2());
       result.setFieldContent2(config.getFieldContent2());
       
       ResultDTO rslt = AuthenticationService.getAthentiatioResult(
               config.getUrl(), 
               config.getContentType(), 
               config.getFieldName1(),
               config.getFieldContent1(), 
               config.getFieldName2(),
               config.getFieldContent2());
       
       result.setServerErrorResponse(rslt.getServerErrorResponse());
       result.setStatusCode(rslt.getStatusCode());
       result.setServerResponse(rslt.getServerResponse());
       result.setTestDate(new Date());
       if (rslt.getServerResponse().equals(AuthenticationService.ServerState.SERVER_ERROR)) {
            result.setPositive(false);
       } else {
            result.setPositive(true);
       }
       return result; 
    }
}
