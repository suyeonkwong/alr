import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /// 다익스트라는 양의 가중차만 있을 때 최소 거리이고
        /// 얘는 최장 거리 & 사이클 없음 때문에 위상 정렬로 접근.

        int n = Integer.parseInt(st.nextToken());
        int[][] direct = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] arr = new int[n][n];
        int[][] edgeCount = new int[n][n];
        List<List<Node>>[] list = new ArrayList[n];

        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            list[i] = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                /// 연결 리스트 구현 ex > 0,0 -> 0,1 로 이동 가능
                list[i].add(new ArrayList<>());
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                for (int h = 0; h < direct.length; h++) {
                    int nx = i + direct[h][0];
                    int ny = j + direct[h][1];
                    /// 이동 거리가 더 큰 애들을 인접 리스트로 구현
                    if (nx >= 0 && nx < arr.length && ny >= 0 && ny < arr.length && arr[i][j] < arr[nx][ny]) {
                        list[i].get(j).add(new Node(nx, ny));
                        edgeCount[nx][ny]++;
                    }
                }
            }
        }

//        for (int i = 0; i < list.length; i++) {
//            for (int j = 0; j < list[i].size(); j++) {
//                System.out.print(i + "," + j + "= ");
//                for (int h = 0; h < list[i].get(j).size(); h++) {
//                    System.out.print(list[i].get(j).get(h).x + "," + list[i].get(j).get(h).y + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

        Queue<Node> queue = new LinkedList<>();
        int[][] dp = new int[n][n];

        /// 연결 갯수가 0인 애들은 오는게 없는 출발이 가능, 즉 나 자신 출발
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].size(); j++) {
//                System.out.print(edgeCount[i][j] + " ");
                if(edgeCount[i][j] == 0) {
                    queue.offer(new Node(i, j));
                    dp[i][j]++; /// 나 자신 대나무 먹기
                }
            }
//            System.out.println();
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            /// 방문 노드와 연결된 애들의 정점 갯수를 하나씩 줄여야 함
            List<Node> linked = list[node.x].get(node.y);

            for(Node a : linked) {
                /// 연결 간선 중 최대 거리 갱신
                dp[a.x][a.y] = Math.max(dp[a.x][a.y], dp[node.x][node.y] + 1);
                /// 간선 연결 갯수 빼기
                edgeCount[a.x][a.y]--;
                if(edgeCount[a.x][a.y] == 0) {
                    queue.offer(a);
                }
            }
        }

        int max = 0;

        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp.length; j++) {
//                System.out.print(dp[i][j] + " ");
                if(max < dp[i][j]) {
                    max = dp[i][j];
                }
            }
//            System.out.println();
        }

        System.out.println(max);

    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
