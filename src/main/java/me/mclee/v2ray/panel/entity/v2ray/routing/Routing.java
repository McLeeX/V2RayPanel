package me.mclee.v2ray.panel.entity.v2ray.routing;

import java.util.List;

public class Routing {
    private DomainStrategy domainStrategy;
    private List<Rule> rules;
    private List<Balancer> balancers;

    public DomainStrategy getDomainStrategy() {
        return domainStrategy;
    }

    public void setDomainStrategy(DomainStrategy domainStrategy) {
        this.domainStrategy = domainStrategy;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Balancer> getBalancers() {
        return balancers;
    }

    public void setBalancers(List<Balancer> balancers) {
        this.balancers = balancers;
    }
}
