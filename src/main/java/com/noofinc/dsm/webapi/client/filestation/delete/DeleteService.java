package com.noofinc.dsm.webapi.client.filestation.delete;

import java.util.Optional;

public interface DeleteService {

    String start(String path, boolean recursive, boolean accurateProgress, Optional<String> searchTaskId);

    DeleteStatus status(String taskId);

    void stop(String taskId);

    void synchronousDelete(String path, boolean recursive, Optional<String> searchTaskId);
}
