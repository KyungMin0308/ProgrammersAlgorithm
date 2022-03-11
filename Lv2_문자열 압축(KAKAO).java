class Solution {
    public int solution(String s) {
        int answer = s.length(); //문자열 s의 길이로 초기화
        
        //압축은 s의 길이의 절반까지만 가능
        for(int i=0; i<s.length()/2; i++) {
            String left = s.substring(0, i+1); //압축 기준 문자열
            int next = left.length(); //문자열 자르는 범위
            int cnt = 1; //left와 같은 문자열 수 카운트
            String compression = ""; //압축된 문자열 변수
            int finIdx = -1; //압축되지 않는 남은 문자열의 시작 인덱스 변수
            
            //압축 기준 문자열 다음 문자부터 끝까지 진행
            for(int j=i+1; j<s.length(); j+=next) {
                //압축된 문자열의 길이보다 남은 문자열의 수가 짧은 경우(압축 불가능)
                if(j+next > s.length()) {
                    finIdx = j; //남은 문자열의 시작 인덱스 저장
                    break; //for문 탈출
                }
                //압축 기준 문자열과 뒤의 문자열이 같은 경우(압축 가능)
                else if(left.equals(s.substring(j, j+next))) {
                    cnt++; //개수 증가
                }
                //압축 기준 문자열과 다른 경우(이전 문자열까지 압축 가능)
                else {
                    //문자열 압축
                    if(cnt != 1) compression += (String.valueOf(cnt) + left);
                    else compression += left;
                    
                    //다음 기준 문자열로 갱신 및 개수 초기화
                    left = s.substring(j, j+next);
                    cnt = 1;
                }
            }
            
            //for문 탈출 이후 탈출 전까지 카운트된 문자열 압축
            if(cnt != 1) compression += (String.valueOf(cnt) + left);
            else compression += left;
            
            //finIdx가 -1이 아니라면 뒤에 압축이 불가능한 남은 문자열이 존재함을 의미
            //압축 불가능한 남은 문자열을 이어붙임
            if(finIdx != -1) compression += s.substring(finIdx);
            
            //가장 짧은 문자열 길이로 갱신
            answer = Math.min(answer, compression.length());
        }
        
        return answer;
    }
}