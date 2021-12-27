package ru.mrs.hamlscript;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class JavaMainTaskRunner extends DefaultTask {

    private NodeHamlRunner nashornRunner;

    @TaskAction
    private void mainTask() {
        nashornRunner = new NodeHamlRunner(getProject());
    }

}
