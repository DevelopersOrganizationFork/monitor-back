package org.developers.monitor.rest.api;

import org.developers.monitor.rest.dto.Host;
import org.developers.monitor.rest.support.RestConfig;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * Created by sebastian.alberski on 2015-05-04.
 */
@RestController
@RequestMapping(RestConfig.HOSTS_PATH)
public class HostsController {
    
    private List<Host> fakeHosts = Arrays.asList(
            new Host() {{name="0"; href="/hosts/0";}},
            new Host() {{name="1"; href="/hosts/1";}},
            new Host() {{name="2"; href="/hosts/2";}},
            new Host() {{name="3"; href="/hosts/3";}},
            new Host() {{name="4"; href="/hosts/4";}}
        );

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Host> getHosts() {
        return fakeHosts;
    }
    
    @RequestMapping(value = "/{hostid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE}) 
    public Host getHost(@PathVariable("hostid") int hostid){
        return fakeHosts.get(hostid);        
    }
}
