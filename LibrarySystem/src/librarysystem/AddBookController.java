package librarysystem;

import java.util.ArrayList;

public class AddBookController{
  
  BookArchieve b;

   

    void addBook(String BookName, int BookID, int NoOfPages, String category)
    {
     Book e=new Book(BookName,BookID,NoOfPages,category); // constructor is considered to be create book
      b = new BookArchieve();  
      b.addBook(e); // communicates with bookarchieve through this method
    }
}
