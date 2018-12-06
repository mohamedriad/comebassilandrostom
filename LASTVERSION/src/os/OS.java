package os;

import java.util.Random;

public class OS {
    public static void main(String[] args) {
    int q=1;
    Banker b = new Banker();
    b.input();
    int[] Request=new int[b.DataTypes];
    Random rand=new Random();
    b.getNeed();
    int[] order=new int[b.NoOfProcesses];
    boolean[] isFinished=new boolean[b.NoOfProcesses];
    int Count=0;// for ordering
    int n=0;// counting requests
    do
     { 
      // fill requests
     for(int i=0;i<b.DataTypes;i++)
     {
        Request[i]=rand.nextInt(q+5);
     }
     //==================== 
       System.out.println("Request:"+n);
       n++;
       int Pwr=rand.nextInt(b.NoOfProcesses);
       b.banker(Pwr,Request);
       System.out.println("===============");
       for(int t=0;t<b.NoOfProcesses;t++)
       {
           if(b.NeedEqualsZero(t) && !isFinished[t])
                {
                    isFinished[t]=true;
                    order[Count] =t;
                    Count++;
                }
       }
      } while(b.NeedNotEqualZero());
    // OrderOfProcesses
      System.out.println("The Order of Processes");
      for(int h=0;h<b.NoOfProcesses;h++)
          System.out.println("P"+order[h]);
      //================================
    }
    
}
