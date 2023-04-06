package com.fqyc.demo.util;

import java.math.BigInteger;
import java.net.InetAddress;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UUIDUtil {
    private static AtomicLong incrementer = new AtomicLong(0L);
    private static Long workerId = Long.valueOf(0L);

    static {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();
            if ((ip != null) && (ip.contains("."))) {
                String[] ips = ip.split("\\.");
                if (ips.length == 4) {
                    workerId = Long.valueOf(ips[3]);
                }
            }
        } catch (Throwable localThrowable) {
        }
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Long generateSnowflakeId() {
        long curTimestamp = System.currentTimeMillis();
        long sequenceMask = 4095L;
        if (incrementer.compareAndSet(sequenceMask, 0L)) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            curTimestamp = System.currentTimeMillis();
        }
        return Long.valueOf(0x0 | curTimestamp << 20 | incrementer

                .incrementAndGet() << 8 | workerId
                .longValue());
    }

    public static String generateShortId() {
        return new BigInteger(generateSnowflakeId() + "", 10).toString(36);
    }
}