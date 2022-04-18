package kr.co.log.controller;


import kr.co.log.domain.MemberVO;
import kr.co.log.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
                       "file:src/main/webapp/WEB-INF/spring/dispatcher-servlet.xml",
                       "file:src/main/webapp/WEB-INF/spring/context-security.xml"})
@WebAppConfiguration
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    MemberService memberService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    public void index_anonymous() throws Exception{
        mockMvc.perform(get("/").with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void index_user() throws Exception{
        mockMvc.perform(get("/").with(user("syeol").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void login() throws Exception{
        MemberVO member = createUser();
        mockMvc.perform(formLogin().user(member.getUsrId()).password("test"))
                .andDo(print())
                .andExpect(authenticated());
    }


    @Test
    @Transactional
    public void login_fail() throws Exception{
        MemberVO member = createUser();
        mockMvc.perform(formLogin().user(member.getUsrId()).password("12341"))
                .andDo(print())
                .andExpect(authenticated());
    }

    private MemberVO createUser() {
        MemberVO member = new MemberVO();
        member.setUsrId("test");
        member.setUsrPw("test");
        member.setUsrNm("syeol");
        memberService.createMember(member);
        return member;
    }
}