package com.xiaoniu.gradle.mylint

import com.android.builder.model.AndroidProject;
import org.gradle.api.Project
import org.gradle.tooling.GradleConnector;

/**
 * Created by wenzhonghu on 2017/12/28.
 */

class MyLintIncrement {
    def task(Project project) {
        project.task('MyLintIncrement') << {
            println "MyLintIncrement"
            def ss = getPostCommitChange(project)
            for (s in ss){
                println "$s"
            }
        }
    }

    private List<String> getPostCommitChange(Project project) {
        ArrayList<String> filterList = new ArrayList<String>()
        try {
            String projectDir = project.getProjectDir()
            String commond = "git diff --name-only --diff-filter=ACMRTUXB  HEAD~1 HEAD~0 $projectDir"
            String changeInfo = commond.execute(null, project.getRootDir()).text.trim()
            println(changeInfo)
            if (changeInfo == null || changeInfo.empty) {
                return filterList
            }
            String[] lines = changeInfo.split("\\n")
            return lines.toList()
        } catch (Exception e) {
            return filterList
        }
    }

    public AndroidProject getAndroidProject() {
        GradleConnector gradleConn = GradleConnector.newConnector()
        gradleConn.forProjectDirectory(getProject().getProjectDir())
        AndroidProject modelProject = gradleConn.connect().getModel(AndroidProject.class)
        return modelProject
    }
}
