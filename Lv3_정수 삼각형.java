class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int st = triangle.length-2; //아래에서 두번째부터 시작
        
        //아래에서 두번째부터 위로 올라가면서
        for(int i=st; i>=0; i--) {
            for(int j=0; j<triangle[i+1].length-1; j++) {
                //현재 배열 값 기준으로
                //현재 배열 값에 바로 아래 배열의 왼쪽과 오른쪽 값을 각각 더한 값 중 더 큰값으로 현재 배열 값을 갱신
                triangle[i][j] = Math.max(triangle[i][j]+triangle[i+1][j], triangle[i][j]+triangle[i+1][j+1]);
            }
        }
        
        //거쳐간 숫자의 최대값은 배열의 가장 맨위
        answer = triangle[0][0];
        
        return answer;
    }
}