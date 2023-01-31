package com.faraz.authservice.tools;

import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class OpenTelemetryTracer {
 /*    public final String jaegerIP = "localhost";//""192.168.99.100";
     public final int jaegerPort = 16686;
     private JaegerGrpcSpanExporter jaegerExporter;
     public  final Tracer tracer = OpenTelemetry.getTracerProvider().get("instrumentation-library-name","semver:1.0.0");
     //TracerSdkProvider tracerProvider = OpenTelemetrySdk.getTracerProvider();

     @PostConstruct
     public void init()
     {
          ManagedChannel jaegerChannel = ManagedChannelBuilder.forAddress(jaegerIP, jaegerPort).build();
          jaegerExporter = JaegerGrpcSpanExporter.newBuilder().
                  setServiceName("Finance").setChannel(jaegerChannel).setDeadline(30).build();
          // Get the tracer
          OpenTelemetrySdk.getTracerProvider()
                  .addSpanProcessor(SimpleSpansProcessor.newBuilder(jaegerExporter).build());
     }

  */
}
