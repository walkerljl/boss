package org.walkerljl.boss.service.monitor.analyze.abstracts;

import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.Analyzer;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;

/**
 * AbstractAnalyzerTest
 *
 * @author xingxun
 */
public class AbstractAnalyzerTest extends BaseMonitorUnitTest {

    @Test
    public void analyze() {

        Analyzer analyzer = new AbstractAnalyzer() {

            @Override
            protected void handle0(TaskExecutionContext context) {

            }

            @Override
            public void analyze(AnalyzeContext context) {

            }
        };

        analyzer.analyze(null);
    }
}