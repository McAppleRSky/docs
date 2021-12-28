package ru.mrs.docs.main;

import freemarker.template.Configuration;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.mrs.docs._1_frontend.GreetingServlet;
import ru.mrs.docs._1_frontend.MainServlet;
import ru.mrs.docs._1_frontend.SignInServlet;
import ru.mrs.docs._2_service.AccountService;
import ru.mrs.docs._2_service.AccountServiceImpl;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Main extends MainConfiguration {

    private static final Logger LOGGER = configureLogger(Main.class);
    public static final Map<Object, Object> context = new HashMap();

    static {
        context.put(ConfigHide.class, configureModuleHide());
        context.put(Config.class, configureModule());
        context.put(Configuration.class, configureFreemarker());
        context.put( AccountService.class, configureAccountService( (ConfigHide)context.get(ConfigHide.class) ) );
    }

    public static void main(String[] args) throws Exception {
//        System.setProperty("DEBUG", "true");
        /*AccountServiceControllerMBean serverStatistics = new AccountServiceController(accountService);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ServiceManager:type=AccountServiceController");
        mBeanServer.registerMBean(serverStatistics, name);*/

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet( new ServletHolder( new MainServlet() ), MainServlet.URL);
        servletContextHandler.addServlet( new ServletHolder( new SignInServlet() ), SignInServlet.URL);
        servletContextHandler.addServlet( new ServletHolder( new GreetingServlet() ), GreetingServlet.URL);

        /*ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(404, "/index.html");
        servletContextHandler.setErrorHandler(errorHandler);*/

        Config config = (Config) (ru.mrs.docs.main.Main.context.get(Config.class));
        int port = Integer.parseInt(config.getProperty("port"));
        Server server = new Server(port);
        server.setHandler(servletContextHandler);
        server.start();
        LOGGER.info("Server started");
        server.join();
    }

}
