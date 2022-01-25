package ru.mrs.docs;

import freemarker.cache.FileTemplateLoader;
import org.apache.commons.lang3.NotImplementedException;
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
import ru.mrs.base.service.file.Vfs;
import ru.mrs.base.service.file.VfsAbstract;
import ru.mrs.docs.frontend.GreetingServlet;
import ru.mrs.docs.frontend.LoginServlet;
import ru.mrs.docs.frontend.MainServlet;
import ru.mrs.docs.service.account.AccountServiceImpl;
import ru.mrs.docs.service.db.MainService;
import ru.mrs.docs.service.db.MainServiceImpl;
import ru.mrs.docs.service.db.entity.MainColumns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

// https://zetcode.com/java/jetty/embedded/
// https://stackoverflow.com/questions/32378028/embedded-jetty-handling-urls-to-serve-content
@SuppressWarnings("unchecked")
public class Embedded extends EmbeddedConfiguration {

    private static final Logger LOGGER = configureLogger(Embedded.class);
    private static final Map<Object, Object> context = new HashMap();

    static {
        LOGGER.info("Context count : " + context.size());
        context.put(Vfs.class, new VfsImpl("content"));
        context.putAll(loadProperties());
        context.put(freemarker.template.Configuration.class, configureFreemarker(
                (Vfs)context.get(Vfs.class),
                context.get(PropertyKeys.TEMPLATE_BASE) ) );
        context.put(
                AccountService.class, new AccountServiceImpl(
                        context.get(PropertyKeys.DEFAULT_USER),
                        context.get(PropertyKeys.DEFAULT_PROF) ) );
        context.put(
                MainService.class, new MainServiceImpl(context) );
        context.put(MainColumns.class, beautifyMainColumns());
        LOGGER.info("Context count : " + context.size());
        context.entrySet().forEach(entry -> { LOGGER.info(entry.getKey() + " " + entry.getValue()); });
    }

    public static void main(String[] args) throws Exception {
//        System.setProperty("DEBUG", "true");
        /*AccountServiceControllerMBean serverStatistics = new AccountServiceController(accountService);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ServiceManager:type=AccountServiceController");
        mBeanServer.registerMBean(serverStatistics, name);*/


        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet( new ServletHolder( new MainServlet(context) ), MainServlet.PATH_SPEC );
        servletContextHandler.addServlet( new ServletHolder( new LoginServlet(context) ), LoginServlet.PATH_SPEC );
        servletContextHandler.addServlet( new ServletHolder( new GreetingServlet(context) ), GreetingServlet.PATH_SPEC );

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        Vfs vfs = (Vfs) context.get(Vfs.class);
        String resourceBase = vfs.getAbsolutePath(context.get(PropertyKeys.RESOURCE_BASE).toString());
        resource_handler.setResourceBase(resourceBase);

//        ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
//        errorHandler.addErrorPage(404, "missing.html");
//        servletContextHandler.setErrorHandler(errorHandler);

        /*ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(404, "/index.html");
        servletContextHandler.setErrorHandler(errorHandler);*/
        Handler[] handlers = {
                resource_handler
                ,servletContextHandler
//                ,errorHandler
        };

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(handlers);

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

    protected static Map<PropertyKeys, String> loadProperties() {
        /*EnumSet<PropertyKeys> RESOURCE_LOCATED = EnumSet.of(
                PropertyKeys.RESOURCE_BASE
                ,PropertyKeys.DEFAULT_PROF
                ,PropertyKeys.CONTEXT_PATH
                ,PropertyKeys.DB_DATA_PATH );
        EnumSet<PropertyKeys> SOURCE_LOCATED = EnumSet
                .noneOf(
                //.of(
                PropertyKeys.class );*/
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
        Map<PropertyKeys, String> mapEnumString = new HashMap<>();
        for (PropertyKeys propertyKey : PropertyKeys.values()) {
            String propertyString = properties.getProperty(propertyKey.toString());
            if (propertyString != null) {
                /*if (RESOURCE_LOCATED.contains(propertyKey)) {
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
                }*/
                mapEnumString.put(propertyKey, propertyString);
            }
        }
        mapEnumString.put(
                PropertyKeys.OS_WINDOWS,
                System.getProperty("os.name").startsWith("Windows") ? "any win" : null );
        return mapEnumString;
    }

    protected static freemarker.template.Configuration configureFreemarker (Vfs vfs, Object file) {
        freemarker.template.Configuration freemarkerTemplateConfiguration
                 = new freemarker.template.Configuration(
                         freemarker.template.Configuration.VERSION_2_3_27 );
        FileTemplateLoader fileTemplateLoader = null;
        try {
            fileTemplateLoader = new FileTemplateLoader(
                    vfs.getFile(file.toString() ) );
            freemarkerTemplateConfiguration
                    .setTemplateLoader(
                            fileTemplateLoader );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freemarkerTemplateConfiguration;
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

class VfsImpl extends VfsAbstract implements Vfs {

    public VfsImpl(String root) {
        super(root);
    }

    @Override
    public File getFile(String file) {
        return new File(getRoot() + "/" + file);
    }

    @Override
    public boolean ifExist(String path) {
        throw new NotImplementedException("boolean ifExist(String path)");
//        return false;
    }

    @Override
    public boolean isDirectory(String path) {
        throw new NotImplementedException("boolean isDirectory(String path)");
//        return false;
    }

    @Override
    public String getAbsolutePath(String file) {
//        throw new NotImplementedException("String getAbsolutePath(String file)");
        return new File(getRoot() + "/" + file).getAbsolutePath();
    }

    @Override
    public byte[] getBytes(String file) {
        throw new NotImplementedException("return new byte[0];");
//        return new byte[0];
    }

    @Override
    public String getUTF8Text(String path) {
        throw new NotImplementedException("String getUTF8Text(String path)");
//        return null;
    }

    @Override
    public Iterator<String> getIterator(String startDir) {
        throw new NotImplementedException("Iterator<String> getIterator(String startDir)");
//        return null;
    }

    @Override
    public boolean mkdir(String path) {
        throw new NotImplementedException("boolean mkdir(String path)");
//        return false;
    }

    @Override
    public boolean ifExistRoot() {
        throw new NotImplementedException("boolean ifExistRoot()");
//        return false;
    }

    @Override
    public boolean isDirectoryRoot() {
        throw new NotImplementedException("boolean isDirectoryRoot()");
//        return false;
    }

    @Override
    public boolean mkdirRoot() {
        throw new NotImplementedException("boolean mkdirRoot()");
//        return false;
    }

    @Override
    public Iterator<String> getIteratorRoot() {
        throw new NotImplementedException("Iterator<String> getIteratorRoot()");
//        return null;
    }

    @Override
    public boolean remove(String file) {
        throw new NotImplementedException("boolean remove(String file) ");
//        return false;
    }

}
