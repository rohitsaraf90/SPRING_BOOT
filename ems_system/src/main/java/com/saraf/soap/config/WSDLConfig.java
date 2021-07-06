package com.saraf.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WSDLConfig {

    @Bean
    public ServletRegistrationBean requestDispatcher(ApplicationContext applicationContext){
        MessageDispatcherServlet mds = new MessageDispatcherServlet();
        mds.setApplicationContext(applicationContext);
        mds.setTransformWsdlLocations(true);
        return  new ServletRegistrationBean(mds, "/soap/*");
    }

    @Bean
    public XsdSchema employeeSchema(){
        return  new SimpleXsdSchema(new ClassPathResource("Employee.xsd"));
    }


    @Bean(name = "empService")
    public DefaultWsdl11Definition createWSDL(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("EmployeePort");
        defaultWsdl11Definition.setLocationUri("/soap");
        defaultWsdl11Definition.setSchema(xsdSchema);
        defaultWsdl11Definition.setTargetNamespace("http://soap.saraf.com/EMS");
        return defaultWsdl11Definition;
    }
}
