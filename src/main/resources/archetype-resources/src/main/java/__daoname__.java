package com.douzone.comet.service.hr.pamodm.x20173;

import com.douzone.comet.components.DzCometService;
import com.douzone.comet.jdbc.mybatis.DzMybatisSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Repository
public class Pamodm00100_X20173Dao extends DzCometService {

    private final DzMybatisSupport mybatis;

    public Pamodm00100_X20173Dao(DzMybatisSupport mybatis) {
        this.mybatis = mybatis;
    }

    public List<Map<String, Object>> list(Map<String, Object> params) throws Exception {
        return mybatis.selectList(getPath("list"), params);
    }

    public void save(Map<String, Object> params) throws Exception {
        return mybatis.insert(getPath("insert"), params);
    }

    private String getPath(String id) {
        return format("%s.%s", this.getClass().getName(), id);
    }
}
