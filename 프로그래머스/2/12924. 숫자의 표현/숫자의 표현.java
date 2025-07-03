class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int start = 0;
        int end = 0;
        int sum = 0;
        
        while(end <= n) {
            // System.out.println(start + "," + end + "/" + sum);
            if(sum == n) {
                answer++;
                end++;
                sum += end;
            } else if(sum < n) {
                end++;
                sum += end;
            } else if(sum > n) {
                start++;
                sum -= start;
            }
        }
        
        return answer;
    }
}