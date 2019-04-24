package com.jfhealthcare.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class Swagger2Config {

    @Bean
    public Docket createRestSystemApi() {
        return createDocker("system","com.jfhealthcare.modules.system.controller",getParamterList());
    }
    
    @Bean
    public Docket createRestBusinessApi() {
        return createDocker("business","com.jfhealthcare.modules.business.controller",getParamterList());
    }
    
    @Bean
    public Docket createRestApply() {
        return createDocker("apply","com.jfhealthcare.modules.apply.controller",getParamterList());
    }
    
    
    @Bean
    public Docket createRestBI() {
        return createDocker("BI","com.jfhealthcare.modules.BI.controller",getParamterList());
    }

    @Bean
    public Docket createRestTeach() {
        return createDocker("teach","com.jfhealthcare.modules.teach.controller",getParamterList());
    }
    
  
    private Docket createDocker(String groupName,String basePackage,List<Parameter> ps) {
    	return new Docket(DocumentationType.SWAGGER_2).groupName(groupName)
                .apiInfo(createDegaultApiInfo())
                .globalOperationParameters(ps)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any()) //过滤的接口
                .build();
    }
    
    private ApiInfo createDegaultApiInfo() {
   	    return createApiInfo("JF RESTful API/九峰开发接口文档 --BI统计API","HTTP状态码大全：http://tool.oschina.net/commons?type=5","程序员  GG","1.0");
   }
    
    private ApiInfo createApiInfo(String title,String description,String contact,String version) {
    	return new ApiInfoBuilder()
                 .title(title) //大标题
                 .description(description) //详细描述
                 .contact(contact) //作者
                 .version(version)
                 .build();
    }
    
    private List<Parameter> getParamterList(){
    	List<Parameter> aParameters = new ArrayList<Parameter>();
	   	aParameters.add(setHeaderToken());
	   	aParameters.add(setHeaderVersion());
	   	return aParameters;
    }
    
    private Parameter setHeaderToken() {
    	ParameterBuilder aParameterBuilder = new ParameterBuilder();
    	aParameterBuilder.name("token").defaultValue("938854e232a4ff23e5de71bc2af7e934")
    	.description("token测试用").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        return aParameterBuilder.build();
    }
    
    private Parameter setHeaderVersion() {
    	ParameterBuilder aParameterBuilder = new ParameterBuilder();
    	aParameterBuilder.name("version").defaultValue("3.5.5.20180830")
    	.description("version测试用").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        return aParameterBuilder.build();
    }
    
}