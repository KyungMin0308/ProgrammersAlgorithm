import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int [][] matrix = new int[rows][columns];
        ArrayList<Integer> lst = new ArrayList<Integer>(); //테두리 원소를 저장할 리스트
        
        //2차원 배열 초기화
        int num = 1;
        for(int i=0; i<rows; i++) {
        	for(int j=0; j<columns; j++) {
        		matrix[i][j] = num;
        		num++;
        	}
        }
        
        for(int i=0; i<queries.length; i++) {
        	//리스트 크기: 지정된 범위에서 가운데 부분 제외한 크기 = 테두리 부분
        	int size = ((queries[i][2]-queries[i][0]+1) * (queries[i][3]-queries[i][1]+1))
                - ((queries[i][3]-queries[i][1]-1) * (queries[i][2]-queries[i][0]-1));
        	
        	//테두리 원소들 리스트에 추가
        	int x = queries[i][0]-1; //x좌표 시작 위치
    		int y = queries[i][1]-1; //y좌표 시작 위치
        	while(lst.size() != size) {
        		//가운데 부분이면 아무것도 하지 않음
        		if(x > queries[i][0]-1 && x < queries[i][2]-1) {
    				if(y > queries[i][1]-1 && y < queries[i][3]-1) {
    					continue;
    				}
    			}
        		
        		//리스트에 추가
        		lst.add(matrix[x][y]);
        		
        		//좌표 이동
        		if(x == queries[i][0]-1 && y < queries[i][3]-1) y++;
        		else if(x < queries[i][2]-1 && y == queries[i][3]-1) x++;
        		else if(x == queries[i][2]-1 && y > queries[i][1]-1 && y <= queries[i][3]-1) y--;
        		else if(x >= queries[i][0]-1 && y == queries[i][1]-1) x--;
        	}
        	
        	//리스트 회전
        	for(int j=lst.size()-1; j>0; j--) {
        		int tmp = lst.get(j);
        		lst.set(j, lst.get(j-1));
        		lst.set(j-1, tmp);
        	}
        	
        	//회전한 원소로 matrix 값 갱신
        	x = queries[i][0]-1; //x좌표 시작 위치
    		y = queries[i][1]-1; //y좌표 시작 위치
    		int idx = 0; //리스트 인덱스
        	while(size != 0) {
        		//가운데 부분이면 아무것도 하지 않음
        		if(x > queries[i][0]-1 && x < queries[i][2]-1) {
    				if(y > queries[i][1]-1 && y < queries[i][3]-1) {
    					continue;
    				}
    			}
        		
        		matrix[x][y] = lst.get(idx);
        		
        		//좌표 이동
        		if(x == queries[i][0]-1 && y < queries[i][3]-1) y++;
        		else if(x < queries[i][2]-1 && y == queries[i][3]-1) x++;
        		else if(x == queries[i][2]-1 && y > queries[i][1]-1 && y <= queries[i][3]-1) y--;
        		else if(x >= queries[i][0]-1 && y == queries[i][1]-1) x--;
        		
        		size--;
        		idx++;
        	}
        	
			answer[i] = Collections.min(lst); //테두리 부분에서 가장 작은값 선택
        	lst.clear(); //리스트 비우기
        }
        
        return answer;
    }
}