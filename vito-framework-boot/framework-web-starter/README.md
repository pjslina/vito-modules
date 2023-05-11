### springboot-web项目公共包

* 引入日志包，logback和log4j2二选一
* 统一异常拦截器


### 全局异常说明
* 使用标注了ControllerAdvice的多个类处理不同的异常，如将grpc的异常放在一个类，Web异常放在一个类，兜底的异常放在一个类
* 使用Order注解来控制异常处理的顺序，越小越先处理