package com.education;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatasourceLogger {
    @Value("${spring.datasource.url}")
    private String url;

    @PostConstruct public void log() {
        System.out.println("Datasource URL: " + url);
    }
}
