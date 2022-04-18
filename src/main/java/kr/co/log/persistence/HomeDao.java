package kr.co.log.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class HomeDao {

    private final SqlSession session;
    private static final String nameSpace = "kr.co.log.mapper.HomeMapper";
    public HomeDao(SqlSession session) {
        this.session = session;
    }

    public List<Map<String, String>> findAll(){
        return session.selectList(nameSpace + ".findAll");
    }


}
