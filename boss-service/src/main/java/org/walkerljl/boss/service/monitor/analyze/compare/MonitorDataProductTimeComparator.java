package org.walkerljl.boss.service.monitor.analyze.compare;

import java.util.Comparator;

import org.walkerljl.boss.service.monitor.model.MonitorData;

/**
 * MonitorDataProductTimeComparator
 *
 * @author xingxun
 */
public class MonitorDataProductTimeComparator implements Comparator<MonitorData> {

    @Override
    public int compare(MonitorData o1, MonitorData o2) {
        if (o1.getTime().getTime() > o2.getTime().getTime()) {
            return -1;
        } else if (o1.getTime().getTime() == o2.getTime().getTime()) {
            return 0;
        } else {
            return 1;
        }
    }
}