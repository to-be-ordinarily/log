package kr.co.log.service;

import kr.co.log.persistence.HomeDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeService {

    private final HomeDao homeDao;

    public HomeService(HomeDao homeDao) {
        this.homeDao = homeDao;
    }

    public List<Map<String, String>> findAll(){
        return homeDao.findAll();
    }


}
