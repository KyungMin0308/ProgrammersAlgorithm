class Solution {
    
    //mid명의 니니즈가 징검다리를 건널 수 있는지 판단
    public boolean isCheck(int [] stones, int k, int mid) {
        int count = 0; //건널 수 없는 니니즈의 수
        for(int i=0; i<stones.length; i++) {
            if(stones[i] < mid) { //stones[i]가 mid보다 작으면 건널 수 없음
                count++;
                if(count >= k) return false;
            }
            else count = 0; //stones[i]가 mid보다 크면 건널 수 있으므로 count를 0으로 초기화
        }        
        return true;
    }
    
    public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1; //니니즈 친구들 최소 인원수
        int max = 200000000; //니니즈 친구들 최대 인원수
        
        //이분탐색
        while(min <= max) {
            int mid = (min + max) / 2; //징검다리를 건널 수 있는 인원수
            if(isCheck(stones, k, mid)) {
                //건널 수 있음
                min = mid + 1;
                answer = mid;
            }
            else {
                //건너지 못함
                max = mid - 1;
            }
        }
        
        return answer;
    }
}