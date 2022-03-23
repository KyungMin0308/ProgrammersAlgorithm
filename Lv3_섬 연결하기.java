import java.util.*;

class Solution {
    
    //간선 정보 클래스
    class Edge implements Comparable<Edge> {
        private int cost; //nodeA <-> nodeB의 비용
        private int nodeA;
        private int nodeB;
        
        public Edge(int nodeA, int nodeB, int cost) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
        }
        
        public int getCost() { return this.cost; }
        public int getNodeA() { return this.nodeA; }
        public int getNodeB() { return this.nodeB; }
        
        //비용이 적은 순으로 정렬
        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }
    
    private int [] parent = new int[100]; //부모 노드 저장
    private ArrayList<Edge> edges = new ArrayList<Edge>(); //간선 정보 저장
    
    //간선 정보를 기준으로 리스트 생성
    public void makeEdges(int [][] costs, int n) {
        //부모 테이블상에서 부모를 자기 자신으로 초기화
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        //간선 정보를 리스트에 저장
        for(int i=0; i<costs.length; i++) {
            int nodeA = costs[i][0];
            int nodeB = costs[i][1];
            int cost = costs[i][2];
            
            edges.add(new Edge(nodeA, nodeB, cost));
        }
        
        //간선 기준 오름차순 정렬
        Collections.sort(edges);
    }
    
    //특정 노드가 속한 집합 찾음(부모 노드를 찾음)
    public int findParent(int x) {
        //루트 노드가 아니라면 루트 노드를 찾을 때까지 재귀적으로 호출
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }
    
    //두 원소가 속한 집합 합침
    public void unionParent(int nodeA, int nodeB) {
        nodeA = findParent(nodeA);
        nodeB = findParent(nodeB);
        
        if(nodeA < nodeB) parent[nodeB] = nodeA;
        else parent[nodeA] = nodeB;
    }
    
    //최소 비용 구하기(크루스칼 알고리즘)
    public int getRouteMinCost(ArrayList<Edge> edges) {
        int result = 0;
        
        for(int i=0; i<edges.size(); i++) {
            int nodeA = edges.get(i).getNodeA();
            int nodeB = edges.get(i).getNodeB();
            int cost = edges.get(i).getCost();
            
            //사이클이 생성되지 않는 경우에만 연결(부모가 같으면 사이클이 발생)
            if(findParent(nodeA) != findParent(nodeB)) {
                unionParent(nodeA, nodeB); //두 원소를 같은 집합으로 합침
                result += cost; //비용 계산
            }
        }
        
        return result;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        makeEdges(costs, n); //리스트 생성
        
        answer = getRouteMinCost(edges); //모든 노드를 연결하는 경로의 최소 비용 구하기
        
        return answer;
    }
}