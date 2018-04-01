package org.walkerljl.boss.support.dao;//package org.walkerljl.smart.dao;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//
///**
// * 数据访问层切面
// *
// * @author lijunlin
// */
//public class DaoAspect {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DaoAspect.class);
//
//    /**
//     * 环绕增强
//     */
//    public Object doAspect(ProceedingJoinPoint joinPoint) {
//        long startTime = System.currentTimeMillis();
//        Object returnValue = null;
//        boolean invokedResult = true;
//        try {
//            Object[] args = joinPoint.getArgs();
//            returnValue = joinPoint.proceed(args);
//        } catch (Throwable e) {
//            invokedResult = false;
//            throw new AppDaoException(e.getMessage(), e);
//        } finally {
//            //DEBUG
//            if (LOGGER.isDebugEnabled()) {
//                long cousumeTime = System.currentTimeMillis() - startTime;
//                String clazzName = joinPoint.getTarget().getClass().getName();
//                String invokedMethodName = joinPoint.getSignature().getName();
//                String debugInfo = clazzName + "." + invokedMethodName;
//                LOGGER.debug(String.format("方法[%s]此次调用共耗时:%s毫秒, 结果:%s.",
//                        new Object[]{debugInfo, cousumeTime, (invokedResult ? "成功" : "失败")}));
//            }
//        }
//        return returnValue;
//    }
//}