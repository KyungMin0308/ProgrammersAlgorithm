class Solution {
    
    //유클리드 호제법(최대 공약수)
	//조건: a > b
	private int GCD(int a, int b) {
		while(b > 0) {
			int tmp = a%b;
			a = b;
			b = tmp;
		}
		
		return a;
	}
    
    public int[] solution(int n, int m) {
        int[] answer = new int[2]; //정답 배열
        int gcd = 0; //최대 공약수
        int lcm = 0; //최소 공배수
        
        //최대 공약수 구하기
        if(n > m)
        	gcd = GCD(n, m);
        else
        	gcd = GCD(m, n);
        
        //최소 공배수 구하기
        lcm = (n*m) / gcd;
        
        answer[0] = gcd;
        answer[1] = lcm;
        
        return answer;
    }
}