package librarysystem;

public class Category{
    BookArchieve b;
   
    public void GetAllRelatedBooks(String category)
    {   b=new BookArchieve(); // communicates with the BookArchieve by that object which means getting the books
        boolean Isin=false;
        for(int i=0;i<BookArchieve.book.size();i++)
        {
          Book x= (Book) BookArchieve.book.get(i);// communicates the Book by that object to get the method getBookdetails
          if(x.getCategory().equals(category))
          {
              Isin=true;
              x.getBookDetails();// method inside the book
              System.out.println("===================");
          }
         
        }
        if(!Isin)
        System.out.println("There is no books in that category or wrong category name");
    }
    
}
