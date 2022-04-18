package kr.co.log.service;

import kr.co.log.domain.MemberVO;
import kr.co.log.persistence.MemberDAO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberDAO memberDAO, PasswordEncoder passwordEncoder) {
        this.memberDAO = memberDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public MemberVO createMember(MemberVO memberVO) {
        memberVO.encodePassword(passwordEncoder);
        return memberDAO.createUser(memberVO);
    }
}
