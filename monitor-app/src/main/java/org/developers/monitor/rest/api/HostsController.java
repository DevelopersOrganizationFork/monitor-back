package org.developers.monitor.rest.api;

import org.developers.monitor.persistence.DAO.HostDao;
import org.developers.monitor.rest.dto.HostDto;
import org.developers.monitor.rest.dto.mapper.HostMapper;
import org.developers.monitor.rest.support.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    HostDao hostDao;

    @RequestMapping(value = "/{hostid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE}) 
    public HostDto getHostById(@PathVariable("hostid") String hostid){
        return new HostMapper().map(hostDao.getHostById(hostid));        
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<HostDto> getHostByName(@RequestParam(value = "name", required = false) String name){
        List<HostDto> hostDtos = new HostMapper().mapList(hostDao.getAllHosts());
        
        return name!=null && !name.isEmpty() 
                ? hostDtos.stream().filter(h -> h.name.contains(name)).collect(Collectors.toList())
                : hostDtos;
       
    }
    
    @RequestMapping(value = "/topten", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<HostDto> getTopTenHosts() {
        return null;
    }
}
