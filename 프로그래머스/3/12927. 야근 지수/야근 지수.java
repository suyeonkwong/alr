import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        /// n을 분산시켜 최대한 works의 원소를 작게 만드는거
        for(int i=0; i<works.length; i++) {
            queue.offer(works[i]);
        }
        
        while(!queue.isEmpty()) {
            
            if(n <= 0 || queue.peek() <= 0) {
                break;
            }
            
            queue.offer(queue.poll()-1);
            n--;
        }
        
        while(!queue.isEmpty()) {
            
            int poll = queue.poll();
            System.out.print(poll + " ");
            answer += Math.pow(poll, 2);
        }
        
        return answer;
    }
}