package gr.helix.core.common.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A generic {@link ErrorCode} that simply holds a key. 
 * <p>
 * This is basically useful as a deserializer of an {@link ErroCode} of an 
 * unknown concrete class.
 */
public class GenericErrorCode implements ErrorCode
{
    private final String key;
    
    @JsonCreator
    public GenericErrorCode(String key)
    {
        this.key = key;
    }
    
    @Override
    public String key()
    {
        return key;
    }
}