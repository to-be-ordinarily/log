package kr.co.log.persistence;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RecentDao {

    private SqlSession session;
    /**
     * 최근 본 명당을 조회한다.
     * @param requestMap gpsLat:위도, gpsLng:경도, seqList:최근 본 명당 ID List, limit:게시물 수, offset:페이지
     * @return 조회된 명당 목록을 반환한다.
     */
    public List<Map<String, Object>> getRecentList(Map<String, Object> requestMap) {
        return session.selectList("recentMapper.getRecentList" , requestMap);
    }
}
