### redis
* 使用Redisson，官网：https://github.com/redisson/redisson

### 分布式锁
方式一：
```java
@Autowired
private DistributedLock distributedLock; 

public void businessMethod() {

  String key = "order_key";
  DistributedLockWrapper lock = null;
  try {
    //获取锁 
    lock = distributedLock.lock(key);

    //业务逻辑

  } catch (Exception e) {
    logger.error("获取分布式锁失败", e);
  } finally {
    //释放锁
    if(lock != null) {
      try {
        lock.close();
      } catch (Exception e) {
        logger.error("释放分布式锁失败", e);
      }
    }
  }

}
```
方式二，使用try-with-resources自动释放锁(推荐)
```java
@Autowired
private DistributedLock distributedLock;

public void businessMethod() {

  String key = "order_key";
  try(DistributedLockWrapper lock = distributedLock.lock(key)) {
    
    //业务逻辑
    
  } catch (Exception e) {
    logger.error("获取分布式锁失败", e);
  } 

}
```
使用tryLock方法，获取锁失败时，不会阻塞，而是直接返回false
```java
@Autowired
private DistributedLock distributedLock;

public void businessMethod() {

  String key = "order_key";

  try(DistributedLockWrapper lock = distributedLock.tryLock(key, 10, TimeUnit.SECONDS)) {

    if(lock == null) {
      // 获取锁失败
      return; 
    }

    // 业务逻辑

  } catch (Exception e) {

    logger.error("获取分布式锁失败", e);

  }

}
```
