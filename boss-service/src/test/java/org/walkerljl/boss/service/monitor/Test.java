package org.walkerljl.boss.service.monitor;

import java.util.HashMap;
import java.util.Map;

import org.walkerljl.boss.common.util.JSONUtil;

/**
 *
 * @author xingxun
 */
public class Test {

    @org.junit.Test
    public void test() {

        Map<String, String> extInfo = new HashMap<>(1);
        extInfo.put("bizCode", "/rabbit/component/mobile/v2/layout.htm?scene=ieye_kpi_detail&extra={\"dim\":\"ltmct\",\"\"dimKey\":\"dimCodes\",\"tableCode\":\"kpi\",\"cardId\":\"2018072300000002\",\"dataExploreId\":\"DE85150\",\"x\":\"thedate\",\"y\":\"trd_mct_cnt_1d\",\"dsName\":\"zsearch_adm_ddm_app_mct_idx_operation_kpi_ltmct_offline_pay_dd_faa31536369026003\",\"name\":\"长尾日动销商家\",\"val\":\"trd_mct_cnt_1d\",\"diw\":\"trd_mct_cnt_1d_diw\",\"dod\":\"trd_mct_cnt_1d_dod\"}");

        System.out.println(JSONUtil.toJSONString(extInfo));

    }
}