package me.mclee.v2ray.panel.entity.v2ray.dns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.mclee.v2ray.panel.common.utils.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonDeserialize(using = Service.ServiceDeserializer.class)
public class Service {

    private static final String PROTOCOL_HTTP = "http://";
    private static final String PROTOCOL_HTTPS = "https://";
    private static final String PROTOCOL_HTTP_LOCAL = "http+local://";
    private static final String PROTOCOL_HTTPS_LOCAL = "https+local://";

    private String address;
    private Integer port;
    private List<String> domains;
    private List<String> expectIPs;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getExpectIPs() {
        return expectIPs;
    }

    public void setExpectIPs(List<String> expectIPs) {
        this.expectIPs = expectIPs;
    }

    @JsonIgnore
    public boolean isDOH() {
        return address != null && (address.startsWith(PROTOCOL_HTTP) || address.startsWith(PROTOCOL_HTTPS)
                || address.startsWith(PROTOCOL_HTTP_LOCAL) || address.startsWith(PROTOCOL_HTTPS_LOCAL));
    }

    public static class ServiceDeserializer extends JsonDeserializer<Service> {

        @Override
        public Service deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Service service = new Service();
            if (p.hasToken(JsonToken.VALUE_STRING)) {
                String address = p.getText();
                service.setAddress(address);
            } else {
                Optional<JsonNode> node = Optional.ofNullable(p.getCodec().readTree(p));
                node.map(n -> n.get("address")).map(JsonNode::asText).ifPresent(service::setAddress);
                node.map(n -> n.get("port")).map(JsonNode::asInt).ifPresent(service::setPort);
                List<String> domains = new ArrayList<>();
                service.setDomains(domains);
                node.map(n -> n.get("domains")).ifPresent(jsonArray -> {
                    for (JsonNode domain : jsonArray) {
                        domains.add(domain.asText());
                    }
                });
                List<String> expectIPs = new ArrayList<>();
                service.setExpectIPs(expectIPs);
                node.map(n -> n.get("expectIPs")).ifPresent(jsonArray -> {
                    for (JsonNode domain : jsonArray) {
                        expectIPs.add(domain.asText());
                    }
                });
            }
            return service;
        }
    }

    public static void main(String[] args) {
        Service service = JsonUtil.string2Obj("{\"address\": \"1.2.3.4\", \"port\": 5353, \"domains\": [\"domain:v2ray.com\"], \"expectIPs\": [\"geoip:cn\"] }", Service.class);
        System.out.println(JsonUtil.obj2String(service));
    }
}
