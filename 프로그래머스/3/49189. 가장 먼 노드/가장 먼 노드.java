import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<Integer>[] list = new List[n+1];
        int[] dist = new int[n+1];
        
        for(int i=1; i<list.length; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            
            list[a].add(b);
            list[b].add(a);
        }
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[1] = 0;
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        queue.offer(new int[]{1, dist[1]});
        
        while(!queue.isEmpty()) {
        
            int[] now = queue.poll();
            
            for(int next : list[now[0]]) {
                
                if(dist[next] > now[1] + 1) {
                    dist[next] = now[1] + 1;
                    queue.offer(new int[]{next, dist[next]});
                }
                
            }
            
        }
        
        int max = 0;
        
        for(int i=1; i<dist.length; i++) {
            max = Math.max(max, dist[i]);
        }
        
        for(int i=1; i<dist.length; i++) {
            if(dist[i] == max) {
                answer++;
            }
        }
        
        return answer;
    }
}