package com.xiaoniu.gradle.mylint

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyLintPlugin implements Plugin<Project> {
    LintIncrementExtension lintIncrementExtension
    void apply(Project project) {
        lintIncrementExtension = project.extensions.create('mylint', LintIncrementExtension)

        new MyLintIncrement().task(project)
        createGitHooksTask(project)

    }

    private void createGitHooksTask(Project project) {
        def preBuild = project.tasks.findByName("preBuild")

        if (preBuild == null) {
            throw new GradleException("lint  need depend on preBuild and clean task")
            return
        }

        def installGitHooks = project.getTasks().create("installGitHooks") .doLast {
            def PATH_POST_COMMIT = ".git/hooks/post-commit"
            if (FileUtils.win) {
                File postCommitFile = new File(project.rootProject.rootDir, PATH_POST_COMMIT)
                if (lintIncrementExtension.isCheckPostCommit()) {
                    FileUtils.copyResourceFile("post-commit", postCommitFile)
                } else {
                    if (preCommitDestFile.exists()) {
                        preCommitDestFile.delete()
                    }
                }
            } else {
                File postCommitFile = new File(project.rootProject.rootDir, PATH_POST_COMMIT)
                if (lintIncrementExtension.isCheckPostCommit()) {
                    FileUtils.copyResourceFile("post-commit-linux", postCommitFile)
                } else {
                    if (preCommitDestFile.exists()) {
                        preCommitDestFile.delete()
                    }
                }
                Runtime.getRuntime().exec("chmod -R +x .git/hooks/")
            }
        }

        preBuild.finalizedBy installGitHooks
    }



}
