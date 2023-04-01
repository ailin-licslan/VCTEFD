package com.lin.licslan.es.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.util.Arrays;
/**
 * @author licslan
 */
@Configuration
public class ESConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.elasticsearch.uris}")
    private String[] uris;

//    private String uris;
    @Override
    public RestHighLevelClient elasticsearchClient() {
        System.out.println("Ip address is "+ Arrays.toString(Arrays.stream(uris).toArray()));


        // 创建多个HttpHost
        HttpHost[] httpHosts = Arrays.stream(uris).map(HttpHost::create).toArray(HttpHost[]::new);

        return new RestHighLevelClient(RestClient.builder(httpHosts));

        //单台机器
//        ClientConfiguration configuration = ClientConfiguration.builder()
//                .connectedTo(uris)
//                .withSocketTimeout(30000)
//                .build();
//        return RestClients.create(configuration).rest();
    }

}
