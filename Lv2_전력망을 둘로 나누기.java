import java.util.*;

class Solution {
    private ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); //그래프
	private boolean [] visited; //방문표시
	private int [] res = new int[2]; //나눠진 전력망에 몇개의 노드가 있는지 저장할 배열
	
	private void dfs(int v, int idx) {
		visited[v] = true;
		res[idx]++; //노드 개수 카운트
		
		for(int i=0; i<graph.get(v).size(); i++) {
			int next = graph.get(v).get(i);
			if(!visited[next]) {
				dfs(next, idx);
			}
		}
	}
	
	public int solution(int n, int[][] wires) {
        int answer = -1;
        
        //배열이 간선의 모임이니까 배열을 돌면서 해당하는 간선을 제외한 나머지 간선들로 연결된 노드들을 파악한다.
        for(int i=0; i<wires.length; i++) {
        	
        	//배열 초기화
        	for(int k=0; k<n+1; k++) { graph.add(new ArrayList<Integer>()); }
    		visited = new boolean[n+1];
        	
    		//그래프 생성
        	for(int j=0; j<wires.length; j++) {
        		if(i == j) continue;
        		
        		int x = wires[j][0];
        		int y = wires[j][1];
        		
        		graph.get(x).add(y);
        		graph.get(y).add(x);
        	}
        	
        	//노드 탐색
        	dfs(wires[i][0], 0);
        	dfs(wires[i][1], 1);
        	
        	if(answer == -1) { answer = Math.abs(res[0]-res[1]); }
        	else { answer = Math.min(answer, Math.abs(res[0]-res[1])); }
        	
        	//초기화
        	res[0] = 0;
        	res[1] = 0;
        	graph.clear();
        }
        
        return answer;
    }
}