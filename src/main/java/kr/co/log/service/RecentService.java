package kr.co.log.service;

import kr.co.log.persistence.RecentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecentService {

    private final RecentDao recentDao;

    /**
     * 명당 검색에서 최근 본 명당 탭을 클릭시 표현될 장지 목록 리스트 중 10개만 표현한다.
     * 회원의 경우 마이페이지의 최근 본 명당에서 나머지 항목을 조회 할 수 있다.
     * @param gpsLat 사용자 위도
     * @param gpsLng 사용자 경도
     * @param seqList 최근 본 명당 업체 아이디 리스트
     * @return 조회된 업체 리스트
     */
    public List<Map<String, Object>> getRecentList(double gpsLat, double gpsLng, List<Integer> seqList) {
        if (seqList == null || seqList.isEmpty()) {
            return List.of();
        }

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gpsLat", gpsLat);
        requestMap.put("gpsLng", gpsLng);
        requestMap.put("seqList", seqList);

        return recentDao.getRecentList(requestMap);
    }
}
