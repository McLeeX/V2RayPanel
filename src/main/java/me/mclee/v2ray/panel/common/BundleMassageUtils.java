package me.mclee.v2ray.panel.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleMassageUtils {

    public static final String ERROR_CODE_BUNDLE_NAME = "errorCode";

    public static String getMessages(String bundleName, Locale locale, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        return bundle.getString(key);
    }

    public static String getMessages(String bundleName, String key) {
        return getMessages(bundleName, Locale.getDefault(), key);
    }

    public static String getErrorCodeMessages(int errorCode) {
        return getMessages(ERROR_CODE_BUNDLE_NAME, Integer.toString(errorCode));
    }

    public static String getErrorCodeMessages(Locale locale, int errorCode) {
        return getMessages(ERROR_CODE_BUNDLE_NAME, locale, Integer.toString(errorCode));
    }
}
