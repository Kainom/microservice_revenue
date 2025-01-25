package revenue.example.revenue.utils;

public class ExpenseTotalUtil {

    private ExpenseTotalUtil(){}

    public static Double updateValue(Double total,Double old,Double newValue){
        
        if(old == newValue){
            return 0.0d;
        }

        if(newValue < old){
            return old - newValue;
        }

        if(newValue > old){

        }



        return 0.0d;
    }
    
}
