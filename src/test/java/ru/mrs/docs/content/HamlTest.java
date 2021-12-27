package ru.mrs.docs.content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.BeforeAll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HamlTest {

    @BeforeAll
    static void getContent() {
        ClassLoader classLoader = HamlTest.class.getClassLoader();
        String listJsonFilePath = classLoader.getResource("haml/list.json").getPath();
        String hamlInputDirPath = classLoader.getResource("haml/input").getPath();
        String hamlOutputDirPath = classLoader.getResource("haml/output").getPath();

//        String jsonString;
//        HashMap<String, String> ioFiles = new HashMap<>();
        /*try {
//            jsonString = new String(Files.readAllBytes(Paths.get("list.json")));
            Map<String,Object> ioFiles = new ObjectMapper().readValue(new String(Files.readAllBytes(Paths.get("list.json"))), HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Process bash// = null
                ;
//        Map<String, String> ioFiles = new ObjectMapper().readValue( Paths.get("list.json"), HashMap.class );
//        JSONObject jsonObj = null;
        try {
            bash = new ProcessBuilder("bash", "-c", "").start();
            if (bash == null) {
                throw new NullPointerException("Bash process not starting");
            } else {
//                jsonObj = new JSONObject( new String( Files.readAllBytes(Paths.get("list.json")) ) );
                Map<String,Object> ioFiles = new ObjectMapper().readValue(
                        new String(Files.readAllBytes(Paths.get(listJsonFilePath //"list.json"
                                                                    ))),
                        HashMap.class
                );
                Iterator<String> iterator = ioFiles.keySet().iterator();
//                Iterator iterator = jsonObj.keys();
                while(iterator.hasNext()){
                    String hamlFilenameKey = iterator.next()//.toString()
                            ,htmlFilenameValue
                            ;
                    if(!hamlFilenameKey.trim().isEmpty()){
                        htmlFilenameValue = ioFiles.get(hamlFilenameKey).toString();
                        ExternalProgramLauncher("haml",
                                Paths.get(hamlInputDirPath,hamlFilenameKey).toString(),
                                Paths.get(hamlOutputDirPath,htmlFilenameValue).toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void ExternalProgramLauncher(String program,String haml,String html){
        ProcessBuilder procBuilder = new ProcessBuilder(program, haml, html);

        // перенаправляем стандартный поток ошибок на
        // стандартный вывод
        procBuilder.redirectErrorStream(true);

        // запуск программы
        Process process = null;
        try {
            process = procBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // читаем стандартный поток вывода
        // и выводим на экран
        InputStream stdout = process.getInputStream();
        InputStreamReader isrStdout = new InputStreamReader(stdout);
        BufferedReader brStdout = new BufferedReader(isrStdout);

        String line = null;
        while(true) {
            try {
                if (!((line = brStdout.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(line);
        }

        // ждем пока завершится вызванная программа
        // и сохраняем код, с которым она завершилась в
        // в переменную exitVal
        try {
            int exitVal = process.waitFor();
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    @Test
    void methodTest(){
        System.out.println();
        assertTrue(true);
    }

}
