package me.newtrekwang.base.utils;

import java.lang.reflect.Method;

import javax.annotation.Nullable;

/**
 * @className CheckUtils
 * @createDate 2019/6/10 0:34
 * @author newtrekWang
 * @email 408030208@qq.com
 * @desc 做一些检查的工具类
 *
 */
public final class CheckUtils {

    private CheckUtils(){}
    /**
     * 快速构造方法异常
     * @param method
     * @param message
     * @param args
     * @return
     */
    public static RuntimeException methodError(Method method, String message, Object... args){
        return methodError(method,null,args);
    }

    /**
     * 快速构造方法异常
     * @param method
     * @param cause
     * @param message
     * @param args
     * @return
     */
    public static RuntimeException methodError(Method method, @javax.annotation.Nullable Throwable cause, String message, Object... args){
        message = String.format(message,args);
        return new IllegalArgumentException(message
                + "\n   for method "
                + method.getDeclaringClass().getSimpleName()
                + "."
                +method.getName(),cause);
    }

    /**
     * 快速构造参数异常
     * @param method 方法
     * @param cause
     * @param p
     * @param message
     * @param args
     * @return
     */
    public static RuntimeException parameterError(Method method, Throwable cause,int p,String message,Object... args){
        return methodError(method,cause,message+"(parameter #"+(p+1)+")",args);
    }

    /**
     * 快速构造参数异常
     * @param method
     * @param p
     * @param message
     * @param args
     * @return
     */
    public static RuntimeException parameterError(Method method,int p,String message,Object... args){
        return methodError(method,message+" (parameter #"+(p+1) + ")",args);
    }

    /**
     * 检查空引用，如果为空，会抛空指针异常
     * @param object
     * @param message
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(@Nullable T object, String message){
        if (object == null){
            throw new NullPointerException(message);
        }
        return object;
    }
}
