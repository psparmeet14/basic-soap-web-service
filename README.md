# basic-soap-web-service
This is a basic SOAP web service project with Spring and Spring Boot. 

It is a SOAP COARSE MANAGEMENT web service.

# Web service
- Service delivered over the web
- Application-to-application interaction
- W3C definition: Software system designed to support interoperable (platform independent), machine-to-machine (application-to-application) interaction over a network (HTTP or queue) 

# SOAP Web Service
- It uses XML as the request exchange format.
- It poses restrictions on the format of the XML which is exchanged between the service provider and the service consumer.
- SOAP defines a specific XML request and response format: Envelope, Header, Body

# Data Exchange Format
- SOAP XML Request
- SOAP XML Response

# Transport
- SOAP over HTTP
- SOAP over MQ

# Service Definition
- WSDL (Web Service Definition Language)

# WSDL
- contract between the service provider and service consumer
- defines the endpoints, all operations, request and response structure

# XSD
- XML Schema Definition
- Defines how request and response should look like, what kind of structure should they have, and what are the valid values for each of the elements inside the request.

# JAXB
- Java API for XML Binding
- JAXB maven plugin will take the XSD and generate Java classes

# SOAP web service client
- Wizdler Chrome plugin

# Dependency
- spring-boot-starter-web-services
- spring-ws-core.jar
