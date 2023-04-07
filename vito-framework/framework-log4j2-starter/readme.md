##配置项说明
* \<property\> ：定义变量，供xml中其他配置读取
* ${sys:spring.application.name:-log-demo} ： 从系统参数(JVM -D参数)中读取spring.application.name参数，如果没读取到，默认取 log-demo（前面的”-“是取默认值的写法）
* STDOUT_PATTERN： 控制台日志格式，如果后面不加%throwable，会自动加%xThrowable。后者会计算location，即includeLocation="false" 会失效。
* \<Select\>+\<SystemPropertyArbiter\>： 条件匹配，从系统参数获取env，如果-Denv=prod，认为服务是启动在生产环境，不打印控制台日志（\<Null name="STDOUT"/\>）。 如果有多个环境不需要控制台打印，多配几个SystemPropertyArbiter标签即可
* \<RollingRandomAccessFile\>：打印日志到滚动文件，filePattern是日志输出的路径和文件名，后缀.%d{yyyyMMddHH} 对应到小时。输出到日志文件的格式一般选择使用json格式，对应日志中心采集的格式。（其中%X{traceId} 是从当前线程ThreadLocal中取traceId，一般是配合调用链使用）
* \<Policies\> ： 日志文件滚动策略，TimeBasedTriggeringPolicy interval="1" 配合filePattern，就是每小时生成一个日志文件。 CronTriggeringPolicy 辅助检查日志文件是否生成
* \<DirectWriteRolloverStrategy\> ： 日志直接写入文件，Delete标签用于删除历史文件，避免日志文件太多磁盘打爆。
* \<AsyncRoot\> ： 全局异常日志打印配置，高性能必选，需要disruptor依赖。includeLocation="false" 不打印位置信息，打印位置信息会产生很大的开销，尤其对于异步日志的性能影响很大。

##注意
* 通过调试发现log4j2默认不支持读取spring:xxx的变量，需要通过扩展Lookup实现，其Environment对象通过SmartApplicationListener设置到SpringContext静态类中
