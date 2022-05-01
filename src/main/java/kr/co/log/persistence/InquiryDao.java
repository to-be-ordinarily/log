package kr.co.log.persistence;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        session.insert("inquiryMapper.insertPersonal", requestMap);
    }

    public List<Map<String, Object>> selectPersonalInquiry(Map<String, Object> requestMap) {
        return session.selectList("inquiryMapper.selectPersonalInquiry", requestMap);
    }

    public int selectTotalCount(String usrId) {
        return session.selectOne("inquiryMapper.selectTotalCount", usrId);
    }

    public Integer insertJoin(Map<String, Object> requestMap) {
        return session.insert("inquiryMapper.insertJoin", requestMap);
    }
}
