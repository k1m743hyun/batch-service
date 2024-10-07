package com.k1m743hyun.batchservice.domain.data.entity;

import jakarta.persistence.*;

public class DynamicLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String columnName; // 예: 사용자가 입력한 컬럼 이름
    private String columnType; // 예: 사용자가 선택한 데이터 타입

    // Getters and Setters
}
