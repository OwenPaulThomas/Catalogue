package catalogue;

import catalogue.json.AuthorJson;
import catalogue.json.BookEntries;
import catalogue.json.BookEntry;
import catalogue.json.IsbnJson;
import catalogue.json.StringJson;
import catalogue.json.TitleJson;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogueController{
    Catalogue catalogue=new Catalogue();
    
    @RequestMapping(value="add",method=RequestMethod.POST,consumes="application/json",produces="application/json")
    public String add(@RequestBody BookEntry entry){
        String s=null;
        try{
            this.catalogue.add(entry);
        }catch(Exception x){
            s=x.getMessage();
        }
        if(s==null){
            s="Book entry added.";
        }
        
        return new StringJson(s).toString();
    }
    
    @RequestMapping(value="find",method=RequestMethod.GET,consumes="application/json",produces="application/json")
    public String find(@RequestParam BigDecimal isbn){
        String s;
        try{
            s=this.catalogue.find(isbn).toString();
        }catch(Exception x){
            s=new StringJson(x.getMessage()).toString();
        }
        
        return s;
    }
    
    @RequestMapping(value="findByTitle",method=RequestMethod.GET,consumes="application/json",produces="application/json")
    public String findByTitle(@RequestParam String title){
        String s;
        try{
            s=new BookEntries(this.catalogue.findByTitle(title)).toString();
        }catch(Exception x){
            s=new StringJson(x.getMessage()).toString();
        }
        
        return s;
    }
    
    @RequestMapping(value="findByAuthor",method=RequestMethod.GET,consumes="application/json",produces="application/json")
    public String findByAuthor(@RequestParam String author){
        String s;
        try{
            s=new BookEntries(this.catalogue.findByAuthor(author)).toString();
        }catch(Exception x){
            s=new StringJson(x.getMessage()).toString();
        }
        
        return s;
    }
    
    @RequestMapping(value="update",method=RequestMethod.POST,consumes="application/json",produces="application/json")
    public String update(@RequestBody BookEntry entry){
        String s=null;
        try{
            this.catalogue.update(entry);
        }catch(Exception x){
            s=x.getMessage();
        }
        if(s==null){
            s="Book entry updated.";
        }
        
        return new StringJson(s).toString();
    }

    @RequestMapping(value="delete",method=RequestMethod.GET,consumes="application/json",produces="application/json")
    public String delete(@RequestParam BigDecimal isbn){
        String s=null;
        try{
            this.catalogue.delete(isbn);
        }catch(Exception x){
            s=x.getMessage();
        }
        if(s==null){
            s="Book entry deleted.";
        }
        
        return new StringJson(s).toString();
    }
}
