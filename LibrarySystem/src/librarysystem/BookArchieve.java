
package librarysystem;
import java.util.ArrayList;

public class BookArchieve {
    static ArrayList book=new ArrayList<Book>();

    void addBook(Book e)
    {
        for(int i=0;i<book.size();i++)
        { 
            Book b=(Book)book.get(i);
            if(b.getID()==e.getID()&&b.getCategory().equals(e.getCategory())&&b.getName().equals(e.getName())&&b.getNoOfPages()==e.getNoOfPages())
            {
              e.getBookDetails();
              System.out.println("book already exists,try again");
              System.out.println("==========================");
              return;
            }
        
        }
        book.add(e);// method that goes for the archieve (DataBase)
    }

    void removeBook(Book e) {
        for(int i=0;i<book.size();i++)
        { 
            Book b=(Book)book.get(i);
            if(b.getID()==e.getID()&&b.getCategory().equals(e.getCategory())&&b.getName().equals(e.getName())&&b.getNoOfPages()==e.getNoOfPages())
            {
              book.remove(b);
              System.out.println("Book Removed Correctly");
                 System.out.println("========================");
              return;
            }
        
        }
        System.out.println("Book didn't exist");
           System.out.println("==========================");
     }

    void addBookCopy(Book e,int NoOfCopies)
    {
        for(int i=0;i<book.size();i++)
        { 
            Book b=(Book)book.get(i);
            if(b.getID()==e.getID()&&b.getCategory().equals(e.getCategory())&&b.getName().equals(e.getName())&&b.getNoOfPages()==e.getNoOfPages())
            {
              b.setNoOfCopies(b.getNoOfCopies()+NoOfCopies); // updateNoOfcopies in the book entity 
              System.out.println("book exist and the copies are updated");
              return;
            }
        
        }
        System.out.println("book doesn't exist");
        
    }

    void removeBookCopy(Book e, int NoOfCopies)
    {
          for(int i=0;i<book.size();i++)
        { 
            Book b=(Book)book.get(i);
            if(b.getID()==e.getID()&&b.getCategory().equals(e.getCategory())&&b.getName().equals(e.getName())&&b.getNoOfPages()==e.getNoOfPages())
            {
              b.setNoOfCopies(b.getNoOfCopies()-NoOfCopies);
              System.out.println("book exist and the copies are updated");
              return;
            }
        
        }
        System.out.println("book doesn't exist");
    }
    
}
