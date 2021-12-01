import java.util.*;

class Solution {
    
    //괄호 체크
    private int check(String s) {
        Stack<Character> stack = new Stack<Character>();
        
        for(int i=0; i<s.length(); i++) {
        	if(stack.isEmpty())
        		stack.push(s.charAt(i));
        	else {
        		char c = stack.peek();
        		if(c == '[' && s.charAt(i) == ']') {
        			stack.pop();
        		}
        		else if(c == '{' && s.charAt(i) == '}') {
        			stack.pop();
        		}
        		else if(c == '(' && s.charAt(i) == ')') {
        			stack.pop();
        		}
        		else {
        			stack.push(s.charAt(i));
        		}
        	}
        }
        
        //stack의 size가 0이면 정상적인 괄호
        return stack.size();
    }
    
    public int solution(String s) {
        int answer = 0;
        StringBuffer sb = new StringBuffer(s);
        
        for(int i=0; i<s.length(); i++) {
            if(check(sb.toString()) == 0) { //정상인 괄호이므로 answer 1 증가
            	answer++;
            }
            
            //맨앞의 괄호를 맨뒤로 이어붙이기
            sb.append(sb.charAt(0));
            sb.replace(0, 1, "");
        }
        
        return answer;
    }
}