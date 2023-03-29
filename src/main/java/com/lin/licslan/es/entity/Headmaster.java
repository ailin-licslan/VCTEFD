package com.lin.licslan.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;
import java.util.List;

/*
 * 班主任
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "headmaster", replicas = 1, shards = 1, createIndex = true)
public class Headmaster {

    @Field(index = true, store = false, type = FieldType.Keyword)
    private String hId;

    @Field(index = true, store = false, type = FieldType.Keyword)
    private String hName;

    @Field(index = true, store = false, type = FieldType.Keyword)
    private String hAddress;

    @Field(index = false, store = false, type = FieldType.Integer)
    private Integer hSalary;

    @Field(index = false, store = false, type = FieldType.Keyword)
    private List<String> hClass;

    @Field(index = true, store = true, type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date  hCreateTime;
}

