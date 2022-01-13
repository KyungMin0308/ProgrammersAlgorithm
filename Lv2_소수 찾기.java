import java.util.*;

class Solution {
    //numbers로 생성가능한 모든 수 저장(중복 제거)
	private static Set<Integer> numLst = new HashSet<Integer>();
	
	//순열을 이용하여 생성 가능한 모든 수 생성
	private static void permutation(int n, int r, LinkedList<Integer> perArr, boolean [] perCheck, String number) {
		if(perArr.size() == r) {
			String num = "";
			for(int i: perArr) {
				//생성된 순열을 이용하여 숫자 생성
				num += String.valueOf(number.charAt(i));
			}
			//Set에 저장
			numLst.add(Integer.parseInt(num));
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!perCheck[i]) {
				perArr.add(i);
				perCheck[i] = true;
				permutation(n, r, perArr, perCheck, number);
				perCheck[i] = false;
				perArr.removeLast();
			}
		}
	}
	
    public static int solution(String numbers) {
        int answer = 0;
        int n = numbers.length();
        
        //주어진 numbers로 만들 수 있는 가장 큰 수가 소수를 구하는 범위
        String [] str = numbers.split("");
		Arrays.sort(str, Collections.reverseOrder());
		
		String max = "";
		for(int i=0; i<str.length; i++)
			max += str[i];
		
        //소수 구하기, 에라토스테네스의 체
		int num = Integer.parseInt(max);
		boolean [] isPrime = new boolean[num+1];
		Arrays.fill(isPrime, true);
		
		isPrime[0] = isPrime[1] = false; //0과 1은 소수가 아님
		for(int i=2; i*i<=num; i++) {
			if(isPrime[i]) {
				for(int j=i*i; j<=num; j+=i) {
					isPrime[j] = false;
				}
			}
		}
        
		//주어진 numbers로 만들 수 있는 모든 수 생성
        for(int i=1; i<=numbers.length(); i++) {
        	LinkedList<Integer> lst = new LinkedList<Integer>();
            boolean [] perCheck = new boolean[n];
            
        	permutation(n, i, lst, perCheck, numbers);
        }
        
        //생성된 숫자 중 소수 판별
        for(int i: numLst) {
        	if(isPrime[i])
        		answer++;
        }
        
        return answer;
    }
}