package me.mclee.v2ray.panel.entity.v2ray.dns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@JsonDeserialize(using = Server.ServiceDeserializer.class)
public class Server {

    private static final String PROTOCOL_HTTP = "http://";
    private static final String PROTOCOL_HTTPS = "https://";
    private static final String PROTOCOL_HTTP_LOCAL = "http+local://";
    private static final String PROTOCOL_HTTPS_LOCAL = "https+local://";

    private String address;
    private Integer port;
    private List<String> domains;
    private List<String> expectIPs;

    @JsonIgnore
    public boolean isDOH() {
        return address != null && (address.startsWith(PROTOCOL_HTTP) || address.startsWith(PROTOCOL_HTTPS)
                || address.startsWith(PROTOCOL_HTTP_LOCAL) || address.startsWith(PROTOCOL_HTTPS_LOCAL));
    }

    public static class ServiceDeserializer extends JsonDeserializer<Server> {

        @Override
        public Server deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Server service = new Server();
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
}
