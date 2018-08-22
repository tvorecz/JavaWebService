package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;
import servlets.MirrorServlet;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet(); //create handler-class for handling http-requests
        MirrorServlet mirrorServlet = new MirrorServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS); //create context for servlets
        context.addServlet(new ServletHolder(allRequestsServlet), "/*"); //adding in context servlet putted in servlet-holder
                                                                                  //path to page - "/*" - servlet handles all paths
        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");

        Server server = new Server(8080); //create jetty-server with port 8080
        server.setHandler(context); //setting handler

        server.start(); //start jetty and create threadpoo
        System.out.println("Server started");
        server.join(); //join this thread to jetty

    }
}
