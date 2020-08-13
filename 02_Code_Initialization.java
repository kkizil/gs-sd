
import java.math.*;
import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Main {

static NumberFormat f = new DecimalFormat("#0.000000000"); 

    
    public static void main(String[] args) {
        
 
      
        goldenSearch(0.1, -3, 3);
        
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
                System.out.println("The optimal x value is between: [a*, b*] = [" + f.format(a) + ", " + f.format(b) + "]");
                System.out.println("f(a*) = " + f.format(theta(a)));
                System.out.println("f(b*) = " + f.format(theta(b)));
                System.out.println("The code found the solution at step " + step_counter +".");
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
    
    public static double theta(double x){
    
        return Math.pow((x-2), 2);
    }
    
 
    
}