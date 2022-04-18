package kr.co.log.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.log.security.domain.ResponseDataDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication auth) throws IOException, ServletException {
        clearAuthenticationAttributes(request);
        ObjectMapper mapper = new ObjectMapper();    //JSON 변경용

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setCode(String.valueOf(HttpServletResponse.SC_OK));
        responseDataDTO.setStatus(String.valueOf(HttpServletResponse.SC_OK));

        Map<String, String> items = new HashMap<>();
        responseDataDTO.setItem(items);

        List<String> roleNames = new ArrayList<>();

        auth.getAuthorities().forEach(authority ->
            roleNames.add(authority.getAuthority())
        );


        if (roleNames.contains("ROLE_MEMBER")) {
            responseDataDTO.setUrl("loginSuccess");
        } else {
            responseDataDTO.setUrl("restricted");
        }

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/");
        response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
        response.getWriter().flush();
    }
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

}