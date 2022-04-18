package kr.co.log.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthVO {
    private String usrId;
    private String auth;

    public AuthVO() {
    }

    public AuthVO(String usrId, String auth) {
        this.usrId = usrId;
        this.auth = auth;
    }
}