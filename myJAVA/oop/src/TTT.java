import java.util.Arrays;

public class TTT {
	
    public static int[] removeElement(int[] arr, int item) {
        return Arrays.stream(arr)
                .filter(i -> i != item)
                .toArray();
    }
    
    public static void main(String[
                                   ] args) {
    	
    	int[] arr = new int[3];
    	
    	arr[0] = 2;
    	arr[1] = 5;
    	arr[2] = 7;
    	
    	int item = 5;
    	
    	arr = removeElement(arr, item);
    	
    	System.out.println(Arrays.toString(arr));
    	System.out.println("length" + arr.length);
    	
    }
    
}

