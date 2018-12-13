package librarysystem;
public class SearchBookController {
    Category c; 
    public void SearchCategory(String Category)
    {  c=new Category(); // has object of the entity controller
       c.GetAllRelatedBooks(Category); // communicates with the entity with that method
    }
}
