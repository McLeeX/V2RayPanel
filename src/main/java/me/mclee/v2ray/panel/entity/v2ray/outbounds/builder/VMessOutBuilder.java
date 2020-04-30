package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess.VMess;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess.Vnext;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class VMessOutBuilder {

    private List<Vnext> vnext = new ArrayList<>();

    public VMess build() {
        VMess vMess = new VMess();
        vMess.setVnext(vnext);
        return vMess;
    }

    public VMessOutBuilder addVnext(Vnext vnext) {
        this.vnext.add(vnext);
        return this;
    }

    public VMessOutBuilder setAllVnext(List<Vnext> vnext) {
        this.vnext = vnext;
        return this;
    }
}
