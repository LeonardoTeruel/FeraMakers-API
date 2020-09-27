package com.teruel.feramakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import sun.java2d.pipe.hw.ContextCapabilities;

public class MailActivationBuilderService {

    @Autowired
    TemplateEngine templateEngine;

    String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailUserActivationTemplate", context);
    }
}
