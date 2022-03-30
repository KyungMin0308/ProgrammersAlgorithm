import java.util.*;

class Solution {
    
    private ArrayList<ArrayList<Integer>> graph; //양-늑대 그래프
    private int countSheep = 0; //모을 수 있는 양의 수
    
    //그래프 생성
    public void makeGraph(int n, int [][] edges) {
        
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        //단방향 연결
        for(int i=0; i<edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
        }
    }
    
    //탐색
    public void dfs(int [] info, int v, int sheep, int wolf, ArrayList<Integer> nodes) {
        if(info[v] == 0) sheep++; //양이라면
        if(info[v] == 1) wolf++; //늑대라면
        
        //양이 늑대보다 작거나 같아지면 이동 불가능하므로 리턴
        if(sheep <= wolf) return;
        
        countSheep = Math.max(countSheep, sheep); //결과값 갱신
        
        //방문 가능한 노드 추가
        ArrayList<Integer> nextNodes = new ArrayList<Integer>();
        nextNodes.addAll(nodes); //기존의 방문 가능한 노드 복사
        nextNodes.addAll(graph.get(v)); //현재 노드에서 방문 가능한 노드 추가
        nextNodes.remove(nextNodes.indexOf(v)); //현재 노드는 방문했으므로 방문 가능 노드에서 제거
        
        //방문 가능한 노드 탐색
        for(int i=0; i<nextNodes.size(); i++) {
            int next = nextNodes.get(i);
            dfs(info, next, sheep, wolf, nextNodes);
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        int n = info.length; //노드 개수
        graph = new ArrayList<ArrayList<Integer>>(); //양-늑대 그래프
        ArrayList<Integer> nodes = new ArrayList<Integer>(); //방문 가능한 노드 리스트
        
        //그래프 생성
        makeGraph(n, edges);
        
        nodes.add(0); //최초 방문 가능한 노드는 0번 노드
        
        //양-늑대 정보, 시작 노드, 양의 수, 늑대 수, 방문 가능한 노드
        dfs(info, 0, 0, 0, nodes); //탐색 시작
        
        answer = countSheep;
        
        return answer;
    }
}