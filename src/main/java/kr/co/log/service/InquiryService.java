package kr.co.log.service;

import kr.co.log.persistence.InquiryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryDao dao;

//    @Value("#{config['user.inquiryImg.path']}")
//    String inquiryImgPath;

    /**
     * 1:1 문의 등록 서비스 - 파일 업로드 및 문의 글 등록
     * @param requestMap 문의 글 등록 정보
     * @param files 문의 글에 첨부한 이미지
     * @throws IOException 파일 업로드시 에러
     */
    public void registerPersonal(Map<String, Object> requestMap, List<MultipartFile> files) throws IOException {

        // 파일 업로드
        //List<String> fileNames = fileUploadService.saveMultiFile(files, inquiryImgPath, UploadFileType.IMG);
        //setfileName(requestMap, fileNames);

        dao.registerPersonal(requestMap);

    }

    /**
     * 1:1 문의
     * @param requestMap usrId : 사용자 ID(필수), index: 페이지당 게시물 게수(선택), offset: 페이지(선택)
     * @return 사용자에 따른 1:1 문의 게수물 정보 및 총 건수
     */
    public Map<String, Object> getPersonalInquiry(Map<String, Object> requestMap){

        List<Map<String, Object>> inquiryList = dao.selectPersonalInquiry(requestMap);

        String usrId = "";
        Integer totalCount = dao.selectTotalCount(usrId);
        Map<String, Object> responseMap = new HashMap<>();

        responseMap.put("inquiryList", inquiryList);
        responseMap.put("totalCount", totalCount);

        // 디버깅필요
        /*for (Map<String, Object> pi : result) {
            for (int i = 1; i < 4; i++) {
                String ip = (String) pi.get("imgPath" + i);
                if (ip == null) {
                    continue;
                }

                pi.put("imgPath" + i, ip);
            }
        }*/

        return responseMap;
    }

    /**
     * 파일 입력 순서대로 파일 경로를 넣어준다
     * @param requestMap 파일 경로를 정의할 Map
     * @param files 파일 명 List
     */
    private void setfileName(Map<String, Object> requestMap, List<String> files) {

        requestMap.put("imgPath1", files.get(0));
        requestMap.put("imgPath2", files.get(1));
        requestMap.put("imgPath3", files.get(2));

    }

    /**
     * 입점 문의 등록
     * @param requestMap 입점 문의 정보
     */
    public void registerJoin(Map<String, Object> requestMap) {
        Integer insertResult = dao.insertJoin(requestMap);
        if(insertResult != 1){
            throw new IllegalArgumentException("에러");
        }
    }

    /**
     * 예약 문의 등록
     * @param requestMap 예약 문의 정보
     */
    public void registerReservation(Map<String, Object> requestMap) {
        Integer insertReservation = dao.insertReservation(requestMap);
        if (insertReservation != 1) {
            throw new IllegalArgumentException("에러");
        }
    }

}
