package kr.co.log.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.log.persistence.MemberDAO;
import kr.co.log.security.domain.ResponseDataDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final MemberDAO dao;

    public CustomLoginFailureHandler(MemberDAO dao) {
        this.dao = dao;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        ObjectMapper mapper = new ObjectMapper(); // JSON 변경용

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setCode("444");
        responseDataDTO.setStatus("444");
        if (exception instanceof AuthenticationServiceException) {
            responseDataDTO.setMessage("아이디 또는 비밀번호가 맞지 않습니다.");
        } else if (exception instanceof BadCredentialsException) {
            responseDataDTO.setMessage("아이디 또는 비밀번호가 맞지 않습니다.");
        } else if (exception instanceof LockedException) {
            responseDataDTO.setMessage("비밀번호 오류횟수가 초과되어 계정이 잠겼습니다.");
        } else if (exception instanceof DisabledException) {
            responseDataDTO.setMessage("사용중지된 회원입니다.");
        } else if (exception instanceof AccountExpiredException){
            responseDataDTO.setMessage("만료된 계정입니다.");
        }else {
            responseDataDTO.setMessage("알수 없는 오류가 발생했습니다.");
        }

        Map<String, String> items = new HashMap<String, String>();
        responseDataDTO.setItem(items);

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
        response.getWriter().flush();
    }

}