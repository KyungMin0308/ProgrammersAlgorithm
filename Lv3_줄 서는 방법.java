import java.util.*;

class Solution {
    
    private long [] factorial; //팩토리얼 배열
    
    //팩토리얼 배열 초기화 함수
    public void fac(int n) {
        factorial[0] = 1;
        factorial[1] = 1;
        for(int i=2; i<=n; i++) {
            factorial[i] = i * factorial[i-1];
        }
    }
    
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<Integer> nums = new ArrayList<Integer>(); //1~n까지 숫자 리스트
        factorial = new long[n+1]; //n팩토리얼 저장 배열
        
        for(int i=1; i<=n; i++) nums.add(i); //리스트 초기화
        fac(n); //n팩토리얼 생성
        
        int ansIdx = 0; //정답배열 인덱스
        long start = k-1; //ansIdx 위치에 들어갈 숫자가 nums 리스트의 몇번째 인덱스인지 알기 위한 시작값
        while(n > 0) {
            int lstIdx = (int) (start / factorial[n-1]); //리스트 인덱스
            
            answer[ansIdx++] = nums.get(lstIdx); //정답 배열에 숫자 저장
            
            nums.remove(lstIdx); //리스트에서 정답 배열에 추가된 숫자 제거
            
            start %= factorial[n-1]; //start 갱신
            n--; //n 감소
        }
        
        return answer;
    }
}