package in.nikitapek.insightweb.listener;

import in.nikitapek.insightweb.Configuration;
import in.nikitapek.insightweb.JDBC;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("[insight-web] Loading configuration values.");
        Configuration.initialize();
        System.out.println("[insight-web] Retrieving authorization realm.");
        JDBC.initialize(event.getServletContext());
        System.out.println("[insight-web] Modifying authorization realm to use provided configuration values.");
        JDBC.configureRealm(JDBC.realm, Configuration.getProperty("dbUsername"), Configuration.getProperty("dbPassword"), Configuration.getProperty("dbURL"), Configuration.getProperty("dbPort"), Configuration.getProperty("dbName"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}