import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        /// 벽을 최소로 부시는(비용이 제일 적은) 횟수

        /// 다음 위치에 저장된 비용이 현재 큐에서 꺼낸 비용 + 현재 위치에 저장된 비용 보다


        int[][] arr = new int[n][n];
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {

            String[] str = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
                dist[i][j] = Integer.MAX_VALUE;
            }

        }

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        dist[0][0] = 0;
        queue.offer(new Node(0, 0, 0));

        int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {

            Node node = queue.poll();
            /// 이미 끝방에 도착하면 더 돌릴 필요가 없음.

            if (node.x == n - 1 && node.y == n - 1) {
                continue;
            }

            for (int i = 0; i < direction.length; i++) {

                int nx = node.x + direction[i][0];
                int ny = node.y + direction[i][1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {

                    /// 흰방이면 통과
                    if (arr[nx][ny] == 1 && dist[nx][ny] > dist[node.x][node.y]) {
                        dist[nx][ny] = dist[node.x][node.y];
                        queue.offer(new Node(nx, ny, dist[nx][ny]));
                    }

                    /// 검은방이면 벽 부셔야 함
                    else if (arr[nx][ny] == 0 && dist[nx][ny] > dist[node.x][node.y] + 1) {
                        dist[nx][ny] = dist[node.x][node.y] + 1;
                        queue.offer(new Node(nx, ny, dist[nx][ny]));
                    }
                }


            }

        }

        System.out.println(dist[n-1][n-1]);

//        for(int i=0; i<n; i++) {
//            for(int j=0; j<n; j++) {
//                System.out.print(dist[i][j] + " ");
//            }
//            System.out.println();
//        }

    }

    static class Node {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

}
