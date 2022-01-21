import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
		int x = 0, y = 0;
		boolean [][] visited = new boolean[maps.length][maps[0].length]; // 방문 표시 배열
		int [] dx = {-1, 0, 1, 0}; //x축 이동
		int [] dy = {0, 1, 0, -1}; //y축 이동
		
		//BFS 사용
		Queue<int []> queue = new LinkedList<int []>();
		
		// [0, 0]에서 시작
		answer += maps[x][y];
		queue.offer(new int [] {x, y, answer});
		visited[x][y] = true; // [0, 0] 방문 처리
		
		while(!queue.isEmpty()) {
			int [] cur = queue.poll(); // 현재 정보 가져옴
			x = cur[0]; // 현재 x좌표
			y = cur[1]; // 현재 y좌표
			answer = cur[2]; // 현재 지나간 칸 수
			
			if(x == maps.length-1 && y == maps[0].length-1) { // 도착 지점에 도착했다면 탈출
				break;
			}
			
			// 이동 가능한 경로로 이동하면서 지나간 칸 수 계산
			for(int i=0; i<dx.length; i++) {
				int nx = x + dx[i]; // x좌표 이동
				int ny = y + dy[i]; // y좌표 이동
				
				if(nx < 0 || nx >= maps.length || ny < 0 || ny >= maps[0].length) continue; // map의 범위를 넘어가면 continue
				if(maps[nx][ny] == 0) continue; // 0이면 이동하지 못하므로 continue
				
				// 이동된 좌표로 방문하지 않았다면
				if(!visited[nx][ny]) {
					maps[nx][ny] += maps[x][y]; // 이동한 위치의 값에 현재 좌표 값을 더함
					answer = maps[nx][ny]; // answer값 갱신
					queue.offer(new int [] {nx, ny, answer}); // 큐에 삽입
					visited[nx][ny] = true; // 이동한 좌표 방문 처리
				}
			}
		}
		
		if(!visited[maps.length-1][maps[0].length-1]) return -1; // 도착 지점을 방문하지 못했다면 -1 리턴
		else return answer; // 도착했다면 answer 리턴
    }
}