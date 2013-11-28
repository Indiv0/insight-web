package in.nikitapek.insightweb.listener;

import in.nikitapek.insightweb.util.Util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("[insight-web] Initializing.");
        Util.initialize();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
