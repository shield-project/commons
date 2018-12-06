package org.shoper.commons.core;

import java.io.*;

/**
 * Created by Administrator on 2017/1/18 0018.
 */
public class CloneUtil {
    private CloneUtil() {
    }

    /**
     * 对象深度克隆....
     *
     * @param srcObj
     * @param clazz
     * @return
     */
    public static <T> T depthClone(Object srcObj, Class<T> clazz) throws SystemException {
        Object cloneObj;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(out);
            oo.writeObject(srcObj);
            ByteArrayInputStream in = new ByteArrayInputStream(
                    out.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(in);
            cloneObj = oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SystemException(e);
        }
        return clazz.cast(cloneObj);
    }
}
