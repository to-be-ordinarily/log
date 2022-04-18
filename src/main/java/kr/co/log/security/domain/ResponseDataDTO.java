package kr.co.log.security.domain;

import lombok.Data;

@Data
public class ResponseDataDTO {
    private String code;
    private String status;
    private String message;
    private Object item;
    private String url;
}