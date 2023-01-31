package com.farapayesh.gatewayservice;

import io.jaegertracing.Configuration;
import io.opentracing.util.GlobalTracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
    @Bean
    public io.opentracing.Tracer initTracer() {
        Configuration config = io.jaegertracing.Configuration.fromEnv("Gservice")
                .withSampler(
                        io.jaegertracing.Configuration.SamplerConfiguration.fromEnv()
                                .withType("const")
                                .withParam(1))
                .withReporter(
                        io.jaegertracing.Configuration.ReporterConfiguration.fromEnv()
                                .withLogSpans(true)
                                .withFlushInterval(1000)
                                .withMaxQueueSize(10000)
                                .withSender(
                                        io.jaegertracing.Configuration.SenderConfiguration.fromEnv()
                                                .withAgentHost("192.168.1.5")
                                                .withAgentPort(6831)
                                ));
        GlobalTracer.register(config.getTracer());
        return GlobalTracer.get();

    }

}
