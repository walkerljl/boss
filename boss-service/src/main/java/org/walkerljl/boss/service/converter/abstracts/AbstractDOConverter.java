package org.walkerljl.boss.service.converter.abstracts;

import org.walkerljl.boss.common.model.BaseModel;
import org.walkerljl.boss.common.model.core.BaseCoreModel;
import org.walkerljl.boss.common.model.core.BaseStdCoreModel;
import org.walkerljl.boss.common.model.core.enums.StatusIEnum;
import org.walkerljl.boss.common.model.dal.BaseDO;
import org.walkerljl.boss.common.model.dal.BaseStdDO;
import org.walkerljl.boss.sdk.auth.util.StringUtil;
import org.walkerljl.boss.service.converter.ModelConverter;

/**
 *
 * @author xingxun
 */
public abstract class AbstractDOConverter<A extends BaseCoreModel, B extends BaseDO> extends AbstractModelConverter<A, B> implements
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

        if (a instanceof BaseStdCoreModel && b instanceof BaseStdDO) {
            ((BaseStdCoreModel) a).setId(String.valueOf(((BaseStdDO) b).getId()));
            ((BaseStdCoreModel) a).setRemark(((BaseStdDO) b).getRemark());
            ((BaseStdCoreModel) a).setExtInfo(BaseModel.parseMap(((BaseStdDO) b).getExtInfo()));
            ((BaseStdCoreModel) a).setCreator(((BaseStdDO) b).getCreator());
            ((BaseStdCoreModel) a).setCreatedTime(((BaseStdDO) b).getGmtCreate());
            ((BaseStdCoreModel) a).setModifier(((BaseStdDO) b).getModifier());
            ((BaseStdCoreModel) a).setModifiedTime(((BaseStdDO) b).getGmtModified());
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

        if (a instanceof BaseStdCoreModel && b instanceof BaseStdDO) {
            String id = ((BaseStdCoreModel) a).getId();
            ((BaseStdDO) b).setId(StringUtil.isBlank(id) ? null : Long.valueOf(id));
            ((BaseStdDO) b).setRemark(((BaseStdCoreModel) a).getRemark());
            ((BaseStdDO) b).setExtInfo(BaseModel.toJSONString(((BaseStdCoreModel) a).getExtInfo()));
            StatusIEnum status = ((BaseStdCoreModel) a).getStatus();
            ((BaseStdDO) b).setStatus(status == null ? null : status.getCode());
            ((BaseStdDO) b).setCreator(((BaseStdCoreModel) a).getCreator());
            ((BaseStdDO) b).setGmtCreate(((BaseStdCoreModel) a).getCreatedTime());
            ((BaseStdDO) b).setModifier(((BaseStdCoreModel) a).getModifier());
            ((BaseStdDO) b).setGmtModified(((BaseStdCoreModel) a).getModifiedTime());
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