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
public final class TitleJson{
    private String title;
    
    public final String getTitle(){
        return this.title;
    }
    
    @Override
    public final String toString(){
        return "{\"title\":\""+this.title+"\"}";
    }
}
