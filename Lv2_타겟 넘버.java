class Solution {
	
	private int count = 0; //타겟 넘버를 만드는 경우의 수 저장
    
    private void dfs(int [] numbers, int idx, int res, int target) {
        if(idx == numbers.length) { //마지막까지 탐색을 마침
            if(res == target) {
                count++;
            }
            return;
        }
        
        dfs(numbers, idx+1, res+numbers[idx], target); //더하기를 한 경우
        dfs(numbers, idx+1, res-numbers[idx], target); //빼기를 한 경우
    }

	public int solution(int[] numbers, int target) {
        int answer = 0;
        
        dfs(numbers, 0, 0, target);
        
        answer = this.count;
        
        return answer;
    }
}