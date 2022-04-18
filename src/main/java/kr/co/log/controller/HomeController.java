package kr.co.log.controller;


import kr.co.log.domain.MemberVO;
import kr.co.log.dto.ResponseDTO;
import kr.co.log.security.domain.CustomUser;
import kr.co.log.service.HomeService;
import kr.co.log.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HomeController {

    private final HomeService homeService;
    private final MemberService userService;

    public HomeController(HomeService homeService, MemberService userService) {
        this.homeService = homeService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : MemberVO") MemberVO memberVO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
        return "index";
    }

    @GetMapping("/signup/{role}/{usrId}/{usrPw}/{usrNm}")
    public MemberVO createUser(@ModelAttribute MemberVO memberVO) {
        MemberVO memberVO1 = userService.createMember(memberVO);
        return memberVO1;
    }


    @GetMapping("/api/findAll")
    @ResponseBody
    public ResponseEntity<?> findAll(){
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
        log.info(log.getClass().getName());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode("S0001");
        responseDTO.setItem(homeService.findAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
