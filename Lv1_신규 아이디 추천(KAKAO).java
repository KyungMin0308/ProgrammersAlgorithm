class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        
        //new_id의 모든 대문자를 소문자로 치환
        answer = answer.toLowerCase();
        
        //new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 제외 다 제거
        StringBuffer sb = new StringBuffer();
        sb.append(answer);
        for(int i=0; i<sb.length(); i++) {
        	if(sb.charAt(i) >= 'a' && sb.charAt(i) <= 'z')
        		continue;
        	if(sb.charAt(i) >= '0' && sb.charAt(i) <= '9')
        		continue;
        	else if(sb.charAt(i) == '-' || sb.charAt(i) == '_' || sb.charAt(i) == '.')
        		continue;
        	else 
        		sb.deleteCharAt(i--);
        }
        answer = sb.toString();
        sb.setLength(0); //StringBuffer 초기화

        //new_id에서 마침표 두번 연속이면 하나로 치환
        sb.append(answer);
        for(int i=0; i<sb.length()-1; i++) {
        	if(sb.charAt(i) == '.' && sb.charAt(i+1) == '.')
        		sb.deleteCharAt(i--);        	
        }
        answer = sb.toString();
        
        //new_id에서 마침표가 처음이나 끝에 있으면 제거
        if(answer.charAt(0) == '.')
        	answer = answer.substring(1);
        if(!answer.isEmpty())
        	if(answer.charAt(answer.length()-1) == '.')
        		answer = answer.substring(0, answer.length()-1);
        
        //new_id가 빈 문자열이라면 a대입
        if(answer.isEmpty())
        	answer += 'a';
        
        //new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거
        //만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거
        if(answer.length() >= 16) {
        	answer = answer.substring(0, 15);
        	if(answer.charAt(answer.length()-1) == '.')
        		answer = answer.substring(0, answer.length()-1);	
        }
        
        //new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙임
        if(answer.length() <= 2) {
        	while(answer.length() != 3) {
        		answer += answer.charAt(answer.length()-1);
        	}
        }
        
        return answer;
    }
}