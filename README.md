spring cloud 微服务框架
主要功能：eureka server为服务注册中心
         config server为配置文件管理中心，所有服务的配置文件都从该服务获取
         zuul server为网关路由层，所有访问都是通过它，然后由zuul进行服务路由到各自微服务中，采用默认的路由规则为轮询
         turbine server为hystrix监控聚合服务，通过它，可以进行各服务监控
         zipkin server为服务链路追踪服务，主要用来查看访问通过各服务的路径和健康状况
         test sidecar server为spring cloud整合非java项目，本文整合的是nodeJs
         user server为node项目，通过test sidecar server 调用
         test server为访问接口
         test producer server为服务提供者
