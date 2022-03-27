class Solution {
    
    //누적합 함수
    public int [][] prefixSum(int [][] arr) {
        //행 방향
        for(int i=0; i<arr.length; i++) {
            for(int j=1; j<arr[0].length; j++) {
                arr[i][j] += arr[i][j-1];
            }
        }
        
        //열 방향
        for(int i=0; i<arr[0].length; i++) {
            for(int j=1; j<arr.length; j++) {
                arr[j][i] += arr[j-1][i];
            }
        }
        
        return arr;
    }
    
    //건물 내구도 합산
    public int [][] calc(int [][] board, int [][] arr) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                board[i][j] += arr[i][j];
            }
        }
        
        return board;
    }
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int [][] damage = new int[board.length][board[0].length]; //내구도 데미지 배열
        
        for(int i=0; i<skill.length; i++) {
            int type = skill[i][0];
            int x1 = skill[i][1];
            int y1 = skill[i][2];
            int x2 = skill[i][3];
            int y2 = skill[i][4];
            int degree = skill[i][5];
            
            //type에 따라 [x1, y1] [x1, y2+1], [x2+1, y1], [x2+1, y2+1] 위치에 degree 합산
            //1. 내구도 하락
            if(type == 1) {
                //[x1, y1], [x2+1, y2+1] = -degree
                //[x1. y2+1], [x2+1, y1] = degree
                damage[x1][y1] += -degree;
                if(x2+1 < damage.length && y2+1 < damage[0].length) damage[x2+1][y2+1] += -degree;
                if(y2+1 < damage[0].length) damage[x1][y2+1] += degree;
                if(x2+1 < damage.length) damage[x2+1][y1] += degree;
            }
            //2. 내구도 상승
            if(type == 2) {
                //[x1, y1], [x2+1, y2+1] = degree
                //[x1. y2+1], [x2+1, y1] = -degree
                damage[x1][y1] += degree;
                if(x2+1 < damage.length && y2+1 < damage[0].length) damage[x2+1][y2+1] += degree;
                if(y2+1 < damage[0].length) damage[x1][y2+1] += -degree;
                if(x2+1 < damage.length) damage[x2+1][y1] += -degree;
            }
        }
        
        damage = prefixSum(damage); //내구도 데미지 배열의 누적합 계산
        
        board = calc(board, damage); //기존 건물 내구도에 데미지 누적합 합산
        
        //파괴되지 않은 건물 개수 구하기
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}