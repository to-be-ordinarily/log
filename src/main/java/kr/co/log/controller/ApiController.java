package kr.co.log.controller;

import kr.co.log.service.InquiryService;
import kr.co.log.service.RecentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ApiController {

    private final RecentService recentService;
    private final InquiryService inquiryService;


    /**
     * 명당 검색에서 최근 본 명당 탭을 클릭시 LocalStorage 의 최근 본 명당 중 10개만 표현한다.
     * 회원의 경우 마이페이지의 최근 본 명당에서 나머지 항목을 조회 할 수 있다.
     * @param gpsLat 사용자 위도
     * @param gpsLng 사용자 경도
     * @param seqList 최근 본 명당 업체 아이디 //TODO: 나중에 변경 가능성 있음 seq를 사용하지 않기로 하였기 때문
     * @return
     * @throws Exception
     */
    @GetMapping(value = "recentList")
    @ResponseBody
    public List<Map<String, Object>>  getRecentList(@RequestParam double gpsLat, @RequestParam double gpsLng, @RequestParam List<Integer> seqList) throws Exception {
        return recentService.getRecentList(gpsLat, gpsLng, seqList);
    }

    /**
     * 1:1 문의 등록
     * @param requestMap 1:1문의를 위한 기본 정보
     * @param files 1:1 문의에서 업로드한 파일
     * @return 1:1 문의 등록 결과
     */
    @PostMapping(value = "personalInquiry")
    @ResponseBody
    public ResponseEntity<String> makePersonalInquiry(@RequestParam Map<String, Object> requestMap, @RequestPart List<MultipartFile> files){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            inquiryService.registerPersonal(requestMap, files);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("오류가 발생했습니다", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("1:1문의를 신청했습니다", responseHeaders, HttpStatus.OK);
    }

}