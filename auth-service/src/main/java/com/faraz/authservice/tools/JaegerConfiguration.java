package com.faraz.authservice.tools;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.metrics.NoopMetricsFactory;
import io.jaegertracing.internal.reporters.RemoteReporter;
import io.jaegertracing.internal.reporters.RemoteReporter.Builder;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import io.jaegertracing.thrift.internal.senders.UdpSender;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextRefreshedEvent;


//@Configuration
public class JaegerConfiguration {//implements ApplicationListener<ContextRefreshedEvent> {

/*
    private static final int JAEGER_PORT = 6831;
    private static final String JAEGER_HOST = "localhost";
    private static final String JAEGER_SERVICE_NAME = "my-awesome-jaeger";
    private static final double SAMPLING_RATE = 0.5;
  //  @Autowired
   // private Tracer tracer;


    @Bean
    @Primary
    public Tracer jaegerTracer(RemoteReporter remoteReporter) {
        return new JaegerTracer.Builder(JAEGER_SERVICE_NAME)
                .withReporter(remoteReporter)
                .withMetricsFactory(new NoopMetricsFactory()).withSampler(new ProbabilisticSampler(SAMPLING_RATE))
                .build();
    }

    @Bean
    public RemoteReporter remoteReporter() {
        return new Builder().withSender(new UdpSender(JAEGER_HOST, JAEGER_PORT, 0)).build();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!GlobalTracer.isRegistered()) {
            GlobalTracer.register(tracer());
        }
    }

 */
}