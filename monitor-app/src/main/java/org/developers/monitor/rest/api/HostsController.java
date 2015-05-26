package org.developers.monitor.rest.api;

import org.developers.monitor.rest.dto.Host;
import org.developers.monitor.rest.support.RestConfig;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@RestController
@RequestMapping(RestConfig.HOSTS_PATH)
public class HostsController {
    
    private List<Host> fakeHosts = Arrays.asList(
            new Host() {{name="hostname0"; href="/hosts/hostname0";}},
            new Host() {{name="hostname1"; href="/hosts/hostname1";}},
            new Host() {{name="hostname2"; href="/hosts/hostname2";}},
            new Host() {{name="hostname3"; href="/hosts/hostname3";}},
            new Host() {{name="hostname4"; href="/hosts/hostname4";}}
        );

    @RequestMapping(value = "/{hostid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE}) 
    public Host getHost(@PathVariable("hostid") int hostid){
        return fakeHosts.get(hostid);        
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Host> getHostByName(@RequestParam(value = "name", required = false) String name){
        return name!=null && !name.isEmpty() 
                ? fakeHosts.stream().filter(h -> h.name.contains(name)).collect(Collectors.toList())
                : fakeHosts;
    }
}
