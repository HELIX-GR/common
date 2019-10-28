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
    private final Long hardLimitForSpace;
    
    /**
     * The soft-limit for space (in bytes).
     * <p>See note on zeros at {@link #hardLimitForSpace}
     */
    private final Long softLimitForSpace;
    
    /**
     * The number of inodes used (i.e the number of files).
     * <p>A zero value indicates either that no accounting is enabled.
     */
    private final Integer usedInodes;
    
    /**
     * The hard-limit for the number of inodes (i.e the number of files).
     * <p>See note on zeros at {@link #hardLimitForSpace}
     */
    private final Integer hardLimitForInodes;
    
    /**
     * The soft-limit for the number of inodes (i.e the number of files).
     * <p>See note on zeros at {@link #hardLimitForSpace}
     */
    private final Integer softLimitForInodes;

    public UserDataReport(
        Long usedBytes, Long hardLimitForSpace, Long softLimitForSpace, 
        Integer usedInodes, Integer hardLimitForInodes, Integer softLimitForInodes)
    {
        this.usedBytes = usedBytes;
        this.hardLimitForSpace = hardLimitForSpace;
        this.softLimitForSpace = softLimitForSpace;
        this.usedInodes = usedInodes;
        this.hardLimitForInodes = hardLimitForInodes;
        this.softLimitForInodes = softLimitForInodes;
    }

    public Long getUsedBytes()
    {
        return usedBytes;
    }

    public Long getHardLimitForSpace()
    {
        return hardLimitForSpace;
    }

    public Long getSoftLimitForSpace()
    {
        return softLimitForSpace;
    }

    public Integer getUsedNumberOfFiles()
    {
        return usedInodes;
    }

    public Integer getHardLimitForNumberOfFiles()
    {
        return hardLimitForInodes;
    }

    public Integer getSoftLimitForNumberOfFiles()
    {
        return softLimitForInodes;
    }
}
