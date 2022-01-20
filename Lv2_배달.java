class Solution {
    
    //플로이드 와샬 알고리즘
	private void floyd(int [][] graph) {
		for(int n=1; n<graph.length; n++) { //거쳐가는 정점
			for(int x=1; x<graph.length; x++) {
				for(int y=1; y<graph.length; y++) {
					graph[x][y] = Math.min(graph[x][n]+graph[n][y], graph[x][y]);
				}
			}
		}
	}
	
	public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int [][] graph = new int[N+1][N+1]; //0번 인덱스는 사용하지 않음
        
        //그래프 초기화
        for(int i=1; i<graph.length; i++) {
        	for(int j=1; j<graph[i].length; j++) {
        		if(i == j) graph[i][j] = 0;
        		else graph[i][j] = 100000000;
        	}
        }
        
        //그래프 생성
        for(int i=0; i<road.length; i++) {
        	int x = road[i][0];
        	int y = road[i][1];
        	int cost = road[i][2]; //배달 시간
        	
        	//새로운 cost와 현재 값 중 작은 값으로 갱신
        	graph[x][y] = Math.min(cost, graph[x][y]);
        	graph[y][x] = Math.min(cost, graph[y][x]);
        }

        //플로이드 와샬 알고리즘 사용
        floyd(graph);
        
        //1번 마을에서 배달할 수 있는 마을 카운트
        for(int i=1; i<graph.length; i++) {
        	if(graph[1][i] <= K) {
        		answer++;
        	}
        }
        
        return answer;
    }
}