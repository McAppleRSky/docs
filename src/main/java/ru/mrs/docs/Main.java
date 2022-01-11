package ru.mrs.docs;

import freemarker.template.Configuration;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.mrs.docs.frontend.MainServlet;
import ru.mrs.docs.frontend.SignInServlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("unchecked")
public class Main extends MainConfiguration {

    private static final Logger LOGGER = configureLogger(Main.class);
    public static final Map<Object, Object> context = new HashMap();

    static {
        for (Map.Entry<PropertyKeys, String> PropertyKeyString : configureContext().entrySet()) {
            context.put(PropertyKeyString.getKey(), PropertyKeyString.getValue());
        }
        context.put(freemarker.template.Configuration.class, configureFreemarker());
//        context.put( AccountService.class, configureAccountService( (ConfigHide)context.get(ConfigHide.class) ) );
//        context.put( AccountService.class, configureAccountService( (AccountService)context.get(AccountServiceImpl.class) ) );
    }

    public static void main(String[] args) throws Exception {
//        System.setProperty("DEBUG", "true");
        /*AccountServiceControllerMBean serverStatistics = new AccountServiceController(accountService);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ServiceManager:type=AccountServiceController");
        mBeanServer.registerMBean(serverStatistics, name);*/

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        servletContextHandler.addServlet(new ServletHolder( new WebSocketChatServlet() ), WebSocketChatServlet.PATH);

        servletContextHandler.addServlet( new ServletHolder( new MainServlet() ), MainServlet.URL);
        servletContextHandler.addServlet( new ServletHolder( new SignInServlet() ), SignInServlet.URL);
//        servletContextHandler.addServlet( new ServletHolder( new GreetingServlet() ), GreetingServlet.URL);
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase((String)context.get("public_html"));

        /*ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(404, "/index.html");
        servletContextHandler.setErrorHandler(errorHandler);*/

//        Config config = (Config) (Main.context.get(Config.class));
//        int port = Integer.parseInt(config.getProperty("port"));
//        Server server = new Server(port);
//        server.setHandler(servletContextHandler);
//        server.start();
        LOGGER.info("Server started");
//        server.join();
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

    protected static Map<PropertyKeys, String> configureContext() {
        Map<PropertyKeys, String> propertyKeysStringMap = new HashMap<>();
        Properties properties = new Properties();
        for (String propertyFile : new String[]{"docs-hide.properties", "docs.properties"}) {
            try (InputStream input = MainConfiguration.class.getClassLoader().getResourceAsStream(propertyFile)) {
                properties.load(input);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        EnumSet<PropertyKeys> propertySet = EnumSet.allOf(PropertyKeys.class);
        for (PropertyKeys propertyKey : PropertyKeys.values()) {
            String propertyValue=properties.getProperty(propertyKey.toString());
            if (propertyValue!=null) {
                propertyKeysStringMap.put(propertyKey, propertyValue);
                propertySet.remove(propertyKey);
            }
        }
        return propertyKeysStringMap;
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
