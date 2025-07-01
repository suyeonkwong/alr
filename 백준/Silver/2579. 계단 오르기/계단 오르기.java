import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];

        for(int i=1; i<arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;
        Queue<int[]> queue = new LinkedList<>();

        /// 계단 한개만 밟거나 or 두개 밟거나
        /// 근데 연속 된거 3번 밟으면 안됨

        /// 0 : index, 1 : sum, 2 : 연속 됨? -- 두번째라면 이미 한칸 건너띄고 온거니까 1로 초기화 할 것.

        /// 항상 두번 씩
        queue.offer(new int[]{0, 0, 0});
        int[][] dp = new int[n+1][2];

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
//            System.out.println(Arrays.toString(poll));

            /// 마지막 계단이면
            if(poll[0] == n) {
                max = Math.max(max, poll[1]);
            }

            /// 한계단씩 올라가거나
            /// n 범위 , 3칸 연속인지, 다음 값이 원래 값 + arr 값 더한것 보다 크면 max 값 갱신
            int index = poll[0];
            int sum = poll[1];
            int flow = poll[2];
            if(index + 1 <= n
                    && flow + 1 <= 2
                    && dp[index+1][0] < sum + arr[index+1]) {
                dp[index+1][0] = sum + arr[index+1];
                queue.offer(new int[]{index+1, dp[index+1][0], flow + 1});
            }

            /// 두계단씩 올라가거나
            if(index + 2 <= n
                    && dp[index+2][1] < sum + arr[index+2]) {
                dp[index+2][1] = sum + arr[index+2];
                queue.offer(new int[]{poll[0] + 2, dp[index+2][1], 1});
            }
        }

        System.out.println(max);

    }
}
