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
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.base.service.file.ObjectWriter;
import ru.mrs.docs.frontend.LoginServlet;
import ru.mrs.docs.service.AccountServiceImpl;
import ru.mrs.docs.service.UserProfile;

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
        context.putAll(configureContext());
        context.put(
                AccountService.class, configureAccountService(
                        context.get(PropertyKeys.default_user),
                        context.get(PropertyKeys.default_prof) ) );
        context.put(freemarker.template.Configuration.class, configureFreemarker());
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

//        servletContextHandler.addServlet( new ServletHolder( new MainServlet() ), MainServlet.URL);
        servletContextHandler.addServlet( new ServletHolder( new LoginServlet() ), LoginServlet.URL);
//        servletContextHandler.addServlet( new ServletHolder( new GreetingServlet() ), GreetingServlet.URL);
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase( context.get(PropertyKeys.base_html).toString() );

//        ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
//        errorHandler.addErrorPage(404, "/missing.html");
//        servletContextHandler.setErrorHandler(errorHandler);

        Handler[] handlers = {
                resource_handler
                ,servletContextHandler
                //,errorHandler
        };

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(handlers);

        /*ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(404, "/index.html");
        servletContextHandler.setErrorHandler(errorHandler);*/

//        int port = ;
        Server server = new Server( Integer.parseInt( Main.context.get( PropertyKeys.server_port ).toString() ) );
//        server.setHandler(servletContextHandler);
        server.setHandler(handlerList);
        server.start();
        LOGGER.info("Server started");
        server.join();
    }

}

//@SuppressWarnings( "deprecation" )
class MainConfiguration {

    private static final String[]
            PROPERTY_FILES = {"docs-hide.properties", "docs.properties"};
    private static final EnumSet<PropertyKeys>
            RESOURCES_PROPERTIES = EnumSet.of(PropertyKeys.base_html, PropertyKeys.default_prof );

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

    protected static Map configureContext() {
        Map<PropertyKeys, String> mapEnumString = new HashMap<>();
        Properties properties = new Properties();
        for (String propertyFile : PROPERTY_FILES) {
            try (InputStream input = MainConfiguration.class.getClassLoader().getResourceAsStream(propertyFile)) {
                properties.load(input);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        for (PropertyKeys propertyKey : PropertyKeys.values()) {
            String propertyString = properties.getProperty(propertyKey.toString());
            if (propertyString != null) {
                if (RESOURCES_PROPERTIES.contains(propertyKey)) {
                    propertyString = MainConfiguration.class.getClassLoader()
                            .getResource(propertyString).getPath();
                }
                mapEnumString.put(propertyKey,propertyString);
            }
        }
        return mapEnumString;
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

    protected static AccountService<UserProfile> configureAccountService(Object defaultLogin, Object defaultProfile) {
        UserProfile userProfile = (UserProfile)ObjectWriter.read(defaultProfile.toString());
        return new AccountServiceImpl(defaultLogin.toString(), userProfile);
    }

}
