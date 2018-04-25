spring cloud 微服务框架
=====================================
1：主要功能
------------------------------------
* eureka server为服务注册中心</br>
* config server为配置文件管理中心，所有服务的配置文件都从该服务获取</br>
* zuul server为网关路由层，所有访问都是通过它，然后由zuul进行服务路由到各自微服务中，采用默认的路由规则为轮询</br>
* turbine server为hystrix监控聚合服务，通过它，可以进行各服务监控</br>
* zipkin server为服务链路追踪服务，主要用来查看访问通过各服务的路径和健康状况</br>
* test sidecar server为spring cloud整合非java项目，本文整合的是nodeJs</br>
* user server为node项目，通过test sidecar server 调用</br>
* test server为访问接口</br>
* test producer server为服务提供者</br>

2：端口设置
-------------------------------------------------
* 80xx为基础服务端口
* 90xx为微服务
* 基础服务端口对应 8000=eureka 8010=config 8020=zuul 8030=turbine 8040=zipkin 8050=sidecar
* 微服务端口对应 9000=test 9010=test producer 9020=node user

3：项目设置
----------------------------------------------------
* eureka 
* config 我采用的是加载本地配置文件，通过location config注释下的配置进行设置，需要你根据本地信息进行重新设置。你也可以采用加载远程配置，需要将remote git config注释下的配置信息打开，同时配置参数信息。（注意项目的中的配置文件信息，一个服务会加载三个配置文件，第一个是项目自带的bootstrap.properties，第二个是{spring.application.name}对应的名称的.properties文件，前提是项目启动时不加参数--spring.cloud.config.profile=xxx，第三个配置文件为application.properties）
* zuul 网关的路由设置采用的serviceId方式。test server ratelimit注释下的为网关限流设置,test server hystrix注释下为服务熔断设置，upload file注释下为上传大文件设置，zipkin info注释下为服务链路追踪的设置
* turbine 服务监控聚合服务turbine.appConfig注释下配置
* zipkin

4：项目使用
----------------------------------------------------
* sidecar 依次启动eureka，config，zuul server ,启动test-sidecar-server,再启动user-server，成功启动后再node的后台就可以看到health访问的请求，时间如果没有重新设置为30s。访问localhost:8000，查看服务启动情况。如全部启动成功可POST方式访问http://localhost:8020/user/query_user，参数{"id":1},查看返回结果
* turbine 聚合hystrix项目，进行健康监控。依次启动eureka，config，zuul，再启动test-server和test-producer-server,最后启动turbine-server。浏览器地址输入http://localhost:8030/hystrix 如果成功，可以看到豪猪界面，在监控地址输入localhost:8030/turbine.stream?cluster=TEST监控，成功的话可以看到如下图
![Image text](https://github.com/zhouhongsheng/ckt_repository/blob/master/img_folder/turbine.jpg)
* zipkin 服务链路追踪。依次启动eureka，config，zuul，在启动test-server和test-producer-server，最后启动zipkin-server。浏览器地址输入http://localhost:8040/ 如果成功，可以进入zipkin界面，进行filter后，可以看到如下图
![Image text](https://github.com/zhouhongsheng/ckt_repository/blob/master/img_folder/zipkin.jpg)
* 限流处理，我在zuul加入了限流的处理，存储方式为redis，所以你需要本地打开redis。为了方便测试，目前设置的为url，60s内限流3次，也就是说上面的http://localhost:8020/test/index 接口在60s内只能访问三次，超过后，zuul会报错，错误code为429
* 熔断处理 我在test-server和test-producer-server 中都加入了熔断处理。采用注解@HystrixCommand方式，目前设置为3000ms，也就说，接口超过3000ms后，将直接返回"indexFallback",你可以根据实际情况，修改@HystrixCommand命令。同时在zuul里面也配置了熔断设置，目前设置的为60s
