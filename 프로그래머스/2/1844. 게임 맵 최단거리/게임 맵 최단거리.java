import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = -1;
        
        int[][] direct = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        // x y dist
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{0,0,1});
        maps[0][0] = 0;
        
        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            
            if(poll[0] == maps.length-1 && poll[1] == maps[0].length-1) {
                return poll[2];
            }
            
            for(int i=0; i<direct.length; i++) {
                int nx = poll[0] + direct[i][0];
                int ny = poll[1] + direct[i][1];
                
                if(nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length 
                   && maps[nx][ny] == 1) {
                   
                    maps[nx][ny] = 0;
                    queue.offer(new int[]{nx, ny, poll[2] + 1});
                }
            }
        }
        
        return answer;
    }
}