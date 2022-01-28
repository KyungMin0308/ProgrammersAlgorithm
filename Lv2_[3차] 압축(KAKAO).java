import java.util.*;

class Solution {
    
    public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> res = new ArrayList<Integer>(); // 사전 색인 번호 저장 리스트
        Map<String, Integer> hm = new HashMap<String, Integer>(); // 사전 해시맵
        
        // 사전 초기화(A~Z)
        int num = 1;
        for(char c='A'; c<='Z'; c++) {
        	hm.put(Character.toString(c), num);
        	num++;
        }
        
        // 문자열을 돌면서 압축
        String cur = "", next = "";
        for(int i=0; i<msg.length(); i++) {
        	cur = Character.toString(msg.charAt(i));
        	if(i != msg.length()-1) { // i가 마지막 문자 인덱스가 아니라면
        		for(int j=i+1; j<msg.length(); j++) { // i를 기준으로 뒤의 문자부터 돌면서 사전에 있는지 확인
            		next = Character.toString(msg.charAt(j));
            		
            		if(!hm.containsKey(cur+next)) { // cur+next가 사전에 없다면
            			res.add(hm.get(cur)); // cur의 사전 색인 번호를 리스트에 삽입
            			hm.put(cur+next, num); // cur+next를 사전에 추가
            			num++;
            			i = j-1; // 문자열 탐색 인덱스 조정
            			break;
            		}
            		else { // cur+next가 사전에 있다면
            			cur += next; // cur를 cur+next로 갱신하고 다음 위치 문자로 넘어감
                        
            			if(j == msg.length()-1) { // 만약 j가 문자열의 마지막 위치라면
            				res.add(hm.get(cur)); // 사전 색인 번호를 리스트에 추가하고
            				i = j; // 뒤에 더 문자가 없으므로 탐색 종료
            				break;
            			}
            		}
            	}
        	}
        	else {
        		//next = "";
        		res.add(hm.get(cur)); // i가 마지막 문자 인덱스라면 사전 색인 리스트에 번호 삽입
        	}
        	
        	//System.out.println("[" + i + "]" + cur + " " + next);
        }
        
        // 정답 배열 복사
        answer = new int[res.size()];
        for(int i=0; i<answer.length; i++)
        	answer[i] = res.get(i);
        
        return answer;
    }
}