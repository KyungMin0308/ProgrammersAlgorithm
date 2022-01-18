class Solution {
    
    private int[] answer = new int[2]; // 정답 배열

	// 영역이 모두 0인지 체크
	private boolean allZero(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != 0)
					return false;
			}
		}
		return true;
	}

	// 영역이 모두 1인지 체크
	private boolean allOne(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != 1)
					return false;
			}
		}
		return true;
	}

	public int[] solution(int[][] arr) {
		
		if(allZero(arr)) answer[0]++; //영역이 모두 0이면 카운트
		else if(allOne(arr)) answer[1]++; //영역이 모두 1이면 카운트
		else {
			//그게 아니라면 2차원 배열을 4등분한 후 다시 solution 호출
			int [][] arr1 = new int[arr.length/2][arr.length/2];
			int [][] arr2 = new int[arr.length/2][arr.length/2];
			int [][] arr3 = new int[arr.length/2][arr.length/2];
			int [][] arr4 = new int[arr.length/2][arr.length/2];
			
			//2차원 배열 4등분
			int x = 0, y = 0;
			for(int i=0; i<arr.length/2; i++) {
				for(int j=0; j<arr[i].length/2; j++) {
					arr1[x][y] = arr[i][j];
					y++;
				}
				x++;
				y = 0;
			}
			
			x = 0; y = 0;
			for(int i=0; i<arr.length/2; i++) {
				for(int j=arr[i].length/2; j<arr[i].length; j++) {
					arr2[x][y] = arr[i][j];
					y++;
				}
				x++;
				y = 0;
			}
			
			x = 0; y = 0;
			for(int i=arr.length/2; i<arr.length; i++) {
				for(int j=0; j<arr[i].length/2; j++) {
					arr3[x][y] = arr[i][j];
					y++;
				}
				x++;
				y = 0;
			}
			
			x = 0; y = 0;
			for(int i=arr.length/2; i<arr.length; i++) {
				for(int j=arr[i].length/2; j<arr[i].length; j++) {
					arr4[x][y] = arr[i][j];
					y++;
				}
				x++;
				y = 0;
			}
			
			//분할된 배열로 solution 다시 호출
			solution(arr1);
			solution(arr2);
			solution(arr3);
			solution(arr4);
		}

		return answer;
	}
}