package org.developers.monitor.rest.dto.mapper;

import org.developers.monitor.persistence.Host;
import org.developers.monitor.rest.dto.HostDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian.alberski on 2015-06-10.
 */
public class HostMapper {
    
    public HostDto map(Host host) {
        HostDto hostDto = new HostDto();
        hostDto.name = host.getHostName();
        hostDto.ip = host.getHostIP();
        hostDto.id = host.getHostId();
        return hostDto;
    }
    
    public List<HostDto> mapList(List<Host> hosts) {
        List<HostDto> hostDtos = new ArrayList<>();
        hosts.stream().forEach(h -> hostDtos.add(map(h)));
        return hostDtos;
    }
}
