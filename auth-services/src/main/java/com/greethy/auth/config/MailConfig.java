package com.greethy.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Configuration class for setting up JavaMailSender using Spring's JavaMailSenderImpl.
 *
 * @author ThanhKien
 */
@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.transport.protocol}")
    private String protocol;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enabled}")
    private boolean enableTls;

    @Value("${spring.mail.properties.mail.smtp.connectionTimeout}")
    private long connectionTimeout;

    /**
     * Configures and creates a JavaMailSender bean using the provided properties.
     *
     * @return An instance of {@code JavaMailSender} configured with the specified properties.
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        mailSender.getJavaMailProperties().put("mail.transport.protocol", protocol);
        mailSender.getJavaMailProperties().put("mail.smtp.auth", auth);
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", enableTls);
        mailSender.getJavaMailProperties().put(".mail.smtp.connectionTimeout", connectionTimeout);

        return mailSender;
    }
}
