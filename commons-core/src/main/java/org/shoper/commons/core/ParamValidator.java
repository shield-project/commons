package org.shoper.commons.core;

import java.util.Collection;

/**
 * 通用参数验证器
 * <p>此类可作为函数参数校验的通用验证工具类使用，可自由扩展自己的验证方法。</p>
 * <p>验证器采用智能链式校验，只要有一个参数没有通过就会直接返回，并记录当前的错误参数消息</p>
 * @author Zebe
 * @url http://www.zebe.me/article-detail-6ce50beb-34f6-4aa7-8b46-b48d589c3f1a.html
 * @version 1.0.0
 */
public class ParamValidator {

    /**
     * 失败参数统计
     */
    private int illegalCount = 0;

    /**
     * 失败消息引用
     */
    private String failedMessage = "";

    /**
     * 初始化验证器
     * @return 参数验证器自身
     */
    public ParamValidator init() {
        illegalCount = 0;
        failedMessage = "";
        return this;
    }

    /**
     * 是否为有效的校验结果
     * @return 执行校验后，失败参数的统计数为0，表示校验通过，返回true
     */
    public boolean isValid() {
        return illegalCount == 0;
    }

    /**
     * 获取当前错误消息
     * @return 返回最后一次校验不通过时，参数传递过来的失败消息引用
     */
    public String getFailedMessage() {
        return failedMessage;
    }

    /**
     * 获取当前错误参数统计
     * @return 错误参数总数
     */
    public int getIllegalCount() {
        return illegalCount;
    }

    /**
     * 要求非空（null）
     * @param obj 任意参数对象
     * @param message 参数不通过时的信息
     * @return 参数验证器自身
     */
    public ParamValidator notNull(Object obj, String message) {
        if (illegalCount > 0) {
            return this;
        } else {
            if (obj == null) {
                illegalCount++;
                failedMessage = message;
            }
        }
        return this;
    }

    /**
     * 要求非空白字符串
     * @param obj 任意参数对象
     * @param message 参数不通过时的信息
     * @return 参数验证器自身
     */
    public ParamValidator notBlank(String obj, String message) {
        if (illegalCount > 0) {
            return this;
        } else {
            if (obj == null || obj.trim().isEmpty()) {
                illegalCount++;
                failedMessage = message;
            }
        }
        return this;
    }

    /**
     * 要求必须是整数（正、负均可）
     * @param obj 任意参数对象
     * @param message 参数不通过时的信息
     * @return 参数验证器自身
     */
    public ParamValidator needIntNumber(Object obj, String message) {
        if (illegalCount > 0) {
            return this;
        } else {
            try {
                Integer.parseInt(String.valueOf(obj));
            } catch (NumberFormatException e) {
                illegalCount++;
                failedMessage = message;
            }
        }
        return this;
    }

    /**
     * 需要一个元素在某个数组中
     * @param obj 任意参数对象
     * @param array 任意对象数组
     * @param message 参数不通过时的信息
     * @return 参数验证器自身
     */
    public ParamValidator needInArray(Object obj, Object[] array, String message) {
        if (illegalCount > 0) {
            return this;
        } else {
            if (array != null) {
                boolean exist = false;
                for (Object o : array) {
                    if (o.equals(obj)) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    illegalCount++;
                    failedMessage = message;
                }
            } else {
                illegalCount++;
                failedMessage = message;
            }
        }
        return this;
    }

    /**
     * 需要一个元素在某个集合中
     * @param obj 任意参数对象
     * @param collection 任意集合
     * @param message 参数不通过时的信息
     * @return 参数验证器自身
     */
    public ParamValidator needInCollection(Object obj, Collection<?> collection, String message) {
        if (illegalCount > 0) {
            return this;
        } else {
            if (collection != null) {
                if (!collection.contains(obj)) {
                    illegalCount++;
                    failedMessage = message;
                }
            } else {
                illegalCount++;
                failedMessage = message;
            }
        }
        return this;
    }

}
