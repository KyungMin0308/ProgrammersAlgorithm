class Solution {
    
    public int solution(String s) {
        int answer = 0;
        
        for(int i=0; i<s.length(); i++) {
            if(s.length()-i < answer) break; //남은 문자열 길이가 answer보다 짧으면 확인할 필요 없음
            for(int j=i; j<s.length(); j++) {
                //String sub = s.substring(i, j+1); -> substring 사용 시 시간 초과
                int len = (j+1) - i; //팰린드롬 문자열 길이
                int idx = j; //팰린드롬 종료 인덱스
                boolean check = true; //팰린드롬 확인 변수
                
                //팰린드롬 확인
                for(int k=i; k<j+1; k++) {
                    if(s.charAt(k) != s.charAt(idx--)) { //문자가 다르면
                        check = false; //팰린드롬이 아님
                        break;
                    }
                }
                if(check) answer = Math.max(answer, len); //정답 갱신
            }
        }

        return answer;
    }
}