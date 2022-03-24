import java.util.*;

class Solution {
    
    ArrayList<ArrayList<Integer>> graph; //경기 결과 그래프
    private int [][] headToHead; //경기 결과(전적) 저장
    private boolean [] visited; //방문 처리 배열
    
    //그래프 생성
    public void makeGraph(int n, int [][] results) {
        graph = new ArrayList<ArrayList<Integer>>();
        
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        for(int i=0; i<results.length; i++) {
            graph.get(results[i][0]).add(results[i][1]);
        }
    }
    
    //부모 노드 기준 방문 가능한 자식 노드는 부모 노드가 모두 이길 수 있음을 의미
    //win: 부모 노드
    //lose: 자식 노드
    //승(1), 패(-1), 결과 없음(0)
    //DFS 탐색
    public void dfs(int win, int lose, boolean [] visited) {
        visited[lose] = true;
        if(win != lose) {
            headToHead[win][lose] = 1; //win선수가 lose선수를 이김(1)
            headToHead[lose][win] = -1; //lose선수가 win선수에게 짐(-1)
        }
        for(int i=0; i<graph.get(lose).size(); i++) {
            int next = graph.get(lose).get(i);
            if(!visited[next])
                dfs(win, next, visited);
        }
    }
    
    //순위를 알 수 있는 선수의 수 구하는 함수
    public int getCountRank(int [][] arr) {
        int count = 0; //순위를 알 수 있는 선수의 수
        for(int i=1; i<arr.length; i++) {
            int cntZero = 0; //행에서 0의 개수를 카운트
            for(int j=1; j<arr.length; j++) {
                if(arr[i][j] == 0) cntZero++;
                if(cntZero >= 2) break;
            }
            //0의 개수가 1개라면 순위를 매길 수 있음
            if(cntZero == 1) count++;
        }
        
        return count;
    }
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        headToHead = new int[n+1][n+1]; //경기 결과(전적) 배열 초기화
        
        makeGraph(n, results); //그래프 생성
        
        //선수 번호는 1번부터 이므로 1부터 n까지 탐색
        for(int i=1; i<=n; i++) {
            visited = new boolean[n+1];
            dfs(i, i, visited);
        }
        
        //정답 구하기
        answer = getCountRank(headToHead);
        
        return answer;
    }
}