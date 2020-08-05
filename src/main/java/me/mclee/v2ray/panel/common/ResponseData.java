package me.mclee.v2ray.panel.common;

import lombok.Data;

@Data
public class ResponseData<T> {

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

    public static <T> ResponseData<T> success() {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setErrorCode(0);
        return responseData;
    }

    public static <T> ResponseData<T> success(T obj) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setErrorCode(0);
        responseData.setData(obj);
        return responseData;
    }

    public static <T> ResponseData<T> fail(int errorCode) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setErrorCode(errorCode);
        String message = BundleMassageUtils.getErrorCodeMessages(errorCode);
        responseData.setMessage(message);
        return responseData;
    }
}
