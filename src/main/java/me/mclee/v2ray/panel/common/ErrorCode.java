package me.mclee.v2ray.panel.common;

public class ErrorCode {
    public static final int OK = 0;
    /**
     * 内部错误
     */
    public static final int INTERNAL_ERROR = 1000;
    /**
     * 认证信息失败
     */
    public static final int AUTHENTICATION_ERROR = 1001;

    //========用户=10000-10999======//
    /**
     * 用户信息不存在
     */
    public static final int USER_NOT_FOUND = 10000;
    /**
     * 该用户名已被注册
     */
    public static final int USER_NAME_DUPLICATED = 10001;
}
