package org.walkerljl.boss.service.converter;

import java.util.List;

/**
 * 模型转换器
 *
 * @author xingxun
 */
public interface ModelConverter<A, B> {

    /**
     * 转换成模型A
     *
     * @param b
     * @return
     */
    A toA(B b);

    /**
     * 转换成模型A列表
     *
     * @param bList
     * @return
     */
    List<A> toAList(List<B> bList);

    /**
     * 转换成模型B
     *
     * @param a
     * @return
     */
    B toB(A a);

    /**
     * 转换成模型B列表
     *
     * @param aList
     * @return
     */
    List<B> toBList(List<A> aList);
}