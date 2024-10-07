package com.k1m743hyun.batchservice.application.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.k1m743hyun.batchservice.application.data.dto.request.TableCreateRequest;
import com.k1m743hyun.batchservice.domain.port.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LinkService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createTable(TableCreateRequest request) {

        // SQL 쿼리로 테이블 생성
        String sql = String.format("CREATE TABLE %s (%s %s)", request.getTableName(), request.getColumnName(), request.getColumnType());
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}
