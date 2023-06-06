package com.vito.framework.log.operation.starter.diff;

import de.danielbechler.diff.node.DiffNode;

/**
 * @author muzhantong
 * create on 2022/1/3 8:26 下午
 */
public interface IDiffItemsToLogContentService {

    /**
     * convert diffNode to log content
     * @param diffNode diffNode
     * @param o1 o1
     * @param o2 o2
     * @return log content
     */
    String toLogContent(DiffNode diffNode, final Object o1, final Object o2);
}
