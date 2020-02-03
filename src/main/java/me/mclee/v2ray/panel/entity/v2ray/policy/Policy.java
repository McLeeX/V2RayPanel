package me.mclee.v2ray.panel.entity.v2ray.policy;

import java.util.Map;

public class Policy {
    private Map<Integer, LevelPolicy> levels;
    private SystemPolicy system;

    public Map<Integer, LevelPolicy> getLevels() {
        return levels;
    }

    public void setLevels(Map<Integer, LevelPolicy> levels) {
        this.levels = levels;
    }

    public SystemPolicy getSystem() {
        return system;
    }

    public void setSystem(SystemPolicy system) {
        this.system = system;
    }
}
