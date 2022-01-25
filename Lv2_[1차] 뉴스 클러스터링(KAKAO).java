import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> lst1 = new ArrayList<String>();
        ArrayList<String> lst2 = new ArrayList<String>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for(int i=0; i<str1.length()-1; i++) {
            String key = str1.substring(i, i+2);
            
            //숫자, 특수문자, 공백이 들어가면 제외
            if(key.contains(" ")) continue;
            if(key.charAt(0) < 'a' || key.charAt(0) > 'z') continue;
            if(key.charAt(1) < 'a' || key.charAt(1) > 'z') continue;
            if(key.charAt(0) >= '0' && key.charAt(0) <= '9') continue;
            if(key.charAt(1) >= '0' && key.charAt(1) <= '9') continue;
            
            lst1.add(key);
        }
        
        for(int i=0; i<str2.length()-1; i++) {
            String key = str2.substring(i, i+2);
            
            //숫자, 특수문자, 공백이 들어가면 제외
            if(key.contains(" ")) continue;
            if(key.charAt(0) < 'a' || key.charAt(0) > 'z') continue;
            if(key.charAt(1) < 'a' || key.charAt(1) > 'z') continue;
            if(key.charAt(0) >= '0' && key.charAt(0) <= '9') continue;
            if(key.charAt(1) >= '0' && key.charAt(1) <= '9') continue;
            
            lst2.add(key);
        }
        
		/*
		 * for(String s: lst1) System.out.print(s + " "); System.out.println();
		 * for(String s: lst2) System.out.print(s + " ");
		 */
        
        int union = lst1.size() + lst2.size(); //합집합: 전체 집합의 합에서 교집합 빼기
        int inter = 0; //교집합
        
        //교집합 계산
        for(String s: lst1) {
        	if(lst2.contains(s)) {
                inter++;
                //원소간 중복이 허용되기 때문에 이미 교집합으로 체크된 원소가 중복 체크되는 것을 방지
                lst1.set(lst1.indexOf(s), "**");
                lst2.set(lst2.indexOf(s), "**");
            }
        }
        
        //합집합 계산
        union -= inter;
        
        //자카드 유사도
        double jaccard = 0.0;
        
        if(union == 0 && inter == 0) jaccard = 1.0 * 65536;
        else jaccard = (double) inter / union * 65536;
        
        answer = (int) jaccard;
        
        return answer;
    }
}