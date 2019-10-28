package gr.helix.core.common.model.user;

/**
 * A report on usage of a user's data directory
 */
public class UserDataReport
{
    /**
     * The space used (in bytes).
     * <p>A zero value may indicate either that no accounting is enabled, or that no blocks
     * are allocated. 
     */
    private final Long usedBytes;
    
    /**
     * The hard-limit for space (in bytes).
     * <p>A zero value may indicate either that no accounting is enabled or that a limit
     * is not defined (yet).
     */
    private final Long hardLimitForBytes;
    
    /**
     * The soft-limit for space (in bytes).
     * <p>See note on zeros at {@link #hardLimitForBytes}
     */
    private final Long softLimitForBytes;
    
    /**
     * The number of files used.
     * <p>A zero value indicates either that no accounting is enabled.
     */
    private final Integer usedNumberOfFiles;
    
    /**
     * The hard-limit for the number of files.
     * <p>See note on zeros at {@link #hardLimitForBytes}
     */
    private final Integer hardLimitForNumberOfFiles;
    
    /**
     * The soft-limit for the number of files.
     * <p>See note on zeros at {@link #hardLimitForBytes}
     */
    private final Integer softLimitForNumberOfFiles;

    public UserDataReport(
        Long usedBytes, Long hardLimitForBytes, Long softLimitForBytes, 
        Integer usedNumberOfFiles, Integer hardLimitForNumberOfFiles, Integer softLimitForNumberOfFiles)
    {
        this.usedBytes = usedBytes;
        this.hardLimitForBytes = hardLimitForBytes;
        this.softLimitForBytes = softLimitForBytes;
        this.usedNumberOfFiles = usedNumberOfFiles;
        this.hardLimitForNumberOfFiles = hardLimitForNumberOfFiles;
        this.softLimitForNumberOfFiles = softLimitForNumberOfFiles;
    }

    public Long getUsedBytes()
    {
        return usedBytes;
    }

    public Long getHardLimitForBytes()
    {
        return hardLimitForBytes;
    }

    public Long getSoftLimitForBytes()
    {
        return softLimitForBytes;
    }

    public Integer getUsedNumberOfFiles()
    {
        return usedNumberOfFiles;
    }

    public Integer getHardLimitForNumberOfFiles()
    {
        return hardLimitForNumberOfFiles;
    }

    public Integer getSoftLimitForNumberOfFiles()
    {
        return softLimitForNumberOfFiles;
    }

    @Override
    public String toString()
    {
        return String.format(
            "UserDataReport [" +
            "usedBytes=%s, hardLimitForBytes=%s, softLimitForBytes=%s, " +
            "usedNumberOfFiles=%s, hardLimitForNumberOfFiles=%s, softLimitForNumberOfFiles=%s]",
            usedBytes, hardLimitForBytes, softLimitForBytes, usedNumberOfFiles,
            hardLimitForNumberOfFiles, softLimitForNumberOfFiles);
    }
}
