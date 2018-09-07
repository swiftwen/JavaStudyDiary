import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	
	public static String PrintMinNumber(int [] numbers) {

		String s = "";
		String[] strs = new String[]{"3","32","321"};
		Arrays.sort(strs);
		System.out.println(strs[0]+","+strs[1]+","+strs[2]);
		return s;
    }
	public static void main(String[] args) throws Exception {
	    List<Integer> list = new ArrayList<>();
	    list.add(1);
	    System.out.println(list.isEmpty()==false);
	}
}

