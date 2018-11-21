package org.shoper.commons.core;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间、日期工具类
 *
 * @author ShawnShoper
 * @date 2017/1/19 0019 16:56
 */
public class DateUtil {
    public static final String DATE24 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE12 = "yyyy-MM-dd hh:mm:ss";
    public static final String DATE24_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String DATE12_CN = "yyyy年MM月dd日 hh时mm分ss秒";

    public static class TimeElapse {
        private long day;
        private long hour;
        private long min;
        private long sec;
        private long msec;

        public long getDay() {
            return day;
        }

        public void setDay(long day) {
            this.day = day;
        }

        public long getHour() {
            return hour;
        }

        public void setHour(long hour) {
            this.hour = hour;
        }

        public long getMin() {
            return min;
        }

        public void setMin(long min) {
            this.min = min;
        }

        public long getSec() {
            return sec;
        }

        public void setSec(long sec) {
            this.sec = sec;
        }

        public long getMsec() {
            return msec;
        }

        public void setMsec(long msec) {
            this.msec = msec;
        }

        public String simpleToString() {
            return (this.day == 0 ? "" : day + " d,")
                    + (this.hour == 0 ? "" : this.hour + " H,")
                    + (this.min == 0 ? "" : this.min + " m");
        }

        @Override
        public String toString() {
            return (this.day == 0 ? "" : day + " d,")
                    + (this.hour == 0 ? "" : this.hour + " H,")
                    + (this.min == 0 ? "" : this.min + " m,")
                    + (this.sec == 0 ? "" : this.sec + " s,") + this.msec
                    + " ms";
        }
    }

    public static final long SEC = 1000;
    public static final long MIN = 60 * SEC;
    public static final long HOUR = 60 * MIN;
    public static final long DAY = 24 * HOUR;

    /**
     * Convert to time to day-hour-min-sec-msec field
     *
     * @param time
     * @return
     */
    public static TimeElapse timeToStr(long time) {
        TimeElapse dateBean = new TimeElapse();
        if (time > SEC) {
            if (time < MIN) {
                dateBean.setSec(time / SEC);
                dateBean.setMsec(time - dateBean.getSec() * SEC);
            } else if (time < HOUR) {
                dateBean.setMin(time / MIN);
                dateBean.setSec((time - dateBean.getMin() * MIN) / SEC);
                dateBean.setMsec(time - dateBean.getMin() * MIN
                        - dateBean.getSec() * SEC);
            } else if (time < DAY) {
                dateBean.setHour(time / HOUR);
                dateBean.setMin((time - dateBean.getHour() * HOUR) / MIN);
                dateBean.setSec((time - dateBean.getHour() * HOUR
                        - dateBean.getMin() * MIN) / SEC);
                dateBean.setMsec(time - dateBean.getHour() * HOUR
                        - dateBean.getMin() * MIN - dateBean.getSec() * SEC);
            } else {
                dateBean.setDay(time / DAY);
                dateBean.setHour((time - dateBean.getDay() * DAY) / HOUR);
                dateBean.setMin((time - dateBean.getDay() * DAY
                        - dateBean.getHour() * HOUR) / MIN);
                dateBean.setSec((time - dateBean.getDay() * DAY
                        - dateBean.getHour() * HOUR - dateBean.getMin() * MIN)
                        / SEC);
                dateBean.setMsec(time - dateBean.getDay() * DAY
                        - dateBean.getHour() * HOUR - dateBean.getMin() * MIN
                        - dateBean.getSec() * SEC);
            }
        } else
            dateBean.setMsec(time);
        return dateBean;
    }

    /**
     * Date 转换成 时间字符串
     *
     * @param format
     * @param date
     * @return
     */
    public static String dateToString(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * time转换成时间字符串
     *
     * @param format
     * @param time
     * @return
     */
    public synchronized static String timeToString(String format, long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    public static class DiffTime {
        private long lastTime;
        private long comparedTime;

        public DiffTime reset() {
            lastTime = 0L;
            comparedTime = 0L;
            return this;
        }

        public void setComparedTime(long comparedTime) {
            this.comparedTime = comparedTime;
        }

        public DiffTime set(long time) {
            lastTime = time - lastTime;
            return this;
        }

        public static TimeElapse elapse2str(long time) {
            return DateUtil.timeToStr(time);
        }

        public TimeElapse elapse2str() {
            return DateUtil.timeToStr((comparedTime != 0 ? comparedTime : System.currentTimeMillis()) - lastTime);
        }

        public long elapse2time() {
            return System.currentTimeMillis() - lastTime;
        }
    }
}