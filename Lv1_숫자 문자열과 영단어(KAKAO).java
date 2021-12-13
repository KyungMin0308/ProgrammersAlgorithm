class Solution {
    public int solution(String s) {
        int answer = 0;
		
        //문자열 숫자 배열
		String [] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		
        //단어를 숫자로 변환
		for(int i=0; i<numbers.length; i++) {
            //s에 numbers[i]가 존재한다면 
			if(s.contains(numbers[i])) {
                //numbers[i]를 해당하는 숫자로 변환
				s = s.replaceAll(numbers[i], Integer.toString(i));
			}
		}
		
		answer = Integer.parseInt(s);
		
        return answer;
    }
}