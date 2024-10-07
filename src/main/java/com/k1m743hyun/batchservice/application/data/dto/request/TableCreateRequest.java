package com.k1m743hyun.batchservice.application.data.dto.request;

import lombok.Getter;

@Getter
public class TableCreateRequest {

    private String tableName;

    private String columnType;

    private String columnName;
}
