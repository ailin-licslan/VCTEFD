package com.lin.licslan.es;

import cn.hutool.json.JSONUtil;
import com.lin.licslan.es.entity.ArchitectureDto;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
/**
 * @author licslan
 */
@RestController
public class EsController {

    //参考文档有
    //https://blog.csdn.net/m0_37294838/article/details/127235741
    //https://www.yii666.com/blog/16712.html
    //https://blog.csdn.net/weixin_44335140/article/details/121162331

    private final RestHighLevelClient restHighLevelClient;

    public EsController(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    /**
     * 注意为了方便测试 直接浏览器测试效果 所有接口均以get请求来测试哈
     * */

    @GetMapping("/createIndex")
    public String addIndex(@RequestParam("index") String index) throws IOException {
        ArchitectureDto architectureDto = new ArchitectureDto();
        architectureDto.setId("100");
        architectureDto.setCity("wh");
        architectureDto.setArea("hub");
        architectureDto.setName("lin");
        // 创建好index请求
        IndexRequest indexRequest = new IndexRequest(index);
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



    @GetMapping("/deleteIndex")
    public String deleteIndex(@RequestParam("index") String index) throws IOException {
        // 获取索引客户端
        IndicesClient indicesClient = restHighLevelClient.indices();
        // 创建删除索引的请求
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        // 创建执行删除索引的请求
        // RequestOptions.DEFAULT 指定名称的配置项
        AcknowledgedResponse response = indicesClient.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        // 索引是否创建成功
        boolean acknowledged = response.isAcknowledged();
        System.out.println("delete success or not : " + acknowledged);
        return "success.";
    }


    @GetMapping("/getIndex")
    public String getIndex() throws IOException {
        // 获取索引客户端
        IndicesClient indicesClient = restHighLevelClient.indices();
        // 创建获取索引的请求对象
        // 当索引不存在的时候，就会抛出一个异常: ElasticsearchStatusException
        GetIndexRequest getIndexRequest = new GetIndexRequest("lining");
        // 执行请求
        // RequestOptions.DEFAULT 指定名称的配置项
        GetIndexResponse response = indicesClient.get(getIndexRequest, RequestOptions.DEFAULT);
        // 处理响应信息
        String[] indices = response.getIndices();
        for (String index : indices) {
            System.out.println("The index name is : " + index);
        }
        return "success.";
    }


    @GetMapping("/existsIndex")
    public String existsIndex(@RequestParam("index")String index) throws IOException {
        // 获取索引客户端
        IndicesClient indicesClient = restHighLevelClient.indices();
        // 创建获取索引的请求对象
        // 当索引不存在的时候，就会抛出一个异常: ElasticsearchStatusException
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        // 执行请求
        // RequestOptions.DEFAULT 指定名称的配置项
        boolean exists = indicesClient.exists(getIndexRequest, RequestOptions.DEFAULT);
        if (exists) {
            // 索引都存在
            return "index exists.";
        }
        // 索引不存在
        return "index does not exists.";
    }


}
