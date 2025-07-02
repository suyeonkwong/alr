import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];

        dp[0] = 1;  /// 얜 자기 자신보다 작은게 없음

        int result = 0;

        /// 현재 인덱스 값보다 작으면서 길이가 가장 큰 애들을 찾아서 +1 해주면서 갱신 하면 될듯
        for(int i=1; i<arr.length; i++) {

            int currentValue = arr[i];

            for(int j=0; j<i; j++) {
                if(arr[j] < currentValue) {
                   dp[i] = Math.max(dp[i], dp[j] + 1);
                } else {
                   dp[i] = Math.max(dp[i], 1);
                }

//                result = Math.max(result, dp[i]);

            }

        }

//        System.out.println(Arrays.toString(dp));

        for(int i=0; i<dp.length; i++) {
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
