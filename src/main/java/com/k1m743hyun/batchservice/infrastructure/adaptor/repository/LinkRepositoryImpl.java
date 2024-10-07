package com.k1m743hyun.batchservice.infrastructure.adaptor.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
import com.k1m743hyun.batchservice.application.data.dto.request.TableCreateRequest;
import com.k1m743hyun.batchservice.domain.port.repository.LinkRepository;
import com.k1m743hyun.batchservice.infrastructure.provider.mybatis.mapper.LinkMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LinkRepositoryImpl{

    //
    //private final LinkMapper linkMapper;
    //
    //public void createTable(TableCreateRequest request) {
    //
    //    String sql = "CREATE TABLE TBL_" + request.getTableName();
    //    sql = sql + "(boardId number primary key)";
    //
    //    HashMap map = new HashMap();
    //    map.put("sql", sql);
    //
    //    SqlSession ss = openSession();
    //    SqlSession session = linkMapper.openSession();
    //    session.update("createTable", map);
    //}
}
