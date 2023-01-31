package com.faraz.finance.service;


import com.faraz.finance.controller.FGNR.dto.LoginDTO;
import com.faraz.finance.model.FGNR.RolePermission;
import com.faraz.finance.repository.LoginDTORepository;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMap;
import io.opentracing.propagation.TextMapExtractAdapter;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;
import io.opentracing.propagation.*;

//import javax.ws.rs.core.MultivaluedMap;
//import javax.

@Service
@RequiredArgsConstructor
public class UserService {


    private final LoginDTORepository loginDTORepository;


    public Collection<LoginDTO> getLoginDto(final String username, final int farazKey) {
        Tracer tracer = GlobalTracer.get();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.
                getRequestAttributes())).getRequest();
        Request.Builder requestBuilder = new Request.Builder()
                .url(request.getRequestURL().toString());
      //   header = new HttpHeaders();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("test", "jhksd");

        HttpHeaders httpHeaders = new HttpHeaders();
   //     MultivaluedMap<String, String> rawHeaders =  httpHeaders.getRequestHeaders();
   //     final HashMap<String, String> headers = new HashMap<String, String>();
   //     for (String key : rawHeaders.keySet()) {
   //         headers.put(key, rawHeaders.get(key).get(0));
    //    }
/*

        Tracer.SpanBuilder spanBuilder;
        try {
            SpanContext parentSpan = tracer.extract(Format.Builtin.HTTP_HEADERS, new TextMap() {
                @Override
                public Iterator<Map.Entry<String, String>> iterator() {
                    return null;
                }

                @Override
                public void put(String key, String value) {

                }
            });
            if (parentSpan == null) {
                spanBuilder = tracer.buildSpan("operationName");
            } else {
                spanBuilder = tracer.buildSpan("operationName").asChildOf(parentSpan);
            }
        } catch (IllegalArgumentException e) {
            spanBuilder = tracer.buildSpan("operationName");
        }
//        spanBuilder.withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER).startActive(true);



 */


 //       final SpanContext parentSpan =
 //               tracer.extract(Format.Builtin.HTTP_HEADERS,
 //                       new HeadersMapInjectAdapter(new HttpHeaders()));

        // is a child of http client span in step 1
   //     Span span = GlobalTracer.get().buildSpan("httpServerSpan").asChildOf(parentSpan).start();

      //  Tracer.SpanBuilder spanBuilder;
  /*      try {
            if (parentSpan == null) {
                spanBuilder = tracer.buildSpan("finance-getlogin");
            } else {
                spanBuilder = tracer.buildSpan("finance-getlogin").asChildOf(parentSpan);
            }
        } catch (IllegalArgumentException e) {
            spanBuilder = tracer.buildSpan("finance-getlogin");
        }


   */
//        Tracer tracer = GlobalTracer.get();
        System.out.println("nnnbbbbb================================================"+ tracer);
/*        Tracer.SpanBuilder spanBuilder;
        try {
            SpanContext parentSpan = tracer.extract(Format.Builtin.TEXT_MAP, new HeadersMapInjectAdapter(new HttpHeaders()));
            if (parentSpan == null) {
                spanBuilder = tracer.buildSpan("getLogingDto");
            } else {
                spanBuilder = tracer.buildSpan("getLoginDto").asChildOf(parentSpan);
            }
        } catch (IllegalArgumentException e) {
            spanBuilder = tracer.buildSpan("getLoginDto");
        }
        */
/*
        Span span = spanBuilder.start();
         try (Scope scope = spanBuilder.withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER).startActive(true))
         {
             span.log("user name Finance Service:" + username);
             Tags.SPAN_KIND.set(tracer.activeSpan(), Tags.SPAN_KIND_SERVER);
             Tags.HTTP_METHOD.set(tracer.activeSpan(), "GET");

         } finally {
            span.finish();
        }

*/
        System.out.println("login--:"+username+"="+farazKey);
        System.out.println("login2--:"+loginDTORepository.getRoleForLogin(username, farazKey).get());

        return loginDTORepository.getRoleForLogin(username, farazKey).get();

    }

    public Set<GrantedAuthority> getGrantedAuthoritiesList(Collection<RolePermission> rolePermissions) {
        Set<GrantedAuthority> grantedAuthoritiesList = new HashSet<>();
        System.out.println("role="+rolePermissions.size());
        for (RolePermission rolePermission : rolePermissions) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rolePermission.getPermissionId().getEnTitle());
            grantedAuthoritiesList.add(grantedAuthority);
        }
        return grantedAuthoritiesList;
    }
}
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
