package kr.co.log.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Getter
@Setter
public class MemberVO {
    private String usrId;
    private String usrPw;
    private String usrNm;
    private String wthdYn;
    private Integer errCnt;
    private String enableYn;
    private List<AuthVO> authList;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.usrPw = passwordEncoder.encode(this.usrId);
    }
    public MemberVO(){}

    public MemberVO(String usrId, String usrPw, String usrNm, String wthdYn, Integer errCnt, String enableYn, List<AuthVO> authList) {
        this.usrId = usrId;
        this.usrPw = usrPw;
        this.usrNm = usrNm;
        this.wthdYn = wthdYn;
        this.errCnt = errCnt;
        this.enableYn = enableYn;
        this.authList = authList;
    }
}