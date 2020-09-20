package com.artsgard.athenticationtest.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_result")
public class TestResultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false, unique = false)
    private String url;

    @Column(name = "content_type", nullable = false, unique = false)
    private String contentType;

    @Column(name = "field_name1", nullable = false, unique = false)
    private String fieldName1;

    @Column(name = "field_content1", nullable = false, unique = false)
    private String fieldContent1;
    
    @Column(name = "field_name2", nullable = false, unique = false)
    private String fieldName2;

    @Column(name = "field_content2", nullable = false, unique = false)
    private String fieldContent2;
    
    @Column(name = "status_code", nullable = false, unique = false)
    private Integer statusCode;

    @Column(name = "server_response", nullable = false)
    private String serverResponse;
    
    @Column(name = "server_error_response", nullable = false)
    private String serverErrorResponse;
    
    @Column(name = "test_date", nullable = false)
    private Date testDate;

    @Column(name = "positive", nullable = false)
    private boolean positive;
}
