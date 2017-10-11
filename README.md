spring cloud 
======
1:config server 将config_files目录下的配置文件拷贝到对应spring.cloud.config.server.native.searchLocations指定的目录下，也可以用远程git仓库的方式，将注释打开<br/>
2:调用test-server服务index接口http://localhost:8020/test/index<br/>
3:turbine方法url为http://localhost:9000/hystrix,在监控url中输入localhost:8030/turbine.stream?cluster=TEST<br/>
