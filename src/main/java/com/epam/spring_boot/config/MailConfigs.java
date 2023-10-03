package com.epam.spring_boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mailing")
public class MailConfigs {
    private String server;

    private String user;

    private String password;
    private String enabletls;

    @Override
    public String toString() {
        return "MailConfigs{" +
                "server='" + server + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", enabletls='" + enabletls + '\'' +
                '}';
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabletls() {
        return enabletls;
    }

    public void setEnabletls(String enabletls) {
        this.enabletls = enabletls;
    }
}
