package librarysystem;
public class Book
{   
      private String name;
      private int ID;
      private int NoOfPages;
      private String category;
      private int NoOfCopies;
   Book(String BookName, int BookID, int NoOfPages, String category)
    {
        name=BookName;
        ID=BookID;
        this.NoOfPages=NoOfPages;
        this.category=category;
      }
    public void getBookDetails() // when we press search button and want to print the books details
    {
        System.out.println("The Book Name:"+this.name);
        System.out.println("The Book ID:"+this.ID);
        System.out.println("The Book NoOfPages:"+this.NoOfPages);
        System.out.println("The Book category:"+this.category);
        System.out.println("The Book NoOfCopies:"+this.NoOfCopies);
    }
   /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the NoOfPages
     */
    public int getNoOfPages() {
        return NoOfPages;
    }

    /**
     * @param NoOfPages the NoOfPages to set
     */
    public void setNoOfPages(int NoOfPages) {
        this.NoOfPages = NoOfPages;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the NoOfCopies
     */
    public int getNoOfCopies() {
        return NoOfCopies;
    }

    /**
     * @param NoOfCopies the NoOfCopies to set
     */
    public void setNoOfCopies(int NoOfCopies) {
        this.NoOfCopies = NoOfCopies;
    }
}
