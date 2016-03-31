package org.ionnic.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author apple
 *
 */
public class SystemUtils {

    /**
     * 获取本机的所有ipv4地址
     *
     * @return
     */
    public static List<InetAddress> getAllAddresses() {
        try {
            List<InetAddress> list = new ArrayList<InetAddress>();
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                for (InetAddress addr : Collections.list(netint.getInetAddresses())) {
                    if (!addr.isLoopbackAddress() && addr instanceof Inet4Address) {
                        list.add(addr);
                    }
                }
            }
            return list;
        } catch (SocketException e) {
            return new ArrayList<InetAddress>(0);
        }
    }

    /**
     * 获取本机第一个非回环ipv4地址
     *
     * @return
     */
    public static InetAddress getFirstNonLoopAddress() {
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                for (InetAddress addr : Collections.list(netint.getInetAddresses())) {
                    if (!addr.isLoopbackAddress() && addr instanceof Inet4Address)
                        return addr;
                }
            }
        } catch (SocketException e) {
        }
        return null;
    }

    /**
     * 获取当前进程ID
     *
     * @return
     */
    public static int getPID() {
        // 8368@whj-desktop -> 8386
        String rtName = ManagementFactory.getRuntimeMXBean().getName();
        int index = rtName.indexOf('@');
        if (index != -1) {
            return Integer.parseInt(rtName.substring(0, index));
        }
        return -99;
    }

    /**
     * 检查端口是否可用
     *
     * @param port
     * @return
     */
    public static boolean isPortAvailable( int port ) {
        // MIN_PORT_NUMBER:0 MAX_PORT_NUMBER:65535
        if (port < 0 || port > 65535)
            throw new IllegalArgumentException("Invalid start port: " + port);

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null)
                ds.close();

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                }
            }
        }
        return false;
    }

    /**
     * 打印内存使用信息
     *
     * @param pw
     */
    public static void printMemInfo( PrintWriter pw ) {
        Runtime rt = Runtime.getRuntime();
        pw.println("Total Memory: " + rt.totalMemory() / 1024 / 1024 + "m");
        pw.println("Max Memory:   " + rt.maxMemory() / 1024 / 1024 + "m");
        pw.println("Free Memory:  " + rt.freeMemory() / 1024 / 1024 + "m");
    }

    /**
     * 打印线程使用信息
     *
     * @param pw
     */
    public static void printThreadInfo( PrintWriter pw ) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        pw.println("Peak Thread Count:    " + threadMXBean.getPeakThreadCount());
        pw.println("Current Thread Count: " + threadMXBean.getThreadCount());
    }

}