class Solution {
	
	//정답 배열 크기 구하기
	private int getSize(int n) {
        int size = 0;
        for(int i=1; i<=n; i++) {
            size += i;
        }
        return size;
    }
    
    public int[] solution(int n) {
        int[] answer = new int[getSize(n)];
        int [][] map = new int[n][n]; //삼각 달팽이 저장 2차원 배열
        int [] dir = {0, 1, 2}; //방향(0:아래, 1:오른쪽, 2:대각선)
        int num = 1; //숫자
        int x = -1; //2차원 배열 x좌표
        int y = 0; //2차원 배열 y좌표
        int count = n; //숫자를 찍는 횟수
        int dirIdx = 0; //방향 전환용 인덱스 값
        
        //num이 정답배열의 크기와 같아질 때까지 반복
        while(num <= answer.length) {
        	//숫자를 찍는 횟수가 규칙적으로 줄어듬
        	//ex.4->3->2->1
        	for(int i=0; i<count; i++) {
        		//아래 방향
        		if(dir[dirIdx%3] == 0) {
        			x++;
        		}
        		//오른쪽 방향
        		if(dir[dirIdx%3] == 1) {
        			y++;
        		}
        		//대각선 방향
        		if(dir[dirIdx%3] == 2) {
        			x--; y--;
        		}
        		
        		map[x][y] = num; //x, y위치에 num 저장
        		num++; //num 1 증가
        	}
        	
        	dirIdx++; //방향 전환
        	count--; //숫자 찍는 횟수 1 감소 
        }

        //정답 배열 생성
        int idx = 0;
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                if(map[i][j] == 0) continue;
                answer[idx++] = map[i][j];
            }
        }
        
        return answer;
    }
}
