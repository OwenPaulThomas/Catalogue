/*
 * (C) Copyright Owen Thomas. All rights reserved.
 *  Express written permission must be obtained from Owen Thomas in order to
 *  reproduce all or part of this source or compiled code in a manner
 *  specified in said permission.
 */
package catalogue.json;

import java.math.BigDecimal;

/**
 *
 * @author owen
 */
public final class IsbnJson{
    private BigDecimal isbn;
    
    public final BigDecimal getIsbn(){
        return this.isbn;
    }
    
    @Override
    public final String toString(){
        return "{\"isbn\":\""+this.isbn+"\"}";
    }
}
