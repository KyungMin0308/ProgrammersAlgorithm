class Solution {

	//유클리드 호제법(최대 공약수)
	//조건: a > b
	private int GCD(int a, int b) {
		while (b > 0) {
			int tmp = a % b;
			a = b;
			b = tmp;
		}

		return a;
	}

	public int solution(int[] arr) {
		int answer = 0; //정답 저장(최소 공배수)
		int gcd = 0; //최대 공약수

		//배열의 첫번째 원소 저장
		int num = arr[0];

		//N개의 최소 공배수 구하기
		//두 원소의 최소 공배수를 구하고 그 최소 공배수와 다음 원소의 최소 공배수를 구한다.
		for (int i=1; i<arr.length; i++) {
			//최대 공약수 구하기
			if (num > arr[i])
				gcd = GCD(num, arr[i]);
			else
				gcd = GCD(arr[i], num);

			//최소 공배수 구하기
			answer = (num * arr[i]) / gcd;
			
			//num에 최소 공배수 저장
			num = answer;
		}

		return answer;
	}
}