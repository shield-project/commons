package org.shoper.commons.core;

import org.apache.commons.lang3.SerializationException;

import java.io.*;
import java.util.Objects;

/**
 * Created by Administrator on 2017/1/18 0018.
 */
public class SeriablizableUtil {
    /**
     * 序列化对象<br>
     * Created by ShawnShoper 2016年5月19日
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Serializable object) {
            ByteArrayOutputStream baos = null;
            byte[] dataBytes;
            ObjectOutputStream out = null;
            try {
                baos = new ByteArrayOutputStream(1024);
                out = new ObjectOutputStream(baos);
                out.writeObject(object);
                dataBytes = baos.toByteArray();
            } catch (IOException ex) {
                throw new SerializationException(ex);
            } finally {
                try {
                    if (Objects.nonNull(out))
                        out.close();
                    if (Objects.nonNull(baos))
                        baos.close();
                } catch (IOException ex) {
                }
            }
            return dataBytes;
    }

    /**
     * 反序列化 <br>
     * Created by ShawnShoper 2016年6月2日
     *
     * @param objectData
     * @return
     */
    public static <K> K deserialize(byte[] objectData, Class<K> k) throws IOException {
        if (objectData == null)
            return null;
        ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
        return deserialize(bais, k);
    }

    /**
     * 反序列化 <br>
     * Created by ShawnShoper 2016年6月2日
     *
     * @param inputStream
     * @return
     */
    public static <K> K deserialize(InputStream inputStream, Class<K> k) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException(
                    "The InputStream must not be null");
        }
        ObjectInputStream in = null;
        try {
            // stream closed in the finally
            in = new ObjectInputStream(inputStream);
            return (K) in.readObject();

        } catch (ClassNotFoundException ex) {
            throw new SerializationException(ex);
        } catch (IOException ex) {
            throw new SerializationException(ex);
        } finally {
            if (in != null)
                in.close();
        }
    }
}

class SerializableObject<T> implements Serializable {

    private static final long serialVersionUID = -7735253286844892242L;
    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T t) {
        this.object = t;
    }

}
