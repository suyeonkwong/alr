import java.io.*;
import java.util.*;

public class Main {

    static public void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[n];
        for (int i=0; i<nums.length; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int min = Integer.MAX_VALUE;

        int start = 0;
        int end = nums.length-1;

        int a = nums[start];
        int b = nums[end];

        while (start < end) {

            int sum = nums[end] + nums[start];
            
            //-99, -2, -1, 4, 98
            
            if (Math.abs(sum) < min) {
                a = nums[start];
                b = nums[end];
                min = Math.abs(sum);
            }

            if (sum > 0) {
                end--;
            } else {
                start++;
            }
        }

        System.out.println(a + " " + b);
    }
}
