import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int[][] direct = {{0,1}, {0,-1}, {-1,0}, {1,0}};
    static int[][] arr;
    static Set<String> set;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[5][5];
        set = new HashSet<>();

        for(int i=0; i<arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /// 1. 일단 arr 전체를 시작점으로 두는 법.
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                dfs(0, i, j, new String[6]);
            }
        }

        System.out.println(set.size());


        /// 2. 한 곳을 정해놓고 이동해서 문자열로 자르느 법.
    }

    static void dfs(int depth, int x, int y, String[] strs) {

        strs[depth] = String.valueOf(arr[x][y]);
        if(depth == 5) {
//            System.out.println(Arrays.toString(strs));
            set.add(Arrays.toString(strs));
            return;
        }

        for(int i=0; i<direct.length; i++) {
            int nx = x + direct[i][0];
            int ny = y + direct[i][1];

            if(nx >= 0 && nx < arr.length && ny >= 0 && ny < arr.length) {
                dfs(depth+1, nx, ny, strs);
            }
        }

    }
}
