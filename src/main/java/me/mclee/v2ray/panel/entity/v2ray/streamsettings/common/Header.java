package me.mclee.v2ray.panel.entity.v2ray.streamsettings.common;

public class Header {
    private Type type;
    private HTTPRequest request;
    private HTTPResponse response;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public HTTPRequest getRequest() {
        return request;
    }

    public void setRequest(HTTPRequest request) {
        this.request = request;
    }

    public HTTPResponse getResponse() {
        return response;
    }

    public void setResponse(HTTPResponse response) {
        this.response = response;
    }
}
