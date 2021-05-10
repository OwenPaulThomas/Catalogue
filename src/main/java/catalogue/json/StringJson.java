/*
 * (C) Copyright Owen Thomas. All rights reserved.
 *  Express written permission must be obtained from Owen Thomas in order to
 *  reproduce all or part of this source or compiled code in a manner
 *  specified in said permission.
 */
package catalogue.json;

/**
 *
 * @author owen
 */
public final class StringJson{
    private final String string;
    
    public StringJson(String string){
        if(string==null){
            throw new RuntimeException("String not given.");
        }
        this.string=string;
    }
    
    public final String getString(){
        return this.string;
    }
    
    @Override
    public final String toString(){
        return "{\"string\":\""+this.string+"\"}";
    }
}
