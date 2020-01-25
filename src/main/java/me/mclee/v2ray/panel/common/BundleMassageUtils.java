package me.mclee.v2ray.panel.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleMassageUtils {

    public static String getMessages(String bundleName, Locale locale, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        return bundle.getString(key);
    }
}
