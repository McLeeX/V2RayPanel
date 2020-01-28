package me.mclee.v2ray.panel.common;

import java.util.Locale;

public class AppException extends Exception {

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public AppException(int errorCode) {
        super(BundleMassageUtils.getMessages("errorCode", Integer.toString(errorCode)));
        this.errorCode = errorCode;
    }
}
