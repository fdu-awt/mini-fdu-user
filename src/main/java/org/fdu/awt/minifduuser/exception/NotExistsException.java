package org.fdu.awt.minifduuser.exception;

/**
 * @author Violette
 * @date 2024/4/16 17:41
 */
public class NotExistsException extends RuntimeException {
    public NotExistsException(String message) {
        super(message);
    }

    public static NotExistsException NotExistsEntity(String entityClassName, String name) {
        return new NotExistsException(String.format("不存在name为%s的%s", name, entityClassName));
    }
}
