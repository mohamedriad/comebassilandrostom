
package librarysystem;
class RemoveBookController {
 BookArchieve b;
    void removeBook(String BookName, int BookID, int NoOfPages, String category)
    {
        Book e=new Book(BookName,BookID,NoOfPages,category);
        b=new BookArchieve();
        b.removeBook(e);
    }
    
}
