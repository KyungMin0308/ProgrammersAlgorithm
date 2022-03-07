import java.util.*;
import java.util.Map.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        HashMap<String, String []> hm = new HashMap<String, String []>();
        
        for(int i=0; i<records.length; i++) {
        	String [] re = records[i].split(" ");
        	
        	String key = re[1]; //차량 번호
        	
        	//입차 출차 여부 확인 후 맵에 삽입
        	if(re[2].equals("IN")) {
        		if(hm.containsKey(re[1])) { //차량이 등록되어 있는 상황에서 나갔다가 다시 들어온 경우
        			String [] cur = hm.get(key);
        			
        			cur[0] = re[2]; //IN&OUT 갱신
        			cur[1] = re[0]; //출입시간 저장
        			
        			hm.put(key, cur); //갱신
        		}
        		else { //차량이 등록되어 있지 않은 상황에서 새롭게 들어온 경우
        			//IN&OUT, 출입시간, 누적시간 >> IN, 00:00, 0
        			String [] cur = new String [] {re[2], re[0], "0"};
        			hm.put(key, cur);
        		}
        	}
        	else if(re[2].equals("OUT")) { //차량이 무조건 등록되어 있는 상황
        		String [] cur = hm.get(key); //IN, 출입시간, 누적시간
        		
                //출입 시간을 분으로 변환
        		String [] c = cur[1].split(":");
        		int curTime = (Integer.parseInt(c[0]) * 60) + Integer.parseInt(c[1]);
        		
                //출차 시간을 분으로 변환
        		String [] t = re[0].split(":");
        		int outTime = (Integer.parseInt(t[0]) * 60) + Integer.parseInt(t[1]);
        		
                //누적 시간 계산
        		int totalTime = Integer.parseInt(cur[2]) + (outTime - curTime);
        		
        		cur[0] = re[2]; //OUT 상태로 갱신
        		cur[1] = ""; //출입시간 초기화
        		cur[2] = String.valueOf(totalTime); //누적 시간 저장
        		
        		hm.put(key, cur);
        	}
        }
        
        //출차 시간이 없는 경우 23:59로 출차 시간을 지정하여 누적 시간 계산
        for(String keys: hm.keySet()) {
        	String [] cur = hm.get(keys);
        	if(cur[0].equals("IN")) {
                //마지막 출입 시간
        		String [] c = cur[1].split(":");
        		int curTime = (Integer.parseInt(c[0]) * 60) + Integer.parseInt(c[1]);
        		
                //출차 시간(23:59)
        		int outTime = (23 * 60) + 59;
        		
                //누적 시간 계산
        		int totalTime = Integer.parseInt(cur[2]) + (outTime - curTime);
        		
        		cur[0] = "OUT";
        		cur[1] = "";
        		cur[2] = String.valueOf(totalTime);
        		
        		hm.put(keys, cur);
        	}
        }
        
        //차량 번호 작은 순으로 정렬
        List<Entry<String, String []>> entrys = new ArrayList<Entry<String, String []>>(hm.entrySet());
        
        Collections.sort(entrys, new Comparator<Entry<String, String []>>() {
        	public int compare(Entry<String, String []> e1, Entry<String, String []> e2) {
        		return Integer.parseInt(e1.getKey()) - Integer.parseInt(e2.getKey());
        	}
        });
        
        //정답 배열 생성
        answer = new int[entrys.size()];
        for(int i=0; i<entrys.size(); i++) {
        	String [] cur = entrys.get(i).getValue();
        	int totalTime = Integer.parseInt(cur[2]);
        	
            //주차 요금 계산
        	if(totalTime > fees[0]) {
        		double over = Math.ceil(((double) totalTime - fees[0]) / fees[2]);
        		answer[i] = fees[1] + (int) over * fees[3];
        	}
        	else {
        		answer[i] = fees[1];
        	}
        	
        }
        
        return answer;
    }
}