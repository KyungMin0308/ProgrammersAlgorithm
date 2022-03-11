import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); //그래프
        boolean [] visited = new boolean[n+1]; //방문 처리
        int [] distance = new int[n+1]; //각 노드별 1번 노드까지 거리
        
        distance[1] = 0; //1번 노드와의 거리는 0
        
        //그래프 초기화
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        //그래프 생성
        for(int i=0; i<edge.length; i++) {
            //양방향 연결
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }
        
        //BFS
        Queue<int []> queue = new LinkedList<int []>(); //BFS 수행 큐
        queue.offer(new int[] {1, distance[1]}); //현재 노드 번호, 해당 노드까지와의 거리 저장
        visited[1] = true;
        
        while(!queue.isEmpty()) {
            int [] cur = queue.poll(); //현재 노드 정보 가져옴
            int node = cur[0]; //현재 노드 번호
            
            //현재 노드와 연결된 노드 방문
            for(int i=0; i<graph.get(node).size(); i++) {
                int next = graph.get(node).get(i); //다음 노드 번호
                //다음 노드를 아직 방문하지 않았다면
                if(!visited[next]) {
                    //다음 노드와의 거리는 현재 노드와의 거리 + 1
                    distance[next] = distance[node] + 1;
                    //큐에 삽입 후 방문 처리
                    queue.offer(new int[] {next, distance[next]});
                    visited[next] = true;
                }
            }
        }
        
        //1번 노드와 가장 멀리 떨어진 노드를 구하기 위해 배열을 리스트로 복사
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=1; i<distance.length; i++) {
            res.add(distance[i]);
        }
        
        //1번 노드와 가장 멀리 떨어진 노드와의 거리
        int maxDistance = Collections.max(res);
        
        //1번 노드와 가장 멀리 떨어진 노드 개수
        for(int i=0; i<res.size(); i++) {
            if(res.get(i) == maxDistance) answer++;
        }
        
        return answer;
    }
}