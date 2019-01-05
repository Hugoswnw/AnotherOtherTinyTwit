package fr.mash.tinytwit;

import com.googlecode.objectify.ObjectifyService;
import fr.mash.tinytwit.ressources.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Bootstrapper implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ObjectifyService.register(User.class);
        ObjectifyService.register(Message.class);
        ObjectifyService.register(MessageIndex.class);
        ObjectifyService.register(HashtagIndex.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}