package os;
import java.util.Random;
import java.util.Scanner;
public class Banker
{  int[][] max;
   int[][] need;
   int[][] allocated;
   int[][] tempallocated;
   int[] available;
   int[] tempavailable;
   int[] NoOfInstances;
   int[] SumOfColoumns; // because in order to put available values it will be  NoOfinstances-sumofcoloumn
   int NoOfProcesses,DataTypes;
   int[] ReleaseArray;
  // functions filling the Matrices
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
         }
    }
  }
  public void fillAllocated()
  { 
      
      for(int i=0;i<NoOfProcesses;i++)
          for(int j=0;j<DataTypes;j++)
              allocated[i][j]=0;
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
     tempallocated=new int[NoOfProcesses][DataTypes];
     available=new int[DataTypes];
     tempavailable=new int[DataTypes];
     ReleaseArray=new int[DataTypes];
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
    boolean isFinished[]=new boolean[NoOfProcesses];
   // getNeed();// calculate the need... Need=max-allocated but the temp allocated so it reaches 0 in the end
    int i=0; 
    int Order=0;
    if(RequestLessThanAvailable(req) && RequestLessThanNeed(ProcessWithRequest,req)){
        NeedandRequest(ProcessWithRequest,req,'-');// Need-=request....3yzen da yfdl sabt
        availableandRequest(req,'-');//available -= request... 3yzen da yfdl sabt
        //copy the available into the temp
        for(int c=0;c<DataTypes;c++)
            this.tempavailable[c]=this.available[c];
        //======================
        AllocatedandRequest(ProcessWithRequest,req,'+');// allocation+=request... hngrb lw yfdl sabt
        //el satreen dol mmkn mykonsh lehom lazma 
      //  AddRequestToTemp(ProcessWithRequest,req); // temp da bytshal feeh requestat w bashelha mel need mlhash 3laka bel checks
       // getNeed(); // msh mhtag a8yr feha,,, need-= previous requests
        //===============================
    //SafetyCheckLoop...mfrood enha mt8yrsh ay haga hya bt3ml hesabat bas 
    while(i<NoOfProcesses)
    {  
        boolean getout=false; //safety variable....3shan lw hasal f mra w 3mlt loop kmla mn l inner loop w mdkhltsh wla mra el if,a3rf atla3 mnha
        for(int r=0;r<NoOfProcesses;r++)
        {
            if(NeedLessThanAvailable(r) && !isDone[r]) // NeedLessThanTempAvailable(r)
            {
                addAllocatedtoAvailable(r); // tempavailable+=allocated....lazm nl3b fl temp w lmethod bta3t l check htb2a 3l temp 3shan ehna hn3mlo copy b3d ma yt3ml l request
                //clearallocated(r);    // allocated =0
                isDone[r]=true; // hena b2a l r b2t l index 3shan zy ma olna el r bet-represent el current process
                getout=true;
                i++;
            } 
        }
        if(!getout)  // 3shan lw wahda fdlt sh8ala mnfdlsh nlef 3alfady gowa
            break;
    } 
    }
    //===============================
    else
    {  
           System.out.println("request wasn't granted Bec it didn't pass the checking whether it's less than available and need or not so it would pass the maximum number ");
    }
     if(i==NoOfProcesses)
         {
         //==================
         // release part of the  resources from allocation into the releasearray
          ReleaseFromAllocation(ProcessWithRequest);
         // add released part to the available
          availableandRequest(this.ReleaseArray,'+');
         //print available 
         System.out.println("Available Array");
         Print1DArray(this.available);
         // print max array
         System.out.println("Max Array ");
         Print2DArray(this.max);
         //==========================
         System.out.println("Allocated Array");
         // print tempallocated array
         Print2DArray(this.allocated);
         //=========================
         // print need array
         System.out.println("Need Array");
         Print2DArray(this.need);
         //==============================
         System.out.println("ReleasedValues");
         Print1DArray(this.ReleaseArray);
         //print request and Process with request
         System.out.println("The Process with Request: P"+ProcessWithRequest);
         System.out.print("The Request Array: ");
         Print1DArray(req);
          //==================
       System.out.println("System is Safe");
         }
       else if(i!=NoOfProcesses && RequestLessThanAvailable(req) && RequestLessThanNeed(ProcessWithRequest,req) ) // m3naah enna b3d ma ednaah el request lakn khala l system msh safe fna baraga3 kol haga zy ma kant w wla aknn l request hasal
         { 
             NeedandRequest(ProcessWithRequest,req,'+');
             availableandRequest(req,'+');
             AllocatedandRequest(ProcessWithRequest,req,'-');
             // fill allocated into temp2dMatrix
          /* for(int rows=0;rows<NoOfProcesses;rows++)
           {
               for(int coloumns=0;coloumns<DataTypes;coloumns++)
               {
                   this.tempallocated[rows][coloumns]=this.allocated[rows][coloumns];
               }
           }*/
             //==========================
              //print available 
         System.out.println("Available Array");
         Print1DArray(this.available);
         // print max array
         System.out.println("Max Array ");
         Print2DArray(this.max);
         //==========================
         System.out.println("Allocated Array");
         // print tempallocated array
         Print2DArray(this.allocated);
         //=========================
         // print need array
         System.out.println("Need Array");
         Print2DArray(this.need);
         //==============================
          
             
             System.out.println("Request wasn't granted because it made the System unsafe");
             //print request and Process with request
             System.out.println("The Process with Request: P"+ProcessWithRequest);
             System.out.print("The Request Array: ");
             Print1DArray(req);
              System.out.println("");
               //================== 
        }
     if(AvailableEqualsZero())
    {
     ReleaseFromAllocation(ProcessWithRequest);
     availableandRequest(this.ReleaseArray,'+');
    }
   }  
 public void AddRequestToTemp(int k,int[] req)
 {  
    for(int coloumns=0;coloumns<DataTypes;coloumns++)
               {
                   this.tempallocated[k][coloumns]+=req[coloumns];
               }
    
             //==========================
 }
 public boolean NeedLessThanAvailable(int k) // bb3tlha l noOfRow aknne ba3at satr wahd 
    {
        for(int i=0;i<DataTypes;i++)
        {
            if(this.need[k][i]>this.tempavailable[i])
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
             this.need[rows][coloumns]=this.max[rows][coloumns]-this.tempallocated[rows][coloumns];
           }
       }
    }
 public void getNeedAfterRelease()
 {
   /* for(int rows=0;rows<NoOfProcesses;rows++)
       {
           for(int coloumns=0;coloumns<DataTypes;coloumns++)
           {
               if(this.need[rows][coloumns]>=this.ReleaseArray[rows][coloumns])
                this.need[rows][coloumns]-=this.ReleaseArray[rows][coloumns];
           }
       }*/
 }
 public void addAllocatedtoAvailable(int k) // nfs fkrt el NeedLessThanAvailable 
    {
      for(int i=0;i<DataTypes;i++)
      {
       this.tempavailable[i]+=allocated[k][i];
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
 public boolean NeedNotEqualZero()
 {   for(int i=0;i<NoOfProcesses;i++)
     {
         for(int j=0;j<DataTypes;j++)
         {
             if(this.need[i][j]>0)
                 return true;
         }
     }
     return false;
 }
 public boolean NeedEqualsZero(int r) {
        for(int coloumns=0;coloumns<DataTypes;coloumns++)
        {
           if(this.need[r][coloumns]>0)
               return false;
        }
        return true;
     }
 public void Print2DArray(int[][] ar)
 {
   for(int i=0;i<NoOfProcesses;i++)
   {
       for(int j=0;j<DataTypes;j++)
       {
           System.out.print(ar[i][j]+" ");
       }
       System.out.println();
   }
 }
 public void Print1DArray(int[] ar)
 {
     for(int i=0;i<DataTypes;i++)
     { 
         System.out.print(ar[i]+" ");
     }
     System.out.println();
 }
public void ReleaseFromAllocation(int k)
{   Random r=new Random();
    int releasedvalue=0;
    for(int c=0;c<DataTypes;c++)
    {
        if(this.allocated[k][c]>0)
        {releasedvalue=(int)r.nextInt(this.allocated[k][c]+1/3);
        this.allocated[k][c]-=releasedvalue;
        this.ReleaseArray[c]=releasedvalue;
        }
    }
    
}

    private boolean AvailableEqualsZero()
    {
        for(int i=0;i<DataTypes;i++)
        {
            if(this.available[i]==0)
                return true;
        
        }
        return false;
    }
   
}
  
   
    

