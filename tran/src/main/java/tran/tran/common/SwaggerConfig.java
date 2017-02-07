package tran.tran.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/
	 * resources/webjars/映射 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试） 重写该方法需要
	 * extends WebMvcConfigurerAdapter
	 * 
	 */

	/**
	 * 可以定义多个组，比如本类中定义把test和demo区分开了 （访问页面就可以看到效果了）
	 *
	 */
	@Bean
	public Docket testApi() {
		return new Docket(DocumentationType.SWAGGER_2)
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("tran.tran.controller")) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build().apiInfo(testApiInfo());
	}

//	@SuppressWarnings("unchecked")
//	@Bean
//	public Docket demoApi() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("demo").genericModelSubstitutes(DeferredResult.class)
//				// .genericModelSubstitutes(ResponseEntity.class)
//				.useDefaultResponseMessages(false).forCodeGeneration(false).pathMapping("/").select()
//				.paths(or(regex("/demo/.*")))// 过滤的接口
//				.build().apiInfo(demoApiInfo());
//	}

	private ApiInfo testApiInfo() {
		ApiInfoBuilder a=new ApiInfoBuilder();
		a.title("Transition Tool");
		a.description("transition tool");
		a.version("0.1");
		a.termsOfServiceUrl("");
		return a.build();
	}
}
