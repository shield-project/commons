package org.shoper.commons.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Scheduler管理类,提供获取,管理Scheduler...等
 *
 * @author ShawnShoper
 */
public class QuartzManager {
    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static ReentrantLock lock = new ReentrantLock();
    private static Map<String, Scheduler> schedulers = new HashMap<String, Scheduler>();

    /**
     * 获取一个Scheduler
     *
     * @param schedulerName
     * @return
     * @throws SchedulerException
     */
    public Scheduler getScheduler(String schedulerName)
            throws SchedulerException {

        if (schedulerName == null || schedulerName.isEmpty())
            throw new IllegalArgumentException(
                    "The [scheduleName] can not be null or empty...");
        Scheduler scheduler = null;
        lock.lock();
        // 检查schedules集合里是否存在对应的schedulers
        if (schedulers.containsKey(schedulerName)) {
            scheduler = schedulers.get(schedulerName);
        } else {
            scheduler = sf.getScheduler();
            schedulers.put(schedulerName, scheduler);
            scheduler.start();
        }
        lock.unlock();
        return scheduler;
    }

    /**
     * 停止定时调度的功能...
     */
    public void shutdown(String schedulerName) throws SchedulerException {
        Scheduler scheduler = getScheduler(schedulerName);
        while (scheduler != null && !scheduler.isShutdown())
            shutdown(scheduler);
    }

    /**
     * shutdown a scheduler
     *
     * @param scheduler
     * @throws SchedulerException
     */
    private static void shutdown(Scheduler scheduler) throws SchedulerException {
        scheduler.shutdown();
    }

    public static void shutdownAll() throws SchedulerException {
        Collection<Scheduler> schedulers = sf.getAllSchedulers();
        if (schedulers != null && !schedulers.isEmpty()) {
            for (Scheduler scheduler : schedulers) {
                shutdown(scheduler);
            }
        }
    }
}
