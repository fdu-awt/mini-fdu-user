package org.fdu.awt.minifduuser.exception;

/**
 * @author Violette
 * @date 2024/4/16 11:52
 */
public class RepeatedEntityException extends RuntimeException {
    public RepeatedEntityException(String message) {
        super(message);
    }

    public static RepeatedEntityException RepeatEntityName(String entityClassName, String name) {
        return new RepeatedEntityException(String.format("已存在name为%s的%s", name, entityClassName));
    }
}
