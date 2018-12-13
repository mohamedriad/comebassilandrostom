
package librarysystem;
class AddBookCopyController {
BookArchieve b;

void AddBookCopy(String BookName, int BookID, int NoOfPages, String category, int NoOfCopies)
{  Book e=new Book(BookName,BookID,NoOfPages,category);
   b=new BookArchieve();
   b.addBookCopy(e,NoOfCopies);// controller talks to archieve through that method
    
}
    
}
