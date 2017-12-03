package com.codecika.usermanagement.service;

import com.codecika.usermanagement.exception.UserManagementException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

/**
 * Created by aska on 7/27/17.
 */
@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public JavaMailSender sender;

    @Autowired
    Configuration freemarkerConfig;

    public void sendMessage(String to, String subject, Map<String, Object> model, String templateFile) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        // set location template
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

        try {
            // set template
            Template template = freemarkerConfig.getTemplate(templateFile);

            // set content email
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            // set metadata
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            sender.send(message);
        } catch (IOException e) {
            log.error("ERROR: ", e);
            throw new UserManagementException("Can't send email, check your log for detail information");
        } catch (TemplateException e) {
            log.error("ERROR: ", e);
            throw new UserManagementException("Can't send email, check your log for detail information");
        } catch (MessagingException e) {
            log.error("ERROR: ", e);
            throw new UserManagementException("Can't send email, check your log for detail information");
        }
    }

}