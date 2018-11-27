package org.walkerljl.boss.service.monitor.analyze.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.common.util.DateUtil;
import org.walkerljl.boss.service.monitor.model.MonitorData;

/**
 * MonitorDataProductTimeComparatorTest
 *
 * @author xingxun
 */
public class MonitorDataProductTimeComparatorTest extends BaseMonitorUnitTest {

    @Test
    public void test() {

        List<MonitorData> monitorDatas = new ArrayList<>(2);
        MonitorData monitorData = new MonitorData();
        monitorDatas.add(monitorData);
        monitorData.setId("1");
        monitorData.setTime(new Date());

        MonitorData monitorData2 = new MonitorData();
        monitorDatas.add(monitorData2);
        monitorData2.setId("1");
        monitorData2.setTime(DateUtil.modifyTime(new Date(), TimeUnit.DAYS, 1));

        Collections.sort(monitorDatas, new MonitorDataProductTimeComparator());
        MonitorData actual = monitorDatas.get(0);
        Assert.assertEquals(monitorData2.getId(), actual.getId());
        Assert.assertEquals(monitorData2.getTime(), actual.getTime());

        MonitorData monitorData3 = new MonitorData();
        monitorDatas.add(monitorData3);
        monitorData3.setId("1");
        monitorData3.setTime(monitorData2.getTime());
        Collections.sort(monitorDatas, new MonitorDataProductTimeComparator());
    }
}