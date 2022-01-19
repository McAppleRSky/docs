package ru.mrs.docs.service.db.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mrs.base.service.db.Executor;
import ru.mrs.docs.PropertyKeys;
import ru.mrs.docs.service.db.entity.MainColumns;
import ru.mrs.docs.service.db.entity.OldTableColumns;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTableBehaviour {

    boolean osWindows = System.getProperty("os.name").startsWith("Windows");
    String srcPath = MainTableBehaviour.class
            .getProtectionDomain()
            .getCodeSource()
            .getLocation()
            .getPath();

    String credentialsProperties = "docs-hide.properties", pathProperties = "docs.properties";
    ClassLoader classLoader = MainTableBehaviour.class.getClassLoader();
    String dbUrl, dbUser, dbPass;
    {
        Properties properties = new Properties();

        if (osWindows && srcPath.startsWith("/")) {
            srcPath = srcPath.substring(1);
        }
        try (
                InputStream inputCredentials = classLoader.getResourceAsStream(credentialsProperties);
                InputStream inputPath = classLoader.getResourceAsStream(pathProperties) )
        {
            properties.load(inputCredentials);
            properties.load(inputPath);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbUrl = "jdbc:h2:" + srcPath + properties.getProperty(PropertyKeys.DB_DATA_PATH.toString());
        dbUser = properties.getProperty(PropertyKeys.DB_USR_NAME.toString());
        dbPass = properties.getProperty(PropertyKeys.DB_USR_PASSWORD.toString());
    }

    @BeforeEach
    void createMainTest() {
        String queryCreateTemplate = "CREATE TABLE main (\n"+
                "\t%s,\n\t%s,\n\t%s,\n\t%s,\n\t" +
                  "%s,\n\t%s,\n\t%s,\n\t%s,\n\t" +
                  "%s,\n\t%s,\n\t%s,\n\t%s,\n\t" +
                  "%s,\n\t%s,\n\t%s,\n\t%s );"
                ;
        String queryCreate = String.format(
                queryCreateTemplate,
                MainColumns.ID
                        + " bigint PRIMARY KEY NOT NULL auto_increment",
                MainColumns.URL_INPUT
                        + " varchar(255)"
//                        + "NOT NULL default ''"
                ,
                MainColumns.GEN_ORG_NUMB
                        + " varchar(100)",
                MainColumns.GEN_ORG_DATE
                        + " DATE",
                MainColumns.OUTPUT_NUMB
                        + " varchar(100)",
                MainColumns.OUTPUT_DATE
                        + " DATE",
                MainColumns.FROM_OWNER
                        + " varchar(100)"
//                        + " NOT NULL default ''"
                ,
                MainColumns.INPUT_DATE
                        + " DATE",
                MainColumns.INPUT_NUMB
                        + " varchar(100)",
                MainColumns.WORKER
                        + " varchar(100)"
//                        + "NOT NULL default ''"
                ,
                MainColumns.HAND_PASS
                        + " DATE",
                MainColumns.ANSWER_COMP
                        + " DATE",
                MainColumns.ANSWER_DATE
                        + " DATE",
                MainColumns.ANSWER_NUMB
                        + " varchar(100)",
                MainColumns.URL_OUTPUT
                        + " varchar(255)"
//                        + "NOT NULL default ''"
                ,
                MainColumns.NOTE
                        + " varchar(255)"
        );
        System.out.println(queryCreate);
        queryCreate = queryCreate
                .replace("\n","")
                .replace("\t", "");
        try(Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            Executor executor = new Executor(connection);
            executor.execUpdate(queryCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Test
    void insertValues() {
        String queryInsertTemplate = "INSERT INTO main (" +
                "%s, %s, %s, " +
                "%s, %s, %s, " +
                "%s, %s, %s, " +
                "%s, %s, %s, " +
                "%s, %s, %s) \n\t\t" +
                "SELECT " +
                "old.%s, old.%s, old.%s, " +
                "old.%s, old.%s, old.%s, " +
                "old.%s, old.%s, old.%s, " +
                "old.%s, old.%s, old.%s, " +
                "old.%s, old.%s, old.%s \n\t\t" +
                "FROM OLD_MAIN_TABLE AS old \n\t\t" +
                "ORDER BY old.%s;" ;
        String queryInsert = String.format(
                queryInsertTemplate,
                MainColumns.URL_INPUT,  MainColumns.GEN_ORG_NUMB,MainColumns.GEN_ORG_DATE,
                MainColumns.OUTPUT_NUMB,MainColumns.OUTPUT_DATE, MainColumns.FROM_OWNER,
                MainColumns.INPUT_DATE, MainColumns.INPUT_NUMB,  MainColumns.WORKER,
                MainColumns.HAND_PASS,  MainColumns.ANSWER_COMP, MainColumns.ANSWER_DATE,
                MainColumns.ANSWER_NUMB,MainColumns.URL_OUTPUT,  MainColumns.NOTE
                ,
//                OldTableColumns.ID,
                OldTableColumns.URL_SED_INPUT,  OldTableColumns.GENERAL_ORG_NUMB, OldTableColumns.GENERAL_ORG_DATE,
                OldTableColumns.OUTPUT_NUMB,    OldTableColumns.OUTPUT_DATE,      OldTableColumns.FROM_OWNER,
                OldTableColumns.INPUT_DATE,     OldTableColumns.INPUT_NUMB,       OldTableColumns.WORKER,
                OldTableColumns.HAND_PASS,      OldTableColumns.ANSWER_COMPLETE,  OldTableColumns.ANSWER_DATE,
                OldTableColumns.ANSWER_NUMB,    OldTableColumns.URL_SED_OUTPUT,   OldTableColumns.NOTE
                , OldTableColumns.ID
        );
        System.out.println(queryInsert);
        queryInsert = queryInsert
                .replace("\n","")
                .replace("\t", "");
        try(Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            Executor executor = new Executor(connection);
            executor.execUpdate(queryInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(false);
    }

}
