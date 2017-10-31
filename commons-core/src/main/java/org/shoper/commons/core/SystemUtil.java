package org.shoper.commons.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * System Utilities
 */
public class SystemUtil {
    private static String PID = "pid";

    /**
     * get application process number.
     *
     * @return pid
     */
    public static int getPid() {
        return Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName()
                .split("@")[0]);
    }

    /**
     * [RecordPid] switch
     */
    private static AtomicBoolean on = new AtomicBoolean(false);

    /**
     * Record your application process number<br>
     * This method prohibits multiple invocations<br>
     *
     * @param exitDelete auto delete pid file when system exit.<br>
     * @param fileName   specify pid file
     * @throws IOException
     */
    public static void recordPid(boolean exitDelete, String fileName) throws IOException {
        if (!on.get()) {
            File file = new File(Objects.isNull(fileName) ? PID : fileName);
            if (exitDelete) file.deleteOnExit();
            OutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(getPid());
                on.set(true);
            } finally {
                fileOutputStream.close();
            }
        } else {
            throw new RuntimeException("This method ['recordPid'] prohibits multiple invocations");
        }
    }
    /**
     * Record your application process number<br>
     * This method prohibits multiple invocations<br>
     * @throws IOException
     */
    public static void recordPid() throws IOException {
        recordPid(false, null);
    }

    public static void main(String[] args) throws IOException {
        recordPid();
    }
}
