//package org.walkerljl.boss.service;
//
//import org.junit.Test;
//import org.walkerljl.commons.util.HttpUtils;
//
//import java.net.InetAddress;
//
///**
// * HttpUtilsTest
// *
// * @author lijunlin
// */
//public class HttpUtilsTest {
//
//    @Test
//    public void test() throws Exception {
//        String host = "www.chat1.jd.com";
//        System.out.println(InetAddress.getByName(host).getHostAddress());
//        //DnsCacheManipulator.setDnsCache(host, "10.191.164.157");
//        System.out.println(InetAddress.getByName(host).getHostAddress());
//
//        System.out.println(
//                new HttpUtils(
//                        "http://chat1.jd.com/api/checkChat?callback=jQuery2930209&pid=1324380799&_=1474942906343"
//                ).execute()
//        );
//    }
//}
