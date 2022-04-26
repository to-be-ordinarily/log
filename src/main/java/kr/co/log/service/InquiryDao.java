package kr.co.log.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class InquiryDao {

    private SqlSession session;

    /**
     * 1:1 문의 글을 등록한다.
     * @param requestMap 1:1 문의 글 정보
     */
    public void registerPersonal(Map<String, Object> requestMap) {
        session.insert("inquriyMapper.insertPersonal", requestMap);
    }
}
