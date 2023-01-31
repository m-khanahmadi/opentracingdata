package com.faraz.finance.tools;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class OpenTelemetryTracer {
 /*    public final String jaegerIP = "localhost";//""192.168.99.100";
     public final int jaegerPort = 16686;


     private JaegerGrpcSpanExporter jaegerExporter;
     public  final Tracer tracer = OpenTelemetry.getTracerProvider().get("FinanceService-OT","Faraz:1.0.0");
 //    TracerSdkProvider tracerProvider = OpenTelemetrySdk.getTracerProvider();



     @PostConstruct
     public void init()
     {
          System.out.println("================================================================");
// Create a channel towards Jaeger end point
          ManagedChannel jaegerChannel =
                  ManagedChannelBuilder.forAddress(jaegerIP, jaegerPort).usePlaintext().build();
          // Export traces to Jaeger
          this.jaegerExporter =
                  JaegerGrpcSpanExporter.newBuilder()
                          .setServiceName("example")
                          .setChannel(jaegerChannel)
                          .setDeadline(3000)
                          .build();

          SpanProcessor spanProcessor =
                  SimpleSpansProcessor.newBuilder(JaegerGrpcSpanExporter.newBuilder()
                          .setServiceName("getting_started")
                          .setChannel(ManagedChannelBuilder.forAddress(
                                  jaegerIP, jaegerPort).usePlaintext().build())
                          .build()).build();
          OpenTelemetrySdk.getTracerProvider().addSpanProcessor(spanProcessor);

          // Set to process the spans by the Jaeger Exporter
//          OpenTelemetrySdk.getTracerProvider()
  //                .addSpanProcessor(SimpleSpansProcessor.newBuilder(this.jaegerExporter));

}

 /*
          TraceConfig alwaysOn = TraceConfig.getDefault().toBuilder().setSampler(
                  Samplers.alwaysOn()
          ).build();
          tracerProvider.updateActiveTraceConfig(
                  alwaysOn
          );
          ManagedChannel jaegerChannel = ManagedChannelBuilder.forAddress(jaegerIP, jaegerPort).build();
          jaegerExporter = JaegerGrpcSpanExporter.newBuilder().
                  setServiceName("Finance").setChannel(jaegerChannel).setDeadline(30).build();
          // Get the tracer
          OpenTelemetrySdk.getTracerProvider()
                  .addSpanProcessor(SimpleSpansProcessor.newBuilder(jaegerExporter).build());

 */
    // }

}
