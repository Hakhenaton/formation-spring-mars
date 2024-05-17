package fr.sncf.d2d.up2dev.colibri2.common.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ApplicationErrorController implements ErrorController {
    
    @RequestMapping("/error")
    public Map<String, Object> handleErrors(HttpServletRequest request){
        
        final var map = new HashMap<String, Object>();
		map.put("status", request.getAttribute("jakarta.servlet.error.status_code"));
		map.put("reason", request.getAttribute("jakarta.servlet.error.message"));
		return map;
    }
}
