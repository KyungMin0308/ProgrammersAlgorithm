import java.util.*;

class Solution {
    
    //올바른 괄호인지 확인
    public boolean isOk(String p) {
        if(p.charAt(0) == ')') return false;
        
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i<p.length(); i++) {
            if(stack.isEmpty()) stack.push(p.charAt(i));
            else {
                if(p.charAt(i) == ')') {
                    if(stack.peek() == '(') stack.pop();
                    else stack.push(p.charAt(i));
                }
                else stack.push(p.charAt(i));
            }
        }
        
        if(stack.size() == 0) return true;
        else return false;
    }
    
    //괄호 뒤집기
    public String reverse(String p) {
        String rev = "";
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) == '(') rev += ")";
            else if(p.charAt(i) == ')') rev += "(";
        }
        return rev;
    }
    
    public String solution(String p) {
        String answer = "";
        String u = "";
        String v = "";
        
        //1. 빈문자열이면 빈문자열 반환
        if(p.length() == 0) return "";
        
        if(isOk(p)) answer += p; //올바른 괄호라면 answer에 이어붙임
        else { //올바른 괄호가 아니라면
            //2. 균형잡힌 문자열 u, v로 분리
            int cnt = 0;
            int idx = 0;
            for(int i=0; i<p.length(); i++) {
                if(p.charAt(i) == '(') cnt++;
                else if(p.charAt(i) == ')') cnt--;
                
                if(cnt == 0) {
                    idx = i;
                    break;
                }
            }
            u = p.substring(0, idx+1);
            v = p.substring(idx+1);
            
            //System.out.println("u: " + u);
            //System.out.println("v: " + v);
            
            //3. u가 올바른 괄호라면
            if(isOk(u)) {
                answer += u; //u를 이어붙이고
                answer += solution(v); //3-1. v에 대해 1단계부터 다시 수행 후 이어붙임
            }
            else { //4. u가 올바른 괄호가 아니라면
                StringBuffer sb = new StringBuffer();
                sb.append("("); //4-1.
                sb.append(solution(v)); //4-2.
                sb.append(")"); //4-3.
                
                //4-4.
                StringBuffer sbU = new StringBuffer(u);
                sb.append(reverse(sbU.substring(1, u.length()-1)));
                answer += sb.toString();
            }
        }
        
        return answer;
    }
}