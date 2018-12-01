package os;

import java.util.Random;

public class OS {

    public static void main(String[] args) {
    Banker b = new Banker();
    b.input();
    int[] Request=new int[b.DataTypes];
    Random rand=new Random();
    
     
     for(int n=0;n<7;n++)
     { 
      // fill requests
     for(int i=0;i<b.DataTypes;i++)
     {
        Request[i]=rand.nextInt(7);
     }
     //==================== 
       int Pwr=rand.nextInt(b.NoOfProcesses);
       b.banker(Pwr,Request);
    
     }
     
          
                  
    }
    
}
