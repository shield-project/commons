package org.shoper.commons.core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 参数验证器单元测试类
 * @author Zebe
 * @version 1.0.0
 */
public class ParamValidatorTests {

    @Test
    public void testInit() {
        // 让验证器不通过，此时错误统计数应该为1，消息不为空
        ParamValidator validator = new ParamValidator();
        validator.notNull(null, "对象不能为null");
        assertTrue(!"".equals(validator.getFailedMessage()));
        assertTrue(validator.getIllegalCount() > 0);
        // 验证器初始化，统计数和消息应该被重置
        validator.init();
        assertTrue("".equals(validator.getFailedMessage()));
        assertTrue(validator.getIllegalCount() == 0);
    }

    @Test
    public void testNotNull() {
        // 传入一个值为null的参数，验证器应该不通过
        ParamValidator validator = new ParamValidator();
        Long id = null;
        validator.notNull(id, "ID不能为空");
        assertFalse(validator.isValid());
        // 传入一个值不为null的参数，验证器应该通过
        validator = new ParamValidator();
        id = 19L;
        validator.notNull(id, "ID不能为空");
        assertTrue(validator.isValid());
    }

    @Test
    public void testNotBlank() {
        // 传入一个空串，验证器应该不通过
        ParamValidator validator = new ParamValidator();
        String name = "";
        validator.notBlank(name, "name不能为空");
        assertFalse(validator.isValid());
        // 传入多个空格，验证器应该不通过
        validator = new ParamValidator();
        String email = "      ";
        validator.notBlank(email, "email不能为空");
        assertFalse(validator.isValid());
        // 传入非空字符串，验证器应该通过
        validator = new ParamValidator();
        String password = "123456";
        validator.notBlank(password, "password不能为空");
        assertTrue(validator.isValid());
    }

    @Test
    public void testNeedIntNumber() {
        // 传入一个小数，验证器应该不通过
        ParamValidator validator = new ParamValidator();
        float price = 5.6f;
        validator.needIntNumber(price, "price必须是整数");
        assertFalse(validator.isValid());
        // 传入一个正整数，验证器应该通过
        validator = new ParamValidator();
        int pageSize = 10;
        validator.needIntNumber(pageSize, "pageSize必须是整数");
        assertTrue(validator.isValid());
        // 传入一个负整数，验证器应该通过
        validator = new ParamValidator();
        int indexOrder = -1;
        validator.needIntNumber(indexOrder, "indexOrder必须是整数");
        assertTrue(validator.isValid());
    }

}
