package me.mclee.v2ray.panel.common;

import java.io.Serializable;

public class ResponseData<T extends Serializable> {

    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 信息
     */
    private String message;
    /**
     * 返回值
     */
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResponseData<Serializable> success() {
        ResponseData<Serializable> responseData = new ResponseData<>();
        responseData.setErrorCode(0);
        return responseData;
    }

    public static <T extends Serializable> ResponseData<T> success(T obj) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setErrorCode(0);
        responseData.setData(obj);
        return responseData;
    }

    public static ResponseData<Serializable> fail(int errorCode) {
        ResponseData<Serializable> responseData = new ResponseData<>();
        responseData.setErrorCode(errorCode);
        String message = BundleMassageUtils.getErrorCodeMessages(errorCode);
        responseData.setMessage(message);
        return responseData;
    }
}
