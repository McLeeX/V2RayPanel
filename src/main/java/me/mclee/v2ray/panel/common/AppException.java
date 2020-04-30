package me.mclee.v2ray.panel.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppException extends Exception {

    private int errorCode;

    public AppException(int errorCode) {
        super(BundleMassageUtils.getMessages("errorCode", Integer.toString(errorCode)));
        this.errorCode = errorCode;
    }

    public AppException(int errorCode, Throwable throwable) {
        super(BundleMassageUtils.getMessages("errorCode", Integer.toString(errorCode)), throwable);
        this.errorCode = errorCode;
    }
}
