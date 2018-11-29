package os;
public class Banker
{ int[][] max;
  int[][] need;
  int[][] allocated;
  int[] available;
  int NoOfProcesses,DataTypes;
  
  public Banker()
  {  
      NoOfProcesses=5;
      DataTypes=3;
      max=new int[NoOfProcesses][DataTypes];
      need=new int[NoOfProcesses][DataTypes];
      allocated=new int[NoOfProcesses][DataTypes];
      available=new int[DataTypes];
 }
  // functions bta3t el random
  public void fillMax()
  {
          
  }
  public void fillAllocated()
  {
  }
  public void fillAvailable()
  {
  
  
  }
  //==============================
 public void banker()
 {  int order[]=new int[NoOfProcesses]; // to know to order the processes finished with
    boolean isDone[]=new boolean[NoOfProcesses]; // so we don't enter to process twice intially the values are false!
    getNeed();// calculate the need... Need=max-allocated
    int i=0; 
    while(i<NoOfProcesses)
    {  
        boolean getout=false; //safety variable....3shan lw hasal f mra w 3mlt loop kmla mn l inner loop w mdkhltsh wla mra el if,a3rf atla3 mnha
        for(int r=0;r<NoOfProcesses;r++)
        {
            if(NeedLessThanAvailable(r) && !isDone[r]) 
            {
                addAllocatedtoAvailable(r); // available+=allocated
                clearallocated(r);    // allocated =0 
                order[i] =r; // arranging the processes inside this array el i na damen enha ht3ed 0 1 2 3 4 f 3shan kda 3mltha el index enma l r hya lassigned 3shan bet-represent el current process ly 5lst 
                isDone[r]=true; // hena b2a l r b2t l index 3shan zy ma olna el r bet-represent el current process
                getout=true;
                i++;
            } 
        }
        if(!getout)  // 3shan lw wahda fdlt sh8ala mnfdlsh nlef 3alfady gowa
            break;
        /* if(AllisDone(isDone)) // kda yb2a kol processes khalaset w akhrog
         {  break;} */
         
    }
      if(i==NoOfProcesses)
      System.out.println("System is Safe");
      else 
          System.out.println("System is unsafe");
      for(int h=0;h<NoOfProcesses;h++)
          System.out.println("P"+order[h]);
 
 
 
 }         

    public boolean NeedLessThanAvailable(int k) // bb3tlha l noOfRow aknne ba3at satr wahd 
    {
        for(int i=0;i<DataTypes;i++)
        {
            if(this.need[k][i]>this.available[i])
                return false;
        }
        return true;
        }

    public void getNeed()
    {
       for(int rows=0;rows<NoOfProcesses;rows++)
       {
           for(int coloumns=0;coloumns<DataTypes;coloumns++)
           {
             this.need[rows][coloumns]=this.max[rows][coloumns]-this.allocated[rows][coloumns];
           }
       }
    }

    public void addAllocatedtoAvailable(int k) // nfs fkrt el NeedLessThanAvailable 
    {
      for(int i=0;i<DataTypes;i++)
      {
       this.available[i]+=allocated[k][i];
      }
    }

   
    public void clearallocated(int k) 
    {
        for(int i=0;i<DataTypes;i++)
        { 
            this.allocated[k][i]=0;
        }
        }

    public boolean AllisDone(boolean ar[]) 
    {
        for(int i=0;i<NoOfProcesses;i++)
        {
           if(ar[i]==false)
               return false;
        }
        return true;
    }
    }
  
   
    

