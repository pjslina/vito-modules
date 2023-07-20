package com.vito.framework.redis.lock;

import cn.hutool.core.text.CharSequenceUtil;
import com.vito.framework.exception.SysErrorCodeEnum;
import com.vito.framework.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Optional;

/**
 * @author panjin
 */
@Slf4j
@Aspect
public class LockAspect {

    @Autowired(required = false)
    private DistributedLock locker;

    /**
     * 用于SpEL表达式解析.
     */
    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    @Around("@within(lock) || @annotation(lock)")
    public Object aroundLock(ProceedingJoinPoint point, Lock lock) throws Throwable {
        if (lock == null) {
            // 获取类上的注解
            lock = point.getTarget().getClass().getDeclaredAnnotation(Lock.class);
        }
        String lockKey = lock.key();
        if (locker == null) {
            throw new SysException(SysErrorCodeEnum.LOCKER_IS_NULL);
        }
        if (CharSequenceUtil.isEmpty(lockKey)) {
            throw new SysException(SysErrorCodeEnum.LOCK_KEY_IS_NULL);
        }

        if (lockKey.contains("#")) {
            MethodSignature methodSignature = (MethodSignature)point.getSignature();
            //获取方法参数值
            Object[] args = point.getArgs();
            lockKey = getValueBySpelExpression(lockKey, methodSignature, args);
        }
        DistributedLockWrapper lockObj = null;
        try {
            //加锁
            if (lock.waitTime() > 0) {
                lockObj = locker.tryLock(lockKey, lock.waitTime(), lock.leaseTime(), lock.unit(), lock.isFair());
            } else {
                lockObj = locker.lock(lockKey, lock.leaseTime(), lock.unit(), lock.isFair());
            }

            if (lockObj != null) {
                return point.proceed();
            } else {
                throw new SysException(SysErrorCodeEnum.LOCK_WAIT_TIMEOUT);
            }
        } finally {
            if (lockObj != null) {
                locker.unlock(lockObj);
            }
        }
    }

    /**
     * 解析spEL表达式
     */
    private String getValueBySpelExpression(String spelExpression, MethodSignature methodSignature, Object[] args) {
        //获取方法形参名数组
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        if (paramNames != null && paramNames.length > 0) {
            Expression expression = spelExpressionParser.parseExpression(spelExpression);
            // Spring的表达式上下文对象
            EvaluationContext context = new StandardEvaluationContext();
            // 给上下文赋值
            for(int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            // 使用Optional封装返回值
            Optional<Object> resultOptional = Optional.ofNullable(expression)
                    .map(e -> e.getValue(context));

            return resultOptional.map(Object::toString).orElse(null);
        }
        return null;
    }
}
