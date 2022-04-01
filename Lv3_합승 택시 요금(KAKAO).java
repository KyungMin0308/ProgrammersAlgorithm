class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int [][] costs = new int[n+1][n+1]; //비용을 저장하는 2차원 배열(0번 인덱스 사용X)
        
        //배열 초기화
        for(int i=0; i<costs.length; i++) {
            for(int j=0; j<costs.length; j++) {
                costs[i][j] = 100000000; //요금의 최대값보다 큰값으로 초기화
            }
        }
        
        for(int i=0; i<fares.length; i++) {
            int st = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            
            costs[st][end] = cost;
            costs[end][st] = cost;
        }
        
        //플로이드 와샬
        for(int k=1; k<costs.length; k++) {
            for(int st=1; st<costs.length; st++) {
                for(int end=1; end<costs.length; end++) {
                    if(st == end) continue;
                    costs[st][end] = Math.min(costs[st][end], costs[st][k]+costs[k][end]);
                }
            }
        }
        
        //합승하지 않고 따로 가는 경우
        answer = costs[s][a] + costs[s][b];
        
        //합승하여 하차 구간을 결정하고 가는 경우
        //costs 배열의 s번째 행을 기준으로 탐색
        for(int i=1; i<costs.length; i++) {
            if(i == s) continue; //시작 지점이랑 하차 지점이 같으면 continue
            
            //하차 지점이 각 도착 지점과 같은 경우
            //하차 지점에서 해당 도착 지점까지 가는 비용은 고려하지 않아도 됨
            if(i == a) answer = Math.min(answer, costs[s][i] + costs[i][b]); //하차 지점이 a와 같음
            else if(i == b) answer = Math.min(answer, costs[s][i] + costs[i][a]); //하차 지점이 b와 같음
            else answer = Math.min(answer, costs[s][i] + (costs[i][a] + costs[i][b])); //그 외의 경우
        }
        
        return answer;
    }
}