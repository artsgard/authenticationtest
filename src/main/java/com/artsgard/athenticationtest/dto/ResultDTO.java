package com.artsgard.athenticationtest.dto;

import java.sql.Timestamp;
import lombok.Data;

/**
 *
 * @author artsgard
 */
@Data
public class ResultDTO {
    private int statusCode;
    private String serverResponse;
    private String serverErrorResponse;
}
