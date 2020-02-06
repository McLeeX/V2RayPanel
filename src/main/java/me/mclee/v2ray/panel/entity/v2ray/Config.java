package me.mclee.v2ray.panel.entity.v2ray;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.mclee.v2ray.panel.common.utils.JsonUtil;
import me.mclee.v2ray.panel.entity.v2ray.api.Api;
import me.mclee.v2ray.panel.entity.v2ray.dns.Dns;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.Inbound;
import me.mclee.v2ray.panel.entity.v2ray.log.Log;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.Outbound;
import me.mclee.v2ray.panel.entity.v2ray.policy.Policy;
import me.mclee.v2ray.panel.entity.v2ray.routing.Routing;
import me.mclee.v2ray.panel.entity.v2ray.stats.Stats;
import me.mclee.v2ray.panel.entity.v2ray.transport.Transport;

import java.util.List;

public class Config {
    private Log log;
    private Api api;
    private Dns dns;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Stats stats;
    private Routing routing;
    private Policy policy;
    private List<Inbound> inbounds;
    private List<Outbound> outbounds;
    private Transport transport;

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public Dns getDns() {
        return dns;
    }

    public void setDns(Dns dns) {
        this.dns = dns;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Routing getRouting() {
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public List<Inbound> getInbounds() {
        return inbounds;
    }

    public void setInbounds(List<Inbound> inbounds) {
        this.inbounds = inbounds;
    }

    public List<Outbound> getOutbounds() {
        return outbounds;
    }

    public void setOutbounds(List<Outbound> outbounds) {
        this.outbounds = outbounds;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public static void main(String[] args) {
        Config config = JsonUtil.string2Obj("{\n" +
                "  \"dns\": {\n" +
                "    \"servers\": [\n" +
                "      \"114.114.114.114\",\n" +
                "      \"8.8.8.8\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"inbounds\": [\n" +
                "    {\n" +
                "      \"protocol\": \"socks\",\n" +
                "      \"port\": 1081,\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"settings\": {\n" +
                "        \"udp\": false\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"port\": 8001,\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"protocol\": \"http\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"log\": {\n" +
                "    \"error\": \"D:\\\\V2RayW\\\\log\\\\error.log\",\n" +
                "    \"access\": \"D:\\\\V2RayW\\\\log\\\\access.log\",\n" +
                "    \"loglevel\": \"debug\"\n" +
                "  },\n" +
                "  \"outbounds\": [\n" +
                "    {\n" +
                "      \"sendThrough\": \"0.0.0.0\",\n" +
                "      \"mux\": {\n" +
                "        \"enabled\": false,\n" +
                "        \"concurrency\": 8\n" +
                "      },\n" +
                "      \"protocol\": \"vmess\",\n" +
                "      \"settings\": {\n" +
                "        \"vnext\": [\n" +
                "          {\n" +
                "            \"address\": \"lirx.me\",\n" +
                "            \"users\": [\n" +
                "              {\n" +
                "                \"id\": \"83c0ebad-9090-4697-b711-4202dda6fbe1\",\n" +
                "                \"alterId\": 32,\n" +
                "                \"security\": \"auto\",\n" +
                "                \"level\": 0\n" +
                "              }\n" +
                "            ],\n" +
                "            \"port\": 443\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"tag\": \"lirx.me wss\",\n" +
                "      \"streamSettings\": {\n" +
                "        \"wsSettings\": {\n" +
                "          \"path\": \"/fetch\",\n" +
                "          \"headers\": {}\n" +
                "        },\n" +
                "        \"quicSettings\": {\n" +
                "          \"key\": \"key\",\n" +
                "          \"security\": \"none\",\n" +
                "          \"header\": {\n" +
                "            \"type\": \"none\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"tlsSettings\": {\n" +
                "          \"allowInsecure\": false,\n" +
                "          \"alpn\": [\n" +
                "            \"http/2.0\"\n" +
                "          ],\n" +
                "          \"serverName\": \"lirx.me\",\n" +
                "          \"allowInsecureCiphers\": false\n" +
                "        },\n" +
                "        \"httpSettings\": {\n" +
                "          \"host\": [\n" +
                "            \"\"\n" +
                "          ],\n" +
                "          \"path\": \"\"\n" +
                "        },\n" +
                "        \"kcpSettings\": {\n" +
                "          \"header\": {\n" +
                "            \"type\": \"none\"\n" +
                "          },\n" +
                "          \"mtu\": 1350,\n" +
                "          \"congestion\": false,\n" +
                "          \"tti\": 50,\n" +
                "          \"uplinkCapacity\": 5,\n" +
                "          \"writeBufferSize\": 2,\n" +
                "          \"readBufferSize\": 2,\n" +
                "          \"downlinkCapacity\": 20\n" +
                "        },\n" +
                "        \"tcpSettings\": {\n" +
                "          \"header\": {\n" +
                "            \"type\": \"none\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"security\": \"tls\",\n" +
                "        \"network\": \"ws\",\n" +
                "        \"sockopt\": {}\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"routing\": {\n" +
                "    \"name\": \"全部使用主服务器\",\n" +
                "    \"domainStrategy\": \"AsIs\",\n" +
                "    \"rules\": [\n" +
                "      {\n" +
                "        \"type\": \"field\",\n" +
                "        \"port\": \"0-65535\",\n" +
                "        \"outboundTag\": \"lirx.me wss\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}", Config.class);
        System.out.println(JsonUtil.obj2String(config));
    }
}
