package ru.mrs.docs;


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
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.base.service.file.ObjectWriter;
import ru.mrs.docs.frontend.GreetingServlet;
import ru.mrs.docs.frontend.LoginServlet;
import ru.mrs.docs.frontend.MainServlet;
import ru.mrs.docs.service.account.AccountServiceImpl;
import ru.mrs.docs.service.account.UserProfile;
import ru.mrs.docs.service.db.MainService;
import ru.mrs.docs.service.db.MainServiceImpl;
import ru.mrs.docs.service.db.entity.MainColumns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@SuppressWarnings("unchecked")
public class Embedded extends EmbeddedConfiguration {

    private static final Logger LOGGER = configureLogger(Embedded.class);
    public static final Map<Object, Object> context = new HashMap();

    static {
        context.entrySet().forEach(entry -> { LOGGER.info(entry.getKey() + " " + entry.getValue()); });
        context.putAll(loadProperties());
        context.put(
                AccountService.class, configureAccountService(
                        context.get(PropertyKeys.DEFAULT_USER),
                        context.get(PropertyKeys.DEFAULT_PROF) ) );
        context.put(freemarker.template.Configuration.class, configureFreemarker());
        /*context.put(
                DBService.class, configureDBService(
                        context.get(PropertyKeys.DB_USR_NAME),
                        context.get(PropertyKeys.DB_USR_PASSWORD),
                        context.get(PropertyKeys.DB_DATA_PATH)
                        ) );*/
        context.put(
                MainService.class, configureMainService(
                        context.get(PropertyKeys.DB_USR_NAME),
                        context.get(PropertyKeys.DB_USR_PASSWORD),
                        context.get(PropertyKeys.DB_DATA_PATH)
                ) );
        context.put(MainColumns.class, beautifyMainColumns());
        context.entrySet().forEach(entry -> { LOGGER.info(entry.getKey() + " " + entry.getValue()); });
    }

    public static void main(String[] args) throws Exception {
//        System.setProperty("DEBUG", "true");
        /*AccountServiceControllerMBean serverStatistics = new AccountServiceController(accountService);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ServiceManager:type=AccountServiceController");
        mBeanServer.registerMBean(serverStatistics, name);*/

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        servletContextHandler.addServlet(new ServletHolder( new WebSocketChatServlet() ), WebSocketChatServlet.PATH);

        servletContextHandler.addServlet( new ServletHolder( new MainServlet() ), MainServlet.PATH_SPEC );
        servletContextHandler.addServlet( new ServletHolder( new LoginServlet() ), LoginServlet.PATH_SPEC );
        servletContextHandler.addServlet( new ServletHolder( new GreetingServlet() ), GreetingServlet.PATH_SPEC );
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase( context.get(PropertyKeys.RESOURCE_BASE).toString() );

//        ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
//        errorHandler.addErrorPage(404, "missing.html");
//        servletContextHandler.setErrorHandler(errorHandler);
//        servletContextHandler.setContextPath(context.get(PropertyKeys.context_path).toString());

        Handler[] handlers = {
                resource_handler
                ,servletContextHandler
//                ,errorHandler
        };

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(handlers);

        /*ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(404, "/index.html");
        servletContextHandler.setErrorHandler(errorHandler);*/

//        int port = ;
        Server server = new Server( Integer.parseInt( Embedded.context.get( PropertyKeys.SERVER_PORT ).toString() ) );
//        server.setHandler(servletContextHandler);
        server.setHandler(handlerList);
        server.start();
        LOGGER.info("Server started");
        server.join();
    }

}

//@SuppressWarnings( "deprecation" )
class EmbeddedConfiguration {

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

    protected static Map loadProperties() {
        EnumSet<PropertyKeys> RESOURCE_LOCATED = EnumSet.of(
                PropertyKeys.RESOURCE_BASE
                ,PropertyKeys.DEFAULT_PROF
                ,PropertyKeys.CONTEXT_PATH
                ,PropertyKeys.DB_DATA_PATH );
        EnumSet<PropertyKeys> SOURCE_LOCATED = EnumSet
                .noneOf(
                //.of(
                PropertyKeys.class );
        Map<PropertyKeys, String> mapEnumString = new HashMap<>();
        Properties properties = new Properties();
        for (String propertyFile : new String[]{"docs-hide.properties", "docs.properties"}) {
            try (InputStream input = EmbeddedConfiguration.class.getClassLoader().getResourceAsStream(propertyFile)) {
                properties.load(input);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        boolean osWindows = System.getProperty("os.name").startsWith("Windows");
        for (PropertyKeys propertyKey : PropertyKeys.values()) {
            String propertyString = properties.getProperty(propertyKey.toString());
            if (propertyString != null) {
                if (RESOURCE_LOCATED.contains(propertyKey)) {
                    ClassLoader classLoader = EmbeddedConfiguration.class.getClassLoader();
                    String path = classLoader.getResource(propertyString).getPath();
                    propertyString = EmbeddedConfiguration.class
                            .getClassLoader()
                            .getResource(propertyString)
                            .getPath();
                    if (osWindows && propertyString.startsWith("/")) {
                        propertyString = propertyString.substring(1);
                    }
                } else {
                    if (SOURCE_LOCATED.contains(propertyKey)) {
                        // https://stackoverflow.com/questions/320542/how-to-get-the-path-of-a-running-jar-file
                        propertyString = EmbeddedConfiguration.class
                                .getProtectionDomain()
                                .getCodeSource()
                                .getLocation()
                                .getPath() + propertyString;
                        if (osWindows) {
                            propertyString = propertyString.substring(1);
                        }
                    }
                }
                mapEnumString.put(propertyKey,propertyString);
            }
        }
        return mapEnumString;
    }

    protected static freemarker.template.Configuration configureFreemarker (){
        freemarker.template.Configuration
                configuration = new freemarker.template.Configuration(
                freemarker.template.Configuration.VERSION_2_3_27);
        try {
            configuration
                    .setDirectoryForTemplateLoading(
                            new File(
                                    EmbeddedConfiguration.class
                                            .getClassLoader()
                                            .getResource("templates")
                                            .getPath() ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }

    protected static AccountService<UserProfile> configureAccountService(Object defaultLogin, Object defaultProfile) {
        UserProfile userProfile = (UserProfile)ObjectWriter.read(defaultProfile.toString());
        return new AccountServiceImpl(defaultLogin.toString(), userProfile);
    }

    /*protected static DBService configureDBService(Object name, Object pass, Object path) {
        return new DBServiceImpl(name.toString(), pass.toString(), path.toString());
    }*/
    protected static MainService configureMainService(Object name, Object pass, Object path) {
        return new MainServiceImpl(name.toString(), pass.toString(), path.toString());
    }
    protected static Collection<String> beautifyMainColumns() {
        Map<MainColumns, String> result = new LinkedHashMap<>();
        result.put(MainColumns.ID, "Iden.");
        result.put(MainColumns.URL_INPUT, "External URL on received document");
        result.put(MainColumns.GEN_ORG_NUMB, "G.O.N.");
        result.put(MainColumns.GEN_ORG_DATE, "G.O.D.");
        result.put(MainColumns.OUTPUT_NUMB, "O.N.");
        result.put(MainColumns.OUTPUT_DATE, "O.D.");
        result.put(MainColumns.FROM_OWNER, "Owner of document");
        result.put(MainColumns.INPUT_DATE, "I.D.");
        result.put(MainColumns.INPUT_NUMB, "I.N.");
        result.put(MainColumns.WORKER, "Worker took over");
        result.put(MainColumns.HAND_PASS, "H.P.");
        result.put(MainColumns.ANSWER_COMP, "A.C.");
        result.put(MainColumns.ANSWER_DATE, "A.D.");
        result.put(MainColumns.ANSWER_NUMB, "A.N.");
        result.put(MainColumns.URL_OUTPUT, "External URL on sending document");
        result.put(MainColumns.NOTE, "Note");
        return result.values();
    }

}
