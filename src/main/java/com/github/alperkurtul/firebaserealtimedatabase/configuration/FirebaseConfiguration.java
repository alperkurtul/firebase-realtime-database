package com.github.alperkurtul.firebaserealtimedatabase.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseDocumentId;
import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseUserAuthKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfiguration {

    @Bean
    public ObjectMapper firebaseObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
            public boolean hasIgnoreMarker(AnnotatedMember m) {
                if (m.getAllAnnotations().has(FirebaseDocumentId.class)) {
                    return this._findAnnotation(m, FirebaseDocumentId.class) != null;
                } else if (m.getAllAnnotations().has(FirebaseUserAuthKey.class)) {
                    return this._findAnnotation(m, FirebaseUserAuthKey.class) != null;
                } else
                    return false;
            }
        });
        return objectMapper;
    }

}
