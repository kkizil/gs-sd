
import java.math.*;
import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Main {
    
    static final int NUM_OF_VAR = 2;
    
    static double[] x = new double[NUM_OF_VAR];
    static double[] d = new double[NUM_OF_VAR];

static NumberFormat f = new DecimalFormat("#0.0000000000"); 

    
    public static void main(String[] args) {
        
        x[0] = 2;
        x[1] = 5;
        
        d[0] = 0;
        d[1] = -1;
 
      
        goldenSearch(0.0000000001, -5000, 5000);
        
    }
    
    public static List<Double> goldenSearch(double l, double a, double b){
        
        double alpha = (-1 + Math.sqrt(5))/2;
        
        double lambda;
        double mu;
    
        double step_counter = 1;
        
        lambda = a + (1-alpha)*(b-a);
        mu = a + (alpha)*(b-a);
        
        while(true){
            if(b-a < l){
                System.out.println("The optimal input value for theta (i.e. optimal stepsize) is between: [" + f.format(a) + ", " + f.format(b) + "]");
                System.out.println("f(x + d*a) = " + f.format(theta(a)));
                System.out.print("x + d*a = [");
                for(int i = 0; i<NUM_OF_VAR; i++){
                 System.out.print(f.format(arraySum(x, scalarArrayMult(a, d))[i]));
                 if(i != NUM_OF_VAR - 1){
                     System.out.print(", ");
                 }
                }
                System.out.print("]");
                System.out.println();
                
                      System.out.println("f(x + d*b) = " + f.format(theta(b)));
                System.out.print("x + d*b = [");
                for(int i = 0; i<NUM_OF_VAR; i++){
                 System.out.print(f.format(arraySum(x, scalarArrayMult(b, d))[i]));
                 if(i != NUM_OF_VAR - 1){
                     System.out.print(", ");
                 }
                }
                System.out.print("]");
                System.out.println();
                
                System.out.println("The code found the solution at step " + step_counter);
                return(Arrays.asList(a,b));
                
            }
            else{
                if(theta(lambda)>theta(mu)){
                    a = lambda;
                    lambda = mu;
                    mu = a + alpha*(b-a);
                }
                if(theta(lambda)<= theta(mu)){
                    b = mu;
                    mu = lambda;
                    lambda = a + (1-alpha)*(b-a);
                }
                step_counter++;
            }
        }
        
    }
    
    public static double theta(double input){
        
        // return 3*Math.pow(input, 4) - 8*Math.pow(input, 3);
        // return Math.pow(input, 3);
        // return Math.pow(input, 2) + 5 - 2*x;
        // return Math.pow((input-2), 2);
        
        
        return f(arraySum(x, scalarArrayMult(input, d)));
    }
    
    public static double f(double[] input){
        
        
        return Math.pow((input[0] - 2), 4) + Math.pow((input[0] - 2*input[1]), 2);
    }
    
    public static double[] scalarArrayMult(double scalar, double[] array){
        double[] result_array = new double[array.length];
        for (int i=0; i<array.length; i++) {
        result_array[i] = array[i] * scalar;
          }
        
        return result_array;
    }
    
    public static double[] arraySum(double[] array1, double[] array2){
        double[] result_array = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
        result_array[i] = array1[i] + array2[i];
        }
        
        return result_array;
    }
    
}
