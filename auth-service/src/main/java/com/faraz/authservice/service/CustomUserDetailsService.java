package com.faraz.authservice.service;


import com.faraz.authservice.exception.exception.ClientException;
import com.faraz.authservice.feign.FinanceFeign;
import com.faraz.authservice.model.CustomUser;

import com.faraz.authservice.model.LoginDTO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.provider.Serializer;
/*import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMap;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
*/
import com.netflix.discovery.shared.Application;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.propagation.Format;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
import lombok.SneakyThrows;
import okhttp3.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Serializer
@Service
@RequiredArgsConstructor
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CustomUserDetailsService implements UserDetailsService {

    private final FinanceFeign financeFeign;
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;
 //   private final Tracer tracer;
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TimeUnit.SECONDS.sleep(10);
        try {
            Application application = eurekaClient.getApplication("finance-service");
            InstanceInfo instanceInfo = application.getInstances().get(0);
            System.out.println("aaaaaa============" + instanceInfo.getIPAddr() + "=" +
                    instanceInfo.getPort());
        }catch (Exception er)
        {
            System.out.println("eeeeeeeeerrrrr==============="+er.toString());
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.
                getRequestAttributes())).getRequest();
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        Enumeration<String> enums = request.getHeaderNames();
        while(enums.hasMoreElements()) {
            String name = enums.nextElement();
         //   body.add(name, request.getHeader(name));
            System.out.println("k------>" + name +"==="+request.getHeader(name));
        }
        body.add("accept", "*/*");
        body.add("host", "192.168.1.6:9091");
        body.add("connection","keep-alive");
        body.add("user-agent","Java/14.0.1");
        Span span = GlobalTracer.get().buildSpan("my span-login").start();
        try (Scope scope = GlobalTracer.get().scopeManager().activate(span)) {
            span.log("user name:" + username);
           // GlobalTracer.get().inject(span.context(), Format.Builtin.HTTP_HEADERS, new );
            System.out.println(">>>>>>>>>>>>>.="+request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getRequestURI()))+
                    "/user/"+ "get-login-dto");
                   Request.Builder requestBuilder = new Request.Builder()
                         .url(request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getRequestURI()))+
                                 "/user/"+ "get-login-dto");
//            TextMap httpHeadersCarrier = new AnHttpHeaderCarrier(httpRequest);
            Tags.SPAN_KIND.set(GlobalTracer.get().activeSpan(), Tags.SPAN_KIND_SERVER);
            Tags.HTTP_METHOD.set(GlobalTracer.get().activeSpan(), "GET");
            //         Tags.HTTP_URL.set(tracer.activeSpan(), url.toString());
            GlobalTracer.get().inject(span.context(), Format.Builtin.HTTP_HEADERS,
                    new RequestBuilderCarrier(requestBuilder));



        System.out.println("request1====:" + request.getRequestURI());
        System.out.println("request2====:" + request.getRequestURL());
        System.out.println("request3====:" + request.getServletPath());
        System.out.println("request4====:" + request.getRemoteHost());
        System.out.println("request5====:" + request.getRemoteAddr());
        System.out.println("request6====:" + request.getServerName());

            int farazKey = Integer.parseInt(request.getParameter("farazKey"));
        List<LoginDTO> loginDTO;
        System.out.println("request====:" + request);
            LoginDTOList loginDTOs ;
            loginDTO = financeFeign.getLoginDto(username, farazKey);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded");
            headers.add("Authorization", "Basic bWFudHJhOm1hbnRyYUVSUDEyMjUyKioq");
            headers.add("client-secret", "mantraERP12252***");
            headers.add("clientId", "secret");
            headers.add("accept", "*/*");
            headers.add("host", "192.168.1.6:9091");
            headers.add("connection","keep-alive");
            headers.add("user-agent","Java/14.0.1");

            new HttpEntity<String>(headers);
            HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
            System.out.println("request80====:" + httpEntity);
            try {
           //     ResponseEntity<LoginDTO[]> responseEntity = restTemplate.exchange(
           //             "http://192.169.1.2:8790/finance-service/user/get-login-dto?username=Admin&password=12252&grant_type=password&&farazKey=1",
           //             HttpMethod.GET, httpEntity, LoginDTO[].class);
            //    System.out.println("request9====:" + responseEntity.getBody()[0].getFamily());
           //     System.out.println("request10====:" + responseEntity.getBody()[0]);
            }catch (Exception er)
            {
                System.out.println("errrrrrrrrrrrrr========"+er.toString());
            }
            System.out.println("request7====:" + body);
     //       ResponseEntity<LoginDTOList>  responseEntity2 = restTemplate.exchange("http://192.168.1.5:9091/user/get-login-dto?username=Admin&farazKey=1",
      //              HttpMethod.GET, httpEntity,  LoginDTOList.class);
        //    responseEntity.getBody()
//            loginDTOs = restTemplate.getForObject("http://192.168.1.5:8790/finance-service/user/get-login-dto?username=Admin&farazKey=1",
//                LoginDTOList.class);

        try {
            try {
             //   System.out.println("tracer===="+GlobalTracer.get());

                loginDTO = financeFeign.getLoginDto(username, farazKey);
                System.out.println("load=====:" + loginDTO);
            } catch (Exception e) {
                System.out.println("loadErr=====:" + e.toString());
                e.printStackTrace();
                throw new ClientException("error.feign.connection.refused", "Finance-Service");
            }
            if (loginDTO != null && !loginDTO.isEmpty()) {
                return new CustomUser(loginDTO);
            } else {
                throw new UsernameNotFoundException("User " + username + " was not found in the database");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        } finally {
            span.finish();
        }

    }

}
/*
    public class RequestBuilderCarrier implements io.opentracing.propagation.TextMap {
        private final Request.Builder builder;

        RequestBuilderCarrier(Request.Builder builder) {
            this.builder = builder;
        }

        @Override
        public Iterator<Map.Entry<String, String>> iterator() {
            throw new UnsupportedOperationException("carrier is write-only");
        }

        @Override
        public void put(String key, String value) {
            builder.addHeader(key, value);
        }
    }
    protected void attachTraceInfo(Tracer tracer, Span span, final Invocation inv) {
        tracer.inject(span.context(), Format.Builtin.TEXT_MAP, new TextMap() {

            @Override
            public void put(String key, String value) {
                inv.getAttachments().put(key, value);
            }

            @Override
            public Iterator<Map.Entry<String, String>> iterator() {
                throw new UnsupportedOperationException("TextMapInjectAdapter should only be used with Tracer.inject()");
            }
        });
    }
}
*/
/*
class HeadersMapInjectAdapter implements TextMap {

    private final HttpHeaders headers;

    public HeadersMapInjectAdapter(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        throw new UnsupportedOperationException("iterator should never be used with Tracer.inject()");
    }

    @Override
    public void put(String key, String value) {
        headers.add(key, value.getBytes(StandardCharsets.UTF_8).toString());
    }
}

 */

 class RequestBuilderCarrier implements io.opentracing.propagation.TextMap {
    private final Request.Builder builder;

    RequestBuilderCarrier(Request.Builder builder) {
        this.builder = builder;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        throw new UnsupportedOperationException("carrier is write-only");
    }

    @Override
    public void put(String key, String value) {
        builder.addHeader(key, value);
    }
}

 class LoginDTOList {
    private List<LoginDTO> lstLoginDTO;

    public LoginDTOList() {
        lstLoginDTO = new ArrayList<>();
    }

    // standard constructor and getter/setter
}
