import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        Map<String, String> hm = new HashMap<String, String>();
        ArrayList<String> res = new ArrayList<String>();
        
        //유저 닉네임 처리
        for(int i=0; i<record.length; i++) {
            String [] msgs = record[i].split(" ");
            
            String cmd = msgs[0];
            String userId = msgs[1];
            String name = "";
            
            //명령어가 Enter, Change라면 key에 해당하는 value값을 삽입 or 갱신
            if(cmd.equals("Enter") || cmd.equals("Change")) {
            	name = msgs[2];
            	hm.put(userId, name);
            }
        }
        
        //채팅방 메시지 생성
        for(int i=0; i<record.length; i++) {
        	String [] msgs = record[i].split(" ");
            
            String cmd = msgs[0];
            String userId = msgs[1];
            String name = hm.get(userId); //userId에 해당하는 닉네임 가져옴
            
            if(cmd.equals("Change")) continue;
            
            String str = "";
            if(cmd.equals("Enter")) {
            	str = name + "님이 들어왔습니다.";
            }
            else if(cmd.equals("Leave")) {
            	str = name + "님이 나갔습니다.";
            }
            
            res.add(str);
        }
        
        //정답 배열 복사
        answer = new String[res.size()];
        res.toArray(answer);
        
        return answer;
    }
}