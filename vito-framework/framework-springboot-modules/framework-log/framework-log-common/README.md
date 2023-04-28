### 日志公共服务
基于springboot工程

### 已完成功能
* 审计日志：  
默认不开启，目前支持logger/db类型，后续再扩展/redis/es  
如何使用：配置vito.audit-log.enabled=true  
使用Spring Expression Language表达式解析方法上的参数  
功能优化还可以参考：https://github.com/mouzt/mzt-biz-log

### 待完成功能
* 日志埋点