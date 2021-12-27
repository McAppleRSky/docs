package ru.mrs.hamlscript;

import org.gradle.api.Project;
import org.json.JSONObject;
import ru.mrs.util.ToolFunc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class NodeHamlRunner{

    private String mainProjectPath;
    private Project mainProjectSources;

//    ProcessBuilder processBuilder;

//    @SuppressWarnings("removal")
    private static String hamlList = "list.json"
//        NodeHamlRunner.class.getClassLoader().getResource("haml_structure/list.json").getPath()
            ,hamlInputDir = "templates"
            ,HtmlOutputDir = "rebuild"
            ;

    public NodeHamlRunner(Project projectSources) {
        this.mainProjectSources = projectSources;
        this.mainProjectPath = mainProjectSources.getProject().getProjectDir().getPath();

        try {
            if(ToolFunc.notNull(new ProcessBuilder("bash", "-c", "").start())){
                hamlQueue(Paths.get(mainProjectPath, hamlInputDir).toString(),
                        Paths.get(mainProjectPath, HtmlOutputDir).toString(),
                        Paths.get(mainProjectPath, hamlList));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hamlQueue(String inputDir, String outputDir, Path filelist) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(new String(Files.readAllBytes(filelist)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator iterator = jsonObj.keys();
        while(iterator.hasNext()){
            String hamlFilenameKey = iterator.next().toString()
                    ,htmlFilenameValue
                    ;
            if(!hamlFilenameKey.trim().isEmpty()){
                htmlFilenameValue = jsonObj.get(hamlFilenameKey).toString();
                ExternalProgramLauncher("haml",
                        Paths.get(inputDir,hamlFilenameKey).toString(),
                        Paths.get(outputDir,htmlFilenameValue).toString());
            }
        }
    }

    void ExternalProgramLauncher(String program,String haml,String html){
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
