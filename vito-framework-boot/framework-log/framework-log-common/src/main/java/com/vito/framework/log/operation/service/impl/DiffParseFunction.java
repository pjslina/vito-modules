package com.vito.framework.log.operation.service.impl;

import com.vito.framework.log.operation.context.LogRecordContext;
import com.vito.framework.log.operation.starter.diff.IDiffItemsToLogContentService;
import com.vito.framework.log.operation.util.diff.ArrayDiffer;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.comparison.ComparisonService;
import de.danielbechler.diff.node.DiffNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author muzhantong
 * create on 2022/2/8 3:44 下午
 */
@Slf4j
public class DiffParseFunction {
    public static final String DIFF_FUNCTION_NAME = "_DIFF";
    public static final String OLD_OBJECT = "_oldObj";

    private static IDiffItemsToLogContentService diffItemsToLogContentService;

    public String functionName() {
        return DIFF_FUNCTION_NAME;
    }

    public String diff(Object source, Object target) {
        if (source == null && target == null) {
            return "";
        }
        if (source == null || target == null) {
            try {
                Class<?> clazz = source == null ? target.getClass() : source.getClass();
                source = source == null ? clazz.getDeclaredConstructor().newInstance() : source;
                target = target == null ? clazz.getDeclaredConstructor().newInstance() : target;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        if (!Objects.equals(AopUtils.getTargetClass(source.getClass()),AopUtils.getTargetClass(target.getClass()))) {
            log.error("diff的两个对象类型不同, source.class={}, target.class={}", source.getClass().toString(), target.getClass().toString());
            return "";
        }
        ObjectDifferBuilder objectDifferBuilder = ObjectDifferBuilder.startBuilding();
        DiffNode diffNode = objectDifferBuilder
                .differs().register((differDispatcher, nodeQueryService) ->
                        new ArrayDiffer(differDispatcher, (ComparisonService) objectDifferBuilder.comparison(), objectDifferBuilder.identity()))
                .comparison().ofType(LocalDateTime.class).toUseEqualsMethod().and()
                .build()
                .compare(target, source);
        return diffItemsToLogContentService.toLogContent(diffNode, source, target);
    }

    public String diff(Object newObj) {
        Object oldObj = LogRecordContext.getVariable(OLD_OBJECT);
        return diff(oldObj, newObj);
    }

    public void setDiffItemsToLogContentService(IDiffItemsToLogContentService diffItemsToLogContentService) {
        DiffParseFunction.diffItemsToLogContentService = diffItemsToLogContentService;
    }
}
