package org.walkerljl.boss.service.converter.abstracts;

import org.walkerljl.boss.common.model.core.BaseCoreModel;
import org.walkerljl.boss.common.model.core.BaseStdCoreModel;
import org.walkerljl.boss.common.model.core.enums.StatusIEnum;
import org.walkerljl.boss.common.model.view.BaseStdVO;
import org.walkerljl.boss.common.model.view.BaseVO;
import org.walkerljl.boss.service.converter.ModelConverter;

/**
 * AbstractVOConverter
 *
 * @author xingxun
 */
public abstract class AbstractVOConverter<A extends BaseVO, B extends BaseCoreModel> extends AbstractModelConverter<A, B> implements
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

        if (a instanceof BaseStdVO && b instanceof BaseStdCoreModel) {
            ((BaseStdVO) a).setId(((BaseStdCoreModel) b).getId());
            ((BaseStdVO) a).setRemark(((BaseStdCoreModel) b).getRemark());
            ((BaseStdVO) a).setExtInfo(((BaseStdCoreModel) b).getExtInfo());
            StatusIEnum status = ((BaseStdCoreModel) b).getStatus();
            ((BaseStdVO) a).setStatus(status == null ? null : status.getCode());
            ((BaseStdVO) a).setCreator(((BaseStdCoreModel) b).getCreator());
            ((BaseStdVO) a).setCreatedTime(((BaseStdCoreModel) b).getCreatedTime());
            ((BaseStdVO) a).setModifier(((BaseStdCoreModel) b).getModifier());
            ((BaseStdVO) a).setModifiedTime(((BaseStdCoreModel) b).getModifiedTime());
        }

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

        if (a instanceof BaseStdVO && b instanceof BaseStdCoreModel) {
            ((BaseStdCoreModel) b).setId(((BaseStdVO) a).getId());
            ((BaseStdCoreModel) b).setRemark(((BaseStdVO) a).getRemark());
            ((BaseStdCoreModel) b).setExtInfo(((BaseStdVO) a).getExtInfo());
            ((BaseStdCoreModel) b).setCreator(((BaseStdVO) a).getCreator());
            ((BaseStdCoreModel) b).setCreatedTime(((BaseStdVO) a).getCreatedTime());
            ((BaseStdCoreModel) b).setModifier(((BaseStdVO) a).getModifier());
            ((BaseStdCoreModel) b).setModifiedTime(((BaseStdVO) a).getModifiedTime());
        }

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