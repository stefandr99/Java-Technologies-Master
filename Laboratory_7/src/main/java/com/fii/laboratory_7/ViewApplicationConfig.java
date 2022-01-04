package com.fii.laboratory_7;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

public class ViewApplicationConfig extends ResourceConfig {
    public ViewApplicationConfig() {
        packages("com.fii.laboratory_7.controllers");
        property(FreemarkerMvcFeature.TEMPLATE_BASE_PATH, "META-INF/templates/freemarker");
        register(FreemarkerMvcFeature.class);
    }
}
