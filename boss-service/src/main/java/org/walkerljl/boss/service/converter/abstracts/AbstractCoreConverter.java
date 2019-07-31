package org.walkerljl.boss.service.converter.abstracts;

import org.walkerljl.boss.common.model.core.BaseStdCoreModel;
import org.walkerljl.boss.service.converter.ModelConverter;

/**
 * AbstractCoreConverter
 *
 * @author xingxun
 */
public abstract class AbstractCoreConverter<A extends BaseStdCoreModel, B extends BaseStdCoreModel> extends AbstractModelConverter<A, B> implements
        ModelConverter<A, B> {

    @Override
    public A toA(B b) {
        if (b == null) {
            return null;
        }

        A a = toA0(b);
        if (a == null) {
            return null;
        }

        a.setId(b.getId());
        a.setRemark(b.getRemark());
        a.setExtInfo(b.getExtInfo());
        a.setCreator(b.getCreator());
        a.setCreatedTime(b.getCreatedTime());
        a.setModifier(b.getModifier());
        a.setModifiedTime(b.getModifiedTime());
        return a;
    }

    @Override
    public B toB(A a) {
        if (a == null) {
            return null;
        }

        B b = toB0(a);
        if (b == null) {
            return null;
        }

        b.setId(a.getId());
        b.setRemark(a.getRemark());
        b.setExtInfo(a.getExtInfo());
        b.setCreator(a.getCreator());
        b.setCreatedTime(a.getCreatedTime());
        b.setModifier(a.getModifier());
        b.setModifiedTime(a.getModifiedTime());

        return b;
    }

    /**
     * toA0
     *
     * @param b
     * @return
     */
    public abstract A toA0(B b);

    /**
     * toB0
     *
     * @param a
     * @return
     */
    public abstract B toB0(A a);
}