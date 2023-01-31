package com.faraz.authservice.feign;

import com.faraz.authservice.model.LoginDTO;
import io.opentracing.Tracer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("finance-service")
public interface FinanceFeign {

    String prefix = "/user/";

    @GetMapping(prefix + "get-login-dto" )
    List<LoginDTO> getLoginDto(@RequestParam String username, @RequestParam int farazKey);
}
