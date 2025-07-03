import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        
        List<int[]> list = new ArrayList<>();
        
        int[] answer = {};
        
        int start = 0;
        int end = 0;
        int sum = sequence[0];
        
        while(start <= end && end < sequence.length) {
            // System.out.println(start + " " + end + " = " + sum);
            if(sum == k) {
                list.add(new int[]{start, end});
                end++;
                if(end >= sequence.length) {
                    continue;
                }
                sum += sequence[end];
            } else if(sum < k) {
                end++;
                if(end >= sequence.length) {
                    continue;
                }
                sum += sequence[end];
            } else if(sum > k) {
                sum -= sequence[start];
                start++;
            }
        }
        
        list.sort((o1, o2) -> (o1[1] - o1[0]) - (o2[1] - o2[0]));
        
        answer = list.get(0);
        
        return answer;
    }
}