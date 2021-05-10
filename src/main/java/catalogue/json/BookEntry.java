package catalogue.json;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author owen
 */
public class BookEntry{
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void validateFields(BookEntry entry){
        if(entry==null){
            throw new RuntimeException("Date not specified");
        }

        BigDecimal isbn=entry.getIsbn();
        if(isbn==null){
            throw new RuntimeException("ISBN not specified.");
        }
        if(isbn.scale()!=0||isbn.precision()>13){
            throw new RuntimeException("ISBN format error.");
        }

        String title=entry.getTitle();
        if(title==null){
            throw new RuntimeException("Title not specified.");
        }

        String[]authors=entry.getAuthors();
        if(authors==null){
            throw new RuntimeException("Authors not specified.");
        }
        
        LocalDate.of(entry.getYear(),entry.getMonth(),entry.getDay());
    }

    private BigDecimal isbn;
    private String title;
    private String[]authors;
    
    private int year;
    private int month;
    private int day;
    
    public final BigDecimal getIsbn(){
        return this.isbn;
    }
    
    public final void setIsbn(BigDecimal isbn){
        this.isbn=isbn;
    }
    
    public final String getTitle(){
        return this.title;
    }
    
    public final void setTitle(String title){
        this.title=title;
    }
    
    public final String[]getAuthors(){
        return this.authors;    
    }
    
    public final void setAuthors(String[]authors){
        this.authors=authors;
    }
    
    public final int getYear(){
        return this.year;
    }
    
    public final void setYear(int year){
        this.year=year;
    }
    
    public final int getMonth(){
        return this.month;
    }
    
    public final void setMonth(int month){
        this.month=month;
    }
    
    public final int getDay(){
        return this.day;
    }
    
    public final void setDay(int day){
        this.day=day;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        BookEntry other = (BookEntry) obj;
        return Objects.equals(this.isbn, other.isbn);
    }
    @Override
    public final String toString(){
        String s="{\"isbn\":"+this.isbn+",\"title\":\""+this.title+"\",\"authors\":[";
        
        boolean comma=false;
        for(String author:this.authors){
            s+=(comma?",":"")+"\""+author+"\"";
            comma=true;
        }
        s+="],\"year\":"+this.year+",\"month\":"+this.month+",\"day\":"+this.day+"}";

        return s;
    }
}
