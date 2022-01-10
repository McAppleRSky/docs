package ru.mrs.docs;

import freemarker.template.Configuration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.mrs.docs._2_service.AccountService;
import ru.mrs.docs._1_frontend.MainServlet;
import ru.mrs.docs._1_frontend.SignInServlet;
import ru.mrs.docs._2_service.AccountServiceFake;
import ru.mrs.docs._2_service.AccountServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("unchecked")
public class Main extends MainConfiguration {

    private static final Logger LOGGER = configureLogger(Main.class);
    public static final Map<Object, Object> context = new HashMap();

    static {
        context.put(ConfigHide.class, configureModuleHide());
        context.put(Config.class, configureModule());
        context.put(Configuration.class, configureFreemarker());
//        context.put( AccountService.class, configureAccountService( (ConfigHide)context.get(ConfigHide.class) ) );
        context.put( AccountService.class, configureAccountService( (AccountService)context.get(AccountServiceImpl.class) ) );
    }

    public static void main(String[] args) throws Exception {
//        System.setProperty("DEBUG", "true");
        /*AccountServiceControllerMBean serverStatistics = new AccountServiceController(accountService);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ServiceManager:type=AccountServiceController");
        mBeanServer.registerMBean(serverStatistics, name);*/

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder( new WebSocketChatServlet() ), WebSocketChatServlet.PATH);

        servletContextHandler.addServlet( new ServletHolder( new MainServlet() ), MainServlet.URL);
        servletContextHandler.addServlet( new ServletHolder( new SignInServlet() ), SignInServlet.URL);
//        servletContextHandler.addServlet( new ServletHolder( new GreetingServlet() ), GreetingServlet.URL);
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase((String)context.get("public_html"));

        /*ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(404, "/index.html");
        servletContextHandler.setErrorHandler(errorHandler);*/

        Config config = (Config) (Main.context.get(Config.class));
        int port = Integer.parseInt(config.getProperty("port"));
        Server server = new Server(port);
        server.setHandler(servletContextHandler);
        server.start();
        LOGGER.info("Server started");
        server.join();
    }

}

//@SuppressWarnings( "deprecation" )
class MainConfiguration {

    protected static Logger configureLogger(Class c){
        ConfigurationBuilder<BuiltConfiguration> cfgBuilder = ConfigurationBuilderFactory.newConfigurationBuilder();
        cfgBuilder
                .add(cfgBuilder
                        .newAppender("Stdout", "CONSOLE")
                        .add(cfgBuilder
                                .newLayout("PatternLayout")
                                .addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"))
                        .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT))
                /*.add(cfgBuilder
                        .newAppender("fileAppender", "FILE")
                        .add(cfgBuilder
                                .newLayout("PatternLayout")
                                .addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"))
                        .addAttribute("fileName", "log/server.log"))*/
        ;
        RootLoggerComponentBuilder rootLoggerBuilder = cfgBuilder
                .newRootLogger(Level.ALL)
                .add(cfgBuilder
                        .newAppenderRef("Stdout")
                        .addAttribute("level", Level.INFO))
                /*.add(cfgBuilder
                        .newAppenderRef("fileAppender")
                        .addAttribute("level", Level.INFO))*/
                ;
        LoggerContext loggerContext = Configurator
                .initialize(cfgBuilder
                        .add(rootLoggerBuilder)
                        .build());
        return loggerContext.getLogger(c.getName());
    }

    protected static ConfigComponent configureContext() {
        ConfigComponent result = null;
        Properties properties = new Properties();
        try (InputStream input = MainConfiguration.class.getClassLoader().getResourceAsStream("doc.properties")) {
            properties.load(input);
            result = new ConfigComponentImpl("server-port", properties.getProperty("server-port"));
            result.setProperty("redis-host", properties.getProperty("redis-host"));
            result.setProperty("redis-port", properties.getProperty("redis-port"));
            result.setProperty("redis-timeout", properties.getProperty("redis-timeout"));
            result.setProperty("chat-room", properties.getProperty("chat-room"));
            result.setProperty("date-time-format", properties.getProperty("date-time-format"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (InputStream input = MainConfiguration.class.getClassLoader().getResourceAsStream("docs-hide.properties")) {
            properties.load(input);
            result.setProperty("user-name", properties.getProperty("user-name"));
            result.setProperty("user-password", properties.getProperty("user-password"));
            result.setProperty("db-usr-name", properties.getProperty("db-usr-name"));
            result.setProperty("db-usr-password", properties.getProperty("db-usr-password"));
            result.setProperty("-", properties.getProperty("-"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    protected static Configuration configureFreemarker (){
        Configuration
                configuration = new freemarker.template.Configuration(
                freemarker.template.Configuration.VERSION_2_3_27);
        try {
            configuration
                    .setDirectoryForTemplateLoading(
                            new File(
                                    MainConfiguration.class
                                            .getClassLoader()
                                            .getResource("templates")
                                            .getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }

}

class ConfigComponentImpl implements ConfigComponent {

    private final Map<String, String> properties = new HashMap<>();

    public ConfigComponentImpl(String property, String value) {
        setProperty(property, value);
    }

    @Override
    public String getProperty(String property) {
        return properties.get(property);
    }

    @Override
    public void setProperty(String property, String value) {
        properties.put(property, value);
    }

}

interface ConfigComponent {

    String getProperty(String property);

    void setProperty(String property, String value);

}
