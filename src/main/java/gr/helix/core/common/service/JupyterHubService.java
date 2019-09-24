package gr.helix.core.common.service;

import gr.helix.core.common.model.NotebookServerRequest;

public interface JupyterHubService {

    /**
     * Initializes a new notebook server
     *
     * @param request Server initialization parameters
     *
     * @return True if the operation is successful; Otherwise false is returned
     */
    boolean initializeNotebookServer(NotebookServerRequest request);

}
