package os;
import java.util.Random;
import java.util.Scanner;
public class Banker
{ int[][] max;
  int[][] need;
  int[][] allocated;
  int[] available;
  int[] NoOfInstances;
  int[] SumOfColoumns; // because in order to put available values it will be  NoOfinstances-sumofcoloumn
  int NoOfProcesses,DataTypes;
  
  // functions bta3t el random
  public void fillMax()
  {
      Random rand=new Random();
     
    for(int i=0;i<NoOfProcesses;i++)
    {
        for(int j=0;j<DataTypes;j++)
        {
            int maximum=NoOfInstances[j];
            int minimum=(NoOfInstances[j])/2;
            this.max[i][j]=(int) ((Math.random() * ((maximum - minimum) + 1)) + minimum);
           // this.max[i][j]=rand.nextInt(available[j])+1; // bec 0 is inclusive and max value is exclusive so we add 1
        }
    }
  }
  public void fillAllocated()
  { 
      
      for(int i=0;i<NoOfProcesses;i++)
          for(int j=0;j<DataTypes;j++)
              allocated[i][j]=0;
      
      
      
      
      
      
      
      
      
      
      /* Random rand=new Random();
   int[] temp=new int[DataTypes]; //store the values of the available array inside another array for filling the allocated array so we don't change the actual value of the available array
    //=============== copy array inside the temp
     for(int i=0;i<DataTypes;i++)
         temp[i]=this.available[i];
     //===============  
      for(int coloumn=0;coloumn<DataTypes;coloumn++)
    {    //int sum=0;
        for(int row=0;row<NoOfProcesses;row++)
        {
             
            this.allocated[row][coloumn]=rand.nextInt(NoOfInstances[coloumn]-temp[coloumn]);
            temp[coloumn]-=allocated[row][coloumn];
         //   sum+=allocated[row][coloumn];
            
        }
      // SumOfColoumns[coloumn]=sum;
    }*/
      
      
  }
  public void fillAvailable()
  {
    //  Random rand= new Random();
     for(int i=0;i<DataTypes;i++)
     {   
         this.available[i]=this.NoOfInstances[i];
     }  
  }
  public void input()
  {  Scanner sc= new Scanner(System.in);
     System.out.print("Please enter the No of processes and No of resources: ");
     NoOfProcesses=sc.nextInt();
     DataTypes=sc.nextInt();   // resource types
     NoOfInstances=new int[DataTypes];
     SumOfColoumns=new int[DataTypes];
     max=new int[NoOfProcesses][DataTypes];
     need=new int[NoOfProcesses][DataTypes];
     allocated=new int[NoOfProcesses][DataTypes];
     available=new int[DataTypes];
     System.out.print("Please enter the No of instances of each resource type: ");
     for(int z=0;z<DataTypes;z++)
         this.NoOfInstances[z]=sc.nextInt();
    fillMax();
    fillAvailable();
    fillAllocated();
  }
  //==============================
 public void banker(int ProcessWithRequest , int[] req)
 {   //input();
    int order[]=new int[NoOfProcesses]; // to know to order the processes finished with
    boolean isDone[]=new boolean[NoOfProcesses]; // so we don't enter to process twice intially the values are false!
    getNeed();// calculate the need... Need=max-allocated
    int i=0; 
    if(RequestLessThanAvailable(req) && RequestLessThanNeed(ProcessWithRequest,req)){
        NeedandRequest(ProcessWithRequest,req,'-');
        availableandRequest(req,'-');
        AllocatedandRequest(ProcessWithRequest,req,'+');
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
         
    } }
    else
    {  
           System.out.println("request wasn't granted Bec it didn't pass the checking whether it's less than available and need or not");
    }
    
       if(i==NoOfProcesses)
         {
             // print max array
      System.out.println("Max Array ");
       for(int x=0;x<NoOfProcesses;x++)
    {
        for(int j=0;j<DataTypes;j++)
        {
            System.out.print(this.max[x][j]+" ");
        }
        System.out.println();
    }
     //==========================
    System.out.println("Allocated Array");
    // print allocated array
      for(int x=0;x<NoOfProcesses;x++)
    {
        for(int j=0;j<DataTypes;j++)
        {
            System.out.print(this.allocated[x][j]+" ");
        }
        System.out.println();
    }
      //=========================
      System.out.println("Need Array");
    // print allocated array
      for(int x=0;x<NoOfProcesses;x++)
    {
        for(int j=0;j<DataTypes;j++)
        {
            System.out.print(this.need[x][j]+" ");
        }
        System.out.println();
    }
   //==============================
      System.out.println("The Order of Processes");
      for(int h=0;h<NoOfProcesses;h++)
          System.out.println("P"+order[h]);
      /*
      //print available 
      System.out.print("The Available Array: ");
      for(int w=0;w<DataTypes;w++)
          System.out.print(available[w]+" ");
      System.out.println(""); */
      //==================
      //print request and Process with request
      System.out.println("The Process with Request: P"+ProcessWithRequest);
      System.out.print("The Request Array: ");
      for(int w=0;w<DataTypes;w++)
          System.out.print(req[w]+" ");
      System.out.println("");
      //==================
             System.out.println("System is Safe");}
       else if(i!=0 && RequestLessThanAvailable(req) && RequestLessThanNeed(ProcessWithRequest,req) ) 
         { 
             NeedandRequest(ProcessWithRequest,req,'+');
             availableandRequest(req,'+');
             AllocatedandRequest(ProcessWithRequest,req,'-');
             System.out.println("Request wasn't granted because it made the System unsafe");
             // print max array
      System.out.println("Max Array ");
       for(int x=0;x<NoOfProcesses;x++)
    {
        for(int j=0;j<DataTypes;j++)
        {
            System.out.print(this.max[x][j]+" ");
        }
        System.out.println();
    }
     //==========================
    System.out.println("Allocated Array");
    // print allocated array
      for(int x=0;x<NoOfProcesses;x++)
    {
        for(int j=0;j<DataTypes;j++)
        {
            System.out.print(this.allocated[x][j]+" ");
        }
        System.out.println();
    }
      //=========================
      System.out.println("Need Array");
    // print allocated array
      for(int x=0;x<NoOfProcesses;x++)
    {
        for(int j=0;j<DataTypes;j++)
        {
            System.out.print(this.need[x][j]+" ");
        }
        System.out.println();
    }
   //==============================
      System.out.println("The Order of Processes");
      for(int h=0;h<NoOfProcesses;h++)
          System.out.println("P"+order[h]);
      /*
      //print available 
      System.out.print("The Available Array: ");
      for(int w=0;w<DataTypes;w++)
          System.out.print(available[w]+" ");
      System.out.println(""); */
      //==================
      //print request and Process with request
      System.out.println("The Process with Request: P"+ProcessWithRequest);
      System.out.print("The Request Array: ");
      for(int w=0;w<DataTypes;w++)
          System.out.print(req[w]+" ");
      System.out.println("");
      //================== 
      }
      
 
 
 
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

    private boolean RequestLessThanAvailable(int[] req) {
        for(int i=0;i<DataTypes;i++)
        {
          if(this.available[i]<req[i])
              return false;
        }
        return true;
    }

    private void NeedandRequest(int ProcessWithRequest,int[] req,char op) {
        if(op == '+')
        {
           for(int coloumns=0;coloumns<DataTypes;coloumns++)
           {
               this.need[ProcessWithRequest][coloumns]+=req[coloumns];
           }
        }
        else {
            for(int coloumns=0;coloumns<DataTypes;coloumns++)
           {
               this.need[ProcessWithRequest][coloumns]-=req[coloumns];
           }
        }
     }

    private void availableandRequest(int[] req,char op) {
        if(op=='+')
        {
           for(int coloumns=0;coloumns<DataTypes;coloumns++)
           {
               this.available[coloumns]+=req[coloumns];
           }
        }
        else {
            for(int coloumns=0;coloumns<DataTypes;coloumns++)
           {
               this.available[coloumns]-=req[coloumns];
           }
        }
     }

    private void AllocatedandRequest(int ProcessWithRequest, int[] req,char op) {
        if(op=='+')
        {
           for(int coloumns=0;coloumns<DataTypes;coloumns++)
           {
               this.allocated[ProcessWithRequest][coloumns]+=req[coloumns];
           }
        }
        else {
            for(int coloumns=0;coloumns<DataTypes;coloumns++)
           {
               this.allocated[ProcessWithRequest][coloumns]-=req[coloumns];
           }
        }
    }

    private boolean RequestLessThanNeed(int ProcessWithRequest, int[] req)
    {
        for(int i=0;i<DataTypes;i++)
        {
            if(this.need[ProcessWithRequest][i]<req[i])
                return false;
        }
        return true;
    }
   
}
  
   
    

