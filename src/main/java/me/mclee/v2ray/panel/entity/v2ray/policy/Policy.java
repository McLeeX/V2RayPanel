package me.mclee.v2ray.panel.entity.v2ray.policy;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Policy {
    private Map<Integer, LevelPolicy> levels;
    private SystemPolicy system;
}
