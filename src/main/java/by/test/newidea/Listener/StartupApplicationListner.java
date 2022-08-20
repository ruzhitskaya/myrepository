package by.test.newidea.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupApplicationListner implements ServletContextListener  {
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContextListener.super.contextDestroyed(sce);
//    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is Up");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context is DOWN");
    }
}
