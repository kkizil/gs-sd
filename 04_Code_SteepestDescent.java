

/**
 *
 * @author Kerim Uygur Kizil, ID#70494
 * INDR 511 HW#4 
 * Due: 28.02.2020
 * 
 */

import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Main {
    
    static final int NUM_OF_VAR = 2;
    
    static double[] x = new double[NUM_OF_VAR];
    static double[] d = new double[NUM_OF_VAR];
    static double epsilon = 0.000001; // For checking if -Gradient(f(x)) is 0.
    
static NumberFormat f = new DecimalFormat("#0.000000000"); 

    
    public static void main(String[] args) {
        
        x[0] = 6;
        x[1] = 6;
        
        System.out.println("Initial f(x) is: " + f(x));
        
        int k = 0;
        while(true){
            
           d = scalarArrayMult(-1, gradientCalc(x)); 
           System.out.println("Iteration " + k);
           System.out.print("Gradient at x = (");
            for(int i = 0; i<NUM_OF_VAR; i++){
                 System.out.print(f.format(x[i]));
                 if(i != NUM_OF_VAR - 1){
                     System.out.print(", ");
                 }
                }
            System.out.print(") is: ");
            
            
            System.out.print("(");
            for(int i = 0; i<NUM_OF_VAR; i++){
                 System.out.print(f.format(d[i]));
                 if(i != NUM_OF_VAR - 1){
                     System.out.print(", ");
                 }
                }
            System.out.print(").");
            System.out.println();
                        
           
           if(arrayDiffLessThan(d, new double[d.length], epsilon)){
               System.out.println("Therefore, the solver procedure is stopped.");
               System.out.println("The procedure stopped at step " + k);
               break;
           }
           goldenSearch(0.0001, -5000, 5000);
           k++;
        }
 
      
        
        
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
                System.out.println("Optimal stepsize is between: [" + f.format(a) + ", " + f.format(b) + "]");
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
                
                System.out.println("***************************************");
                
                
                for(int i = 0; i<NUM_OF_VAR; i++){
                    x[i] = arraySum(x, scalarArrayMult(a, d))[i];
                }
                
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
    
    public static double[] gradientCalc(double[] x_k){
        
        double h = Math.pow(10, -11);
        double[] grad_array = new double[x_k.length];
        
        for(int i = 0; i<x_k.length; i++){
            double[] x_increased = x_k.clone();
            x_increased[i] = x_increased[i] + h;
            grad_array[i] = (f(x_increased) - f(x_k))/h;
            
        }
                
        return grad_array;
    }
    
    public static boolean arrayDiffLessThan(double[] arr1, double[] arr2, double error){
        
        if(arr1.length != arr2.length){
            return false;
        }
        
        for(int i = 0; i<arr1.length; i++){
            if(Math.abs(arr1[i] - arr2[i]) >= error){
                return false;
            }
        }
        
        return true;
    }
    
}
