package kr.co.log.security.domain;

import kr.co.log.domain.MemberVO;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

@Getter
public class CustomUser extends User {
    private final MemberVO user;
    public CustomUser(MemberVO user) {
        super(
                user.getUsrId(),
                user.getUsrPw(),
                user.getEnableYn().equals("Y"),
                true,
                true,
                true,
                user.getAuthList().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList())
        );
        this.user = user;
    }

}