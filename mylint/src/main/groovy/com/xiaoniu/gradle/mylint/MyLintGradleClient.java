package com.xiaoniu.gradle.mylint;


import com.android.annotations.NonNull;
import com.android.build.gradle.internal.LintGradleClient;
import com.android.builder.model.AndroidProject;
import com.android.builder.model.Variant;
import com.android.sdklib.BuildToolInfo;
import com.android.tools.lint.LintCliFlags;
import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.client.api.LintRequest;

import org.gradle.api.Project;

import java.io.File;
import java.util.List;

/**
 * Created by wenzhonghu on 2017/12/28.
 */
public class MyLintGradleClient extends LintGradleClient {

    public MyLintGradleClient(IssueRegistry registry, LintCliFlags flags,
                              Project gradleProject, AndroidProject modelProject,
                              File sdkHome, Variant variant, BuildToolInfo buildToolInfo) {
        super(registry, flags, gradleProject, modelProject, sdkHome, variant, buildToolInfo);
    }

    @Override
    protected LintRequest createLintRequest(@NonNull List<File> files) {
        //注意这个project是com.android.tools.lint.detector.api.project
        LintRequest lintRequest = super.createLintRequest(files);
        for (com.android.tools.lint.detector.api.Project project : lintRequest.getProjects()) {
            //project.addFile(changefile);//加入要扫描的文件
            addChangeFiles(project);
        }
        return lintRequest;
    }

    private void addChangeFiles(com.android.tools.lint.detector.api.Project project) {
        //project.
    }


}
