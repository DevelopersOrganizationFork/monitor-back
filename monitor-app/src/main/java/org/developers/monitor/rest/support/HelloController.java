package org.developers.monitor.rest.support;

import org.developers.monitor.rest.dto.HelloMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * First testing component says hello.
 * If it works properly it means application starts without errors.
 */
@RestController
@RequestMapping("/rest/hello")
public class HelloController {
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloMessage hello() {
        return new HelloMessage("Hello! Monitor is running!");
    }
    
    
}
