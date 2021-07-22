package com.camel.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
class RestApi extends RouteBuilder {

    @Autowired
    private Environment env;

    @Autowired
	private MyService myService; 
	
    @Override
    public void configure() {
        restConfiguration()
                .contextPath("/test")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "my datediff")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port(env.getProperty("server.port", "12345"))
                .bindingMode(RestBindingMode.json);
   	 onException(Exception.class)
    .log("onException_processing_exception:" + this.exceptionMessage().toString())
    .handled(true);
       rest("/dateInput").description("test dateInput")
                .get("/{input}").description("get date input")
                .route().routeId("count-friday-in-datediff")
                .bean(myService)
                .log("count Friday between dateInput to currentDate :${body}");
    }
}
