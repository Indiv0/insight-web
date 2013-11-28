package in.nikitapek.insightweb.listener;

import in.nikitapek.insightweb.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("[insight-web] Loading configuration values.");
        Configuration.initialize();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
