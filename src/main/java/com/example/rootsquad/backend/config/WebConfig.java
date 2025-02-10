package com.example.rootsquad.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // Allow CORS for /public/api
        registry.addMapping("/public/api/**")        // Set the pathPattern
                .allowedOrigins("http://127.0.0.1:5500")              // Restricted requests from: "http://227.0.0.1:5501"
                .allowedMethods("GET", "POST")                          // Allowable HTTP methods
                .allowCredentials(false)                                // Credentials aren't typically needed for public API
                .allowedHeaders(
                        "Content-Type",                                     // Media type of request body (e.g. application/json)
                        "Accept",                                           // Expected response format (e.g. application/json)
                        "X-Requested-With",                                 // Identify AJAX requests (optional, common in web apps)
                        "Cache-Control",                                    // Controls cache behavior (e.g. no-cache)
                        "Origin",                                           // Sent by browser enforce CORS policies
                        "User-Agent")                                       // Identify sender (optional, for logging or analytics)
                .maxAge(3600);                                          // Set max age (sec) for CORS response cached by browser


        // Allow CORS for /auth/api
        registry.addMapping("/auth/api/**")          // Set the pathPattern
                .allowedOrigins("http://127.0.0.1:5500")                // Restricted requests from: "http://227.0.0.1:5501"
                .allowedMethods("GET", "POST", "PUT")                   // Allowable HTTP methods
                .allowCredentials(true)                                 // Allow credentials: cookies, auth headers, TLS certs
                .allowedHeaders(
                        "Authorization",                                    // IMPORTANT: pass auth credentials (e.g. JWT, API keys)
                        "Content-Type",                                     // Media type of request body (e.g. application/json)
                        "Accept",                                           // Expected response format (e.g. application/json)
                        "X-Requested-With",                                 // Identify AJAX requests (optional, common in web apps)
                        "Cache-Control",                                    // Controls cache behavior (e.g. no-cache)
                        "Origin",                                           // Sent by browser enforce CORS policies
                        "User-Agent",                                       // Identify sender (optional, for logging or analytics)
                        "X-CSRF-Token",                                     // IMPORTANT: Token passed to validate against CSRF
                        "Origin",                                           // Indicate domain from which the request originates
                        "Referer")                                          // Identify URL page that referred the request (source)
                .maxAge(3600);                                          // Set max age (sec) for CORS response cached by browser

        // Allow CORS for /admin/api
        registry.addMapping("/admin/api/**")         // Set the pathPattern
                .allowedOrigins("http://127.0.0.1:5500")                // Restricted requests from: "http://227.0.0.1:5501"
                .allowedMethods("GET", "POST", "PUT", "DELETE")         // Allow ALL CRUD operations
                .allowCredentials(true)                                 // Allow credentials: cookies, auth headers, TLS certs
                .allowedHeaders(
                        "Authorization",                                    // IMPORTANT: pass auth credentials (e.g. JWT, API keys)
                        "Content-Type",                                     // Media type of request body (e.g. application/json)
                        "Accept",                                           // Expected response format (e.g. application/json)
                        "X-Requested-With",                                 // Identify AJAX requests (optional, common in web apps)
                        "Cache-Control",                                    // Controls cache behavior (e.g. no-cache)
                        "Origin",                                           // Sent by browser enforce CORS policies
                        "User-Agent",                                       // Identify sender (optional, for logging or analytics)
                        "X-CSRF-Token",                                     // IMPORTANT: Token passed to validate against CSRF
                        "Origin",                                           // Indicate domain from which the request originates
                        "Referer")                                          // Identify URL page that referred the request (source)
                .maxAge(3600);                                          // Set max age (sec) for CORS response cached by browser

        // Allow CORS for /restricted/api
        registry.addMapping("/restricted/api/**")    // Set the pathPattern
                .allowedOrigins("http://127.0.0.1:5500")                // Restricted requests from: "http://227.0.0.1:5501"
                .allowedMethods("GET", "POST", "PUT")                   // Allow ALL CRUD operations
                // TODO change back to true after testing
                .allowCredentials(true)                                 // Allow credentials: cookies, auth headers, TLS certs
                .allowedHeaders(
                        "Authorization",                                    // IMPORTANT: pass auth credentials (e.g. JWT, API keys)
                        "Content-Type",                                     // Media type of request body (e.g. application/json)
                        "Accept",                                           // Expected response format (e.g. application/json)
                        "X-Requested-With",                                 // Identify AJAX requests (optional, common in web apps)
                        "Cache-Control",                                    // Controls cache behavior (e.g. no-cache)
                        "Origin",                                           // Sent by browser enforce CORS policies
                        "User-Agent",                                       // Identify sender (optional, for logging or analytics)
                        "X-CSRF-Token",                                     // IMPORTANT: Token passed to validate against CSRF
                        "Origin",                                           // Indicate domain from which the request originates
                        "Referer")                                          // Identify URL page that referred the request (source)
                .maxAge(3600);                                          // Set max age (sec) for CORS response cached by browser
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapping URL path to the external directory
        registry.addResourceHandler("/" + uploadDir + "/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }
}
