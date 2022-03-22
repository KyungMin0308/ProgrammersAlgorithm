import java.util.*;

class Solution {
    
    private HashMap<String, ArrayList<String>> graph; //항공권 그래프
    private HashMap<String, Integer> used; //티켓 사용 여부
    private ArrayList<String> res; //경로 저장 리스트
    
    //전체 티켓을 사용했는지 확인
    public boolean allUsed(HashMap<String, Integer> used) {
        for(String keys: used.keySet()) {
            if(used.get(keys) != 0) return false;
        }
        return true;
    }
    
    //경로 탐색 DFS 함수
    public void Route(String from) {
    	res.add(from); //경로 추가
    	
    	if(allUsed(used)) return; //전체 티켓을 사용했으면 리턴
    	
    	ArrayList<String> countrys = graph.get(from); //from이 방문 가능한 공항 리스트
    	if(countrys != null) {
    		for(int i=0; i<countrys.size(); i++) {
    			String to = countrys.get(i); //방문 가능한 다음 공항
    			String ticket = from + to; //티켓 생성
    			
    			if(used.get(ticket) == 0) continue; //티켓이 없다면 Continue
    			
    			used.put(ticket, used.get(ticket)-1); //티켓 사용
    			Route(to); //다음 공항부터 다시 탐색
    			
    			if(allUsed(used)) return; //전체 티켓을 사용했으면 리턴
    			
    			used.put(ticket, used.get(ticket)+1); //사용했던 티켓을 다시 초기화
    		}
    	}
    	
    	res.remove(res.size()-1); //리스트의 마지막 원소 제거
    }
    
    //그래프 생성
    public void makeGraph(String [][] tickets) {
    	graph = new HashMap<String, ArrayList<String>>();
    	used = new HashMap<String, Integer>();
    	
    	for(int i=0; i<tickets.length; i++) {
    		String from = tickets[i][0]; //출발 공항
    		String to = tickets[i][1]; //도착 공항
    		if(graph.containsKey(from)) {
    			graph.get(from).add(to); //출발 공항에 경로 추가
    		}
    		else {
    			graph.put(from, new ArrayList<String>()); //리스트 생성
    			graph.get(from).add(to); //출발 공항에 경로 추가
    		}
    		
    		String ticket = from + to; //티켓 생성
    		if(used.containsKey(ticket)) { //티켓이 있다면
    			used.put(ticket, used.get(ticket)+1); //티켓 수 증가
    		}
    		else {
    			used.put(ticket, 1); //티켓 추가
    		}
    	}
    	
    	//이동 가능한 공항 오름차순으로 정렬
    	for(ArrayList<String> lst: graph.values()) {
    		Collections.sort(lst);
    	}
    }
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        res = new ArrayList<String>(); //경로 저장
        makeGraph(tickets); //그래프 생성
        
        //ICN 부터 탐색 시작
        Route("ICN");
        
        //정답 배열 생성
        answer = new String[res.size()];
        for(int i=0; i<res.size(); i++) {
            answer[i] = res.get(i);
        }
        
        return answer;
    }
}