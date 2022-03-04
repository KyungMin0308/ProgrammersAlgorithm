import java.util.*;

class Solution {
    
    private boolean [] visited; //방문 처리 배열
    private ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); //그래프
    
    //v에 연결된 네트워크를 방문하는 DFS 함수
    public void dfs(int v) {
        visited[v] = true; //v번 네트워크 방문 처리
        
        for(int i=0; i<graph.get(v).size(); i++) {
            int next = graph.get(v).get(i); //v번 네트워크와 연결된 다음 네트워크 번호
            if(!visited[next]) { //다음 네트워크를 방문하지 않았다면
                dfs(next);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        //그래프 초기화
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        //그래프 생성
        for(int i=0; i<computers.length; i++) {
            for(int j=0; j<computers[i].length; j++) {
                if(i == j) continue;
                if(computers[i][j] == 1) { //1이라면 연결되어 있음을 의미
                    graph.get(i).add(j);
                }
            }
        }
        
        //네트워크 개수 구하기
        for(int i=0; i<n; i++) {
            if(!visited[i]) { //i번 네트워크를 방문하지 않았다면
                answer++; //네트워크 개수 카운트 후
                dfs(i); //i번에서 연결된 네트워크 DFS
            }
        }
        
        return answer;
    }
}