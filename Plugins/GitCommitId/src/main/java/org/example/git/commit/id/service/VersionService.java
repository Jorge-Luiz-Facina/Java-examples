package org.example.git.commit.id.service;

import org.example.git.commit.id.controller.dto.response.GitCommitResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VersionService {

    @Value("${git.branch}")
    private String branch;

    @Value("${git.commit.id}")
    private String commitId;

    @Value("${git.tags}")
    private String tags;

    public GitCommitResponse get() {
        GitCommitResponse gitCommitResponse = new GitCommitResponse();
        gitCommitResponse.setBranch(branch);
        gitCommitResponse.setCommitId(commitId);
        gitCommitResponse.setTags(tags);
        return gitCommitResponse;
    }
}
