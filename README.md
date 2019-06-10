# jfly

#### 项目介绍
jfinal实现的redbbs版本代码，项目结构经过使用多年jfinal后反复斟酌形成，有需要可借鉴；
适合初学jfinal 的朋友参考使用，也可作为构建jfinal项目基础代码结构 进行自己的项目开发
预览地址：[http://1216.top](http://1216.top)

#### 安装教程
安装前需要准备：  
```
Java运行环境 (必须)
Mysql数据库（必须，数据存贮使用的mysql，必须配置mysql服务）
Redis数据库（非必须，如没有redis数据库，直接注释掉代码中的redis插件）
安装maven （非必须，如果使用idea可使用idea里面自带的maven）
开发工具IDE（非必须）
```

1. 代码获取  
如果自己本地已安装了git，建议直接使用git拉取，
如果未安装git，代码列表右上方的`克隆/下载` 点击下载`zip`包，解压即可
2. 导入到开发工具IDE(这里我就默认使用者都是Java相关的开发人员)
3. 找到数据库文件并把数据导入到自己的mysql数据库
数据库文件地址:`jfly/src/main/resources/db/jfly.sql`  
4. 修改项目配置：
`jfly/src/main/resources/config.properties`
修改配置中的mysql的配置、redis配置
```
#------------------------------------------------#
jdbcUrl=jdbc\:mysql\://127.0.0.1\:3306/jfly?useUnicode\=true&characterEncoding\=utf8&useSSL\=false
user=guest
password=hello

redis.cache_name=jfly
redis.database=1
redis.host=127.0.0.1
redis.password=hello
redis.port=6379
redis.timeout=5000
```
5. 运行  
可直接运行 `FlyConfi.main()` 即可启动，
6. 访问应用
`http://localhost:80` (更多启动方式，端口配置，可查询 [jfinal-undertow](https://gitee.com/jfinal/jfinal-undertow))

#### 使用说明
1. 直接在这个的基础上做自己简单的社区开发
2. 删除多余代码，作为jfinal开发项目的 基础工程结构
3. jfinal新上手 修修改改练手用
