package me.mclee.v2ray.panel.entity.v2ray.log;

/**
 * config.log
 */
public class Log {
    private String access;
    private String error;
    private LogLevel loglevel;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LogLevel getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(LogLevel loglevel) {
        this.loglevel = loglevel;
    }
}
