import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @desc 
 * @Author wenpeng
 * @2018年6月20日 下午4:16:54
 */
public class Main {

	public static void main(String[] args) throws Exception{
      
		String time = "2018-08-06";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.parse(time));
	}

}
