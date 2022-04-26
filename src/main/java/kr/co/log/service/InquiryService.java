package kr.co.log.service;

import kr.co.log.constans.UploadFileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryDao inquiryDao;
    private final FileUploadService fileUploadService;

    @Value("#{config['user.inquiryImg.path']}")
    String inquiryImgPath;

    /**
     * 1:1 문의 등록 서비스 - 파일 업로드 및 문의 글 등록
     * @param requestMap 문의 글 등록 정보
     * @param files 문의 글에 첨부한 이미지
     * @throws IOException 파일 업로드시 에러
     */
    public void registerPersonal(Map<String, Object> requestMap, List<MultipartFile> files) throws IOException {

        // 파일 업로드
        List<String> fileNames = fileUploadService.saveMultiFile(files, inquiryImgPath, UploadFileType.IMG);
        setfileName(requestMap, fileNames);

        inquiryDao.registerPersonal(requestMap);

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

}
