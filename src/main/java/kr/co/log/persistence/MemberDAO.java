package kr.co.log.persistence;

import kr.co.log.domain.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MemberDAO {

    private final SqlSession session;

    public MemberDAO(SqlSession session) {
        this.session = session;
    }

    private static String namespace = "kr.co.myungdangga.mapper.MemberMapper";


    private static final String userNameSpace = "kr.co.log.mapper.UserMapper";

    public String findById(String userId){
        return session.selectOne(userNameSpace + ".findById");
    }

    public MemberVO loginUser(String usrId) {
        return session.selectOne(userNameSpace+".findById", usrId);
    }

    public MemberVO createUser(MemberVO memberVO) {
        session.insert(userNameSpace + ".createUser", memberVO);
        session.insert(userNameSpace + ".createAuth", memberVO);
        return session.selectOne(userNameSpace + ".findById", memberVO);
    }

    public void loginFail(String usrId) {
        session.update(userNameSpace+".loginFail", usrId);
    }
    /*
    // 로그인
    public MemberVO loginManager(String usrId) throws Exception {
        return session.selectOne(namespace+".loginManager", usrId);
    }

    public Map<String, Object> readManager(String usrId) throws Exception {
        return session.selectOne(namespace+".readManager", usrId);
    }

    public void loginFail(String usrId) throws Exception {
        session.update(namespace+".loginFail", usrId);
    }

    // 회원 관리
    public void createWebUser(Map<String, Object> map) throws Exception {
        session.insert(namespace+".createWebUser", map);
    }


    public Map<String, Object> readWebUser(String usrId) throws Exception {
        return session.selectOne(namespace+".readWebUser", usrId);
    }

    public void updateWebUser(Map<String, Object> map) throws Exception {
        session.update(namespace+".updateWebUser", map);
    }

    public void deleteWebUser(Integer seq) throws Exception {
        session.delete(namespace+".deleteWebUser", seq);
    }

    public void createBookmark(Map<String, Object> map) throws Exception {
        session.insert(namespace+".createBookmark", map);
    }

    public List<Map<String, Object>> listBookmarks(String usr_id) throws Exception {
        return session.selectList(namespace+".listBookmarks", usr_id);
    }

    public void deleteBookmark(Map<String, Object> map) throws Exception {
        session.delete(namespace+".deleteBookmark", map);
    }

    // CS 관리
    public void createPersonalInquiry(Map<String, Object> map) throws Exception {
        session.insert(namespace+".createPersonalInquiry", map);
    }

    public Map<String, Object> readPersonalInquiry(Integer seq) throws Exception {
        return session.selectOne(namespace+".readPersonalInquiry", seq);
    }

    public void updatePersonalInquiry(Map<String, Object> map) throws Exception {
        session.update(namespace+".updatePersonalInquiry", map);
    }

    public void deletePersonalInquiry(Integer seq) throws Exception {
        session.delete(namespace+".deletePersonalInquiry", seq);
    }

    public void createReservationInquiry(Map<String, Object> map) throws Exception {
        session.insert(namespace+".createReservationInquiry", map);
    }


    public Map<String, Object> readReservationInquiry(Integer seq) throws Exception {
        return session.selectOne(namespace+".readReservationInquiry", seq);
    }

    public void updateReservationInquiry(Map<String, Object> map) throws Exception {
        session.update(namespace+".updateReservationInquiry", map);
    }

    public void deleteReservationInquiry(Integer seq) throws Exception {
        session.delete(namespace+".deleteReservationInquiry", seq);
    }

    public void createJoinInquiry(Map<String, Object> map) throws Exception {
        session.insert(namespace+".createJoinInquiry", map);
    }

    public Map<String, Object> readJoinInquiry(Integer seq) throws Exception {
        return session.selectOne(namespace+".readJoinInquiry", seq);
    }

    public void updateJoinInquiry(Map<String, Object> map) throws Exception {
        session.update(namespace+".updateJoinInquiry", map);
    }

    public void deleteJoinInquiry(Integer seq) throws Exception {
        session.delete(namespace+".deleteJoinInquiry", seq);
    }

    public Map<String, Object> readNotice(Integer seq) throws Exception {
        return session.selectOne(namespace+".readNotice", seq);
    }

    public void deleteNotice(Integer seq) throws Exception {
        session.delete(namespace+".deleteNotice", seq);
    }

    public void createNoticeAttachedFile(Map<String, Object> map) {
        session.insert(namespace+".createNoticeAttachedFile", map);
    }

    public List<Map<String, Object>> listNoticeAttachedFile(Integer ntcSeq) throws Exception {
        return session.selectList(namespace+".listNoticeAttachedFile", ntcSeq);
    }

    public void deleteNoticeAttachedFile(Integer seq) throws Exception {
        session.delete(namespace+".deleteNoticeAttachedFile", seq);
    }*/
}