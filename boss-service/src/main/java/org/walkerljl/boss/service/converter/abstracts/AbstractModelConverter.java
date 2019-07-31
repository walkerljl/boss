package org.walkerljl.boss.service.converter.abstracts;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.service.converter.ModelConverter;

/**
 * AbstractModelConverter
 *
 * @author xingxun
 */
public abstract class AbstractModelConverter<A, B> implements ModelConverter<A, B> {

    @Override
    public List<A> toAList(List<B> bList) {
        if (CollectionUtil.isEmpty(bList)) {
            return null;
        }

        List<A> aList = new ArrayList<>(bList.size());
        for (B b : bList) {
            A a = toA(b);
            if (a == null) {
                continue;
            }
            aList.add(a);
        }
        return aList;
    }

    @Override
    public List<B> toBList(List<A> aList) {
        if (CollectionUtil.isEmpty(aList)) {
            return null;
        }

        List<B> bList = new ArrayList<>(aList.size());
        for (A a : aList) {
            B b = toB(a);
            if (b == null) {
                continue;
            }
            bList.add(b);
        }
        return bList;
    }
}