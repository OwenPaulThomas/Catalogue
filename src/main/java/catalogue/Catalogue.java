package catalogue;

import catalogue.json.BookEntry;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author owen
 */
public class Catalogue{
    private final Map<BigDecimal,BookEntry>entries=new HashMap<>();
    private final Map<String,Set<BookEntry>>entriesByTitle=new HashMap<>();
    private final Map<String,Set<BookEntry>>entriesByAuthor=new HashMap<>();

    public synchronized final void add(BookEntry entry){
        BookEntry.validateFields(entry);

        if(this.entries.putIfAbsent(entry.getIsbn(),entry)!=null){
            throw new RuntimeException("Entry already exists.");
        }
        
        var title=entry.getTitle();
        
        var eTitleSet=this.entriesByTitle.get(title);
        if(eTitleSet==null){
            this.entriesByTitle.put(title,eTitleSet=new HashSet<>());
        }
        if(!eTitleSet.add(entry)){
            throw new RuntimeException("Integrity error: Entry already added to titles.");
        }
        
        for(var author:entry.getAuthors()){
            var eAuthorSet=this.entriesByAuthor.get(author);
            if(eAuthorSet==null){
                this.entriesByAuthor.put(author,eAuthorSet=new HashSet<>());
            }
            if(!eAuthorSet.add(entry)){
                throw new RuntimeException("Integrity error: Entry already added to authors.");
            }
        }
    }
    
    public synchronized final BookEntry find(BigDecimal isbn){
        if(isbn==null){
            throw new RuntimeException("ISBN not given.");
        }
        
        var e=this.entries.get(isbn);
        if(e==null){
            throw new RuntimeException("Book by ISBN does not exist.");
        }
        
        return e;
    }
    
    public synchronized final BookEntry[]findByTitle(String title){
        if(title==null){
            throw new RuntimeException("Title not given.");
        }
        
        var eSet=this.entriesByTitle.get(title);
        if(eSet==null){
            throw new RuntimeException("Book by Title does not exist.");
        }
        
        return eSet.toArray(new BookEntry[0]);
    }
    
    public synchronized final BookEntry[]findByAuthor(String author){
        if(author==null){
            throw new RuntimeException("Author not given.");
        }
        
        var eSet=this.entriesByAuthor.get(author);
        if(eSet==null){
            throw new RuntimeException("Book by Author does not exist.");
        }
        
        return eSet.toArray(new BookEntry[0]);
    }

    public synchronized void update(BookEntry entry){
        var e=this.entries.get(entry.getIsbn());
        if(e==null){
            throw new RuntimeException("Book does not exist.");
        }
        
        if(entry.getTitle()!=null){
            e.setTitle(entry.getTitle());
        }
        
        if(entry.getAuthors()!=null){
            e.setAuthors(entry.getAuthors());
        }
        
        int year=entry.getYear();
        int month=entry.getMonth();
        int day=entry.getDay();
        
        if(year!=0&&month!=0&&day!=0){
            LocalDate.of(year,month,day);
            
            e.setYear(year);
            e.setMonth(month);
            e.setDay(day);
        }else if(year!=0||month!=0||day!=0){
            throw new RuntimeException("Invalid date.");
        }
    }
    
    public synchronized final void delete(BigDecimal isbn){
        if(isbn==null){
            throw new RuntimeException("ISBN not given.");
        }
        
        var e=this.entries.remove(isbn);
        if(e==null){
            throw new RuntimeException("Entry does not exist.");
        }
        
        var eTitleSet=this.entriesByTitle.get(e.getTitle());
        if(eTitleSet==null){
            throw new RuntimeException("Integrity error: Title set does not exist.");
        }
        if(!eTitleSet.remove(e)){
            throw new RuntimeException("Integrity error: Title entry does not exist.");
        }
        if(eTitleSet.isEmpty()){
            this.entriesByTitle.remove(e.getTitle());
        }
        
        for(var author:e.getAuthors()){
            var eAuthorSet=this.entriesByAuthor.get(author);
            if(eAuthorSet==null){
                throw new RuntimeException("Integrity error: Author set does not exist.");
            }
            if(!eAuthorSet.remove(e)){
                throw new RuntimeException("Integrity error: Author entry does not exist.");
            }
            if(eAuthorSet.isEmpty()){
                this.entriesByTitle.remove(author);
            }
        }
    }
}

