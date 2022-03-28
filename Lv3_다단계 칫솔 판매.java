import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        HashMap<String, String> parentMap = new HashMap<String, String>(); // (자식, 부모) 해시맵
        HashMap<String, Integer> enrollIndexMap = new HashMap<String, Integer>(); // 판매원 인덱스 해시맵
        
        // 해시맵 초기화
        for(int i=0; i<enroll.length; i++) {
            String en = enroll[i]; // 자신
            String re = referral[i]; // 추천인
            
            parentMap.put(en, re);
            enrollIndexMap.put(en, i);
        }
        
        // 판매금액 계산
        for(int i=0; i<seller.length; i++) {
            String cur = seller[i]; // 현재 판매원
            int curAmount = 100 * amount[i]; // 최초 판매금액
            
            // center(-)를 만날 때까지 반복
            while(!cur.equals("-")) {
                int toParent = curAmount / 10; // 부모(추천인)에게 넘겨줄 금액
                int idx = enrollIndexMap.get(cur); // 현재 판매원 인덱스
                
                answer[idx] += (curAmount - toParent); // 현재 판매원의 최종 판매금액(최초 판매금액 - 수수료)
                
                // 부모로 이동
                cur = parentMap.get(cur);
                
                // 부모에게 넘겨줄 금액으로 curAmount 초기화
                curAmount = toParent;
                
                // 부모에게 넘겨줄 금액이 1원 미만이면 넘기지 않음
                if(curAmount < 1)
                    break;
            }
        }
        
        return answer;
    }
}