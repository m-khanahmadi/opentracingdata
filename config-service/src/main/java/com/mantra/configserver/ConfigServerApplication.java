package com.mantra.configserver;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.metrics.NoopMetricsFactory;
import io.opentracing.util.GlobalTracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    @Bean
    public io.opentracing.Tracer initTracer() {
        Configuration config = io.jaegertracing.Configuration.fromEnv("config-service")
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
                                              //  .withEndpoint("http://jaeger-collector:14268/api/traces")
                                ));

/*        Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration().withType("const").withParam(1);
        Configuration.ReporterConfiguration reporterConfig = new Configuration.ReporterConfiguration().withLogSpans(true);
        Configuration config = new Configuration("Config-Server").
                withSampler(samplerConfig).withReporter(reporterConfig).withMetricsFactory(new NoopMetricsFactory());


 */
        GlobalTracer.register(config.getTracer());
        return GlobalTracer.get();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
