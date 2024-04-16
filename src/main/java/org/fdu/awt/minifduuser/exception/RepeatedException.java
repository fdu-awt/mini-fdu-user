package org.fdu.awt.minifduuser.exception;

/**
 * @author Violette
 * @date 2024/4/16 11:52
 */
public class RepeatedException extends RuntimeException {
    public RepeatedException(String message) {
        super(message);
    }

    public static RepeatedException RepeatEntity(String entityClassName, String name) {
        return new RepeatedException(String.format("已存在name为%s的%s", name, entityClassName));
    }
}
