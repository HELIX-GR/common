package gr.helix.core.common.service;

import java.util.List;
import java.util.Optional;

import gr.helix.core.common.model.user.AccountInfo;
import gr.helix.core.common.model.user.UserDataReport;

public interface UserDataManagementService {

    /**
     * Setup a user's data directory for the JupyterHub cluster.
     * The common things that have to be set-up is to prepare the directory hierarchy, 
     * to enable accounting and to apply space quotas. 
     *
     * @param userAccount The target user 
     * @param serverHost The server host for the JupyterHub application 
     * @param quotaForSpace A hard limit (bytes) for the space that can be used. 
     *    A <tt>null</tt> value corresponds to the defaults.
     * @param quotaForNumberOfFiles A hard limit for the number of files (i.e the number of inodes) 
     *    that can be created. A <tt>null</tt> value corresponds to the defaults    
     * 
     * @return True if the operation is successful; Otherwise false is returned
     */
    boolean setupDirectory(AccountInfo userAccount, String serverHost,
        Long quotaForSpace, Integer quotaForNumberOfFiles);
    
    /**
     * Cleanup a user's data directory for the JupyterHub cluster.
     * This is basically to disable accounting and quota enforcement.
     * 
     * @param userAccount The target user 
     * @param serverHost The server host for the JupyterHub application 
     * @param deleteDirs A flag indicating if user directory should also be (recursively) deleted
     * @return
     */
    boolean cleanupDirectory(AccountInfo userAccount, String serverHost, boolean deleteDirs);
    
    /**
     * Get a usage report for user's data directory. 
     * 
     * @param accountId The numeric ID of the account
     * @param serverHost The server host for the JupyterHub application 
     */
    Optional<UserDataReport> getReport(int accountId, String serverHost);
    
    /**
     * List known accounts (as IDs)
     * 
     * @param serverHost The server host for the JupyterHub application 
     */
    List<Integer> listAccounts(String serverHost);
}
