import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

public static double myPow(double x, int n) {
        BigDecimal ret = BigDecimal.ONE;
        BigDecimal xx = BigDecimal.valueOf(x);
        boolean flag = false;
        if(n<0){
        	n = -n;
        	flag = true;
        }
        while(n > 0){
        	if((n&1)==1){
        		ret = ret.multiply(xx);
        		n--;
        	}else{
        		xx = xx.multiply(xx);
        		n>>=1;
        	}
        }
        if(flag){
        	ret = BigDecimal.ONE.divide(ret);
        }
       return ret.doubleValue();
    }
	public static void main(String[] args) throws Exception{
      
		System.out.println(myPow(2,40));
	}

}
