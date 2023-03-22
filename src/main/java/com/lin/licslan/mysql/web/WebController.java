package com.lin.licslan.mysql.web;


import cn.hutool.json.JSONUtil;
import com.lin.licslan.mysql.entity.User;
import com.lin.licslan.mysql.service.CrudService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class WebController {

    private final CrudService curdService;

    public WebController(CrudService curdService) {
        this.curdService = curdService;
    }

    @GetMapping("/users")
    public String getAll(){
        return JSONUtil.toJsonStr(curdService.findAll());
    }

    @GetMapping("/{id}")
    public String getAll(@PathVariable("id") Long id){
        User one = curdService.findOne(id);
        return JSONUtil.toJsonStr(one==null?"no data!":one);
    }


    @DeleteMapping("/{id}")
    public String del(@PathVariable("id") Long id){
        Boolean res = curdService.delOne(id);
        return JSONUtil.toJsonStr(res);
    }
}
