package com.gbqd.zuul.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrWen
 * @create 2019-01-16-16:51
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("订单模块", "/myServerOrder/v2/api-docs", "2.0"));
        resources.add(swaggerResource("用户信息和支付模块", "/myServerMember/v2/api-docs", "2.0"));
        resources.add(swaggerResource("店铺信息和商品模块", "/myServerGoods/v2/api-docs", "2.0"));
        return resources;
    }


    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }


}
