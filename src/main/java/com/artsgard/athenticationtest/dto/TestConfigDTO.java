package com.artsgard.athenticationtest.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestConfigDTO implements Serializable {

    private String url;
    private String contentType;
    private String fieldName1;
    private String fieldContent1;
    private String fieldName2;
    private String fieldContent2;
}