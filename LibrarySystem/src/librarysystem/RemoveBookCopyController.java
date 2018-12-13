
package librarysystem;
public class RemoveBookCopyController{
    BookArchieve b;

    void RemoveBookCopy(String BookName, int BookID, int NoOfPages, String category, int NoOfCopies)
    {
        Book e=new Book(BookName,BookID,NoOfPages,category);
        b=new BookArchieve();
        b.removeBookCopy(e,NoOfCopies);
    }
    
}
