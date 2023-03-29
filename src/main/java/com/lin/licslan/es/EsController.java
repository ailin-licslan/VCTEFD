package com.lin.licslan.es;

import cn.hutool.json.JSONUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.lin.licslan.es.entity.ArchitectureDto;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EsController {

    //https://blog.csdn.net/m0_37294838/article/details/127235741


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @GetMapping("/createIndex")
    public String createIndex() throws IOException {
        // 创建连接
        RestClient restClient = RestClient.builder(
                new HttpHost("192.168.58.128", 9201)).build();
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);
        // 创建索引
        CreateIndexResponse createIndexResponse = client.indices().create(c -> c.index("licslan"));
        // 打印结果
        System.out.println(createIndexResponse.acknowledged());
        // 关闭连接
        transport.close();
        restClient.close();
        return "ok";
    }

    @GetMapping("/createIndex2")
    public String test() throws IOException {
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId("100");
        architectureDto.setCity("wh");
        architectureDto.setArea("hub");
        architectureDto.setName("lin");
        // 创建好index请求
        IndexRequest indexRequest = new IndexRequest("lining");
        // 设置索引
        indexRequest.id("5");
        // 设置超时时间（默认）
        indexRequest.timeout(TimeValue.timeValueSeconds(5));
        // 往请求中添加数据
        indexRequest.source(JSONUtil.toJsonStr(architectureDto), XContentType.JSON);
        //执行添加请求
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
        return "ok!";
    }

}
