package com.k1m743hyun.batchservice.domain.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.k1m743hyun.batchservice.application.data.dto.request.TableCreateRequest;
import com.k1m743hyun.batchservice.domain.data.entity.DynamicLinkEntity;

public interface LinkRepository extends JpaRepository<DynamicLinkEntity, Long> {

    void createTable(TableCreateRequest request);
}