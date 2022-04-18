package kr.co.log.security;

import kr.co.log.domain.MemberVO;
import kr.co.log.persistence.MemberDAO;
import kr.co.log.security.domain.CustomUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final MemberDAO dao;

    public CustomUserDetailsService(MemberDAO dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails result = null;

        try {
            MemberVO user = dao.loginUser(username);

            if (user == null) {
                throw new UsernameNotFoundException("===== Cannot find a user =====");
            }

            result = new CustomUser(user);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}