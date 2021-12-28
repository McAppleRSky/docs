package ru.mrs.docs.main;

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
import ru.mrs.docs._2_service.AccountService;
import ru.mrs.docs._2_service.AccountServiceImpl;
import ru.mrs.docs._2_service.AccountServiceImpl0;
import ru.mrs.docs._2_service.UserProfile;

import java.io.*;
import java.util.Properties;

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
        LoggerContext context = Configurator
                .initialize(cfgBuilder
                        .add(rootLoggerBuilder)
                        .build());
        return context.getLogger(c.getName());
    }

    protected static ConfigHide configureModuleHide() {
        ConfigHide result = null;
        Properties properties = new Properties();
        try (InputStream input = ConfigHide.class.getClassLoader().getResourceAsStream("configuration/module-hide.properties")) {
            properties.load(input);
            result = new ConfigHide(
                    properties.getProperty("module-name"),
                    properties.getProperty("user-name"),
                    properties.getProperty("user-password"),
                    properties.getProperty("db-usr-name"),
                    properties.getProperty("db-usr-password")
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    protected static Config configureModule() {
        Config result = null;
        Properties properties = new Properties();
        try (InputStream input = MainConfiguration.class.getClassLoader().getResourceAsStream("configuration/module.properties")) {
            properties.load(input);
            result = new Config("port", properties.getProperty("port"));
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

    protected static AccountService configureAccountService(ConfigHide configHide) {
        String defaultUserName = configHide.getUSER_NAME(), defaultPassword = configHide.getUSER_PASSWORD(), defaultEmail = "";
        if (defaultUserName.isEmpty() && defaultPassword.isEmpty()) {
            return new AccountServiceImpl();
        } else {
            return new AccountServiceImpl0(defaultUserName, new UserProfile(defaultUserName, defaultPassword, defaultEmail) );
        }
    }

}
