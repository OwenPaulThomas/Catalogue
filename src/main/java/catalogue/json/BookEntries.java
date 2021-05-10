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
public class BookEntries{
    private final BookEntry[]entries;
    
    public BookEntries(){
        this.entries=null;
    }
    
    public BookEntries(BookEntry[]entries){
        if(entries==null||entries.length==0){
            throw new RuntimeException("Entries not given.");
        }
        this.entries=entries;
    }
    
    public final BookEntry[]getEntries(){
        return this.entries;
    }
    
    @Override
    public final String toString(){
        String s="{\"entries\":[";
        
        boolean comma=false;
        for(BookEntry entry:this.entries){
            s+=(comma?",":"")+entry;
            comma=true;
        }
        
        return s+"]}";
    }
}
