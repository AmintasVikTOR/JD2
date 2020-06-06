package by.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartApplicationListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Application stop!");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Application start!");
    }
}

