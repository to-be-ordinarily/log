package kr.co.log.dto;

import lombok.Data;

@Data
public class ResponseDTO {

    private String code;
    private String status;
    private String message;
    private Object item;
    private String url;
}