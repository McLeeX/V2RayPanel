package me.mclee.v2ray.panel.entity.v2ray.routing;

import java.util.List;

public class Balancer {
    private String tag;
    private List<String> selector;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getSelector() {
        return selector;
    }

    public void setSelector(List<String> selector) {
        this.selector = selector;
    }
}
