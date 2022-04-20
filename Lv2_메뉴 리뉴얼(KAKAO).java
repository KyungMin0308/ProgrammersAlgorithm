import java.util.*;
import java.util.Map.*;

class Solution {
    
    //생성 가능한 모든 메뉴 조합 저장 해시맵
    private HashMap<String, Integer> menuMap = new HashMap<String, Integer>();
    
    //메뉴 조합 생성
    public void makeMenu(String [] od, int idx, int depth, int len, boolean [] visited) {
        if(depth == len) { //메뉴 조합 길이와 같다면
            String menu = "";
            for(int i=0; i<visited.length; i++) { //메뉴 생성
                if(visited[i] == true) menu += od[i];
            }
            
            //해시멥에 저장
            if(menuMap.containsKey(menu)) menuMap.put(menu, menuMap.get(menu) + 1);
            else menuMap.put(menu, 1);
        }
        
        //조합
        for(int i=idx; i<od.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                makeMenu(od, i+1, depth+1, len, visited);
                visited[i] = false;
            }
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        //메뉴 조합 생성
        for(int i=0; i<orders.length; i++) {
            for(int j=0; j<course.length; j++) {
                if(orders[i].length() >= course[j]) {
                    String [] od = orders[i].split(""); //단품 메뉴 배열 생성
                    Arrays.sort(od); //오름차순 정렬
                    boolean [] visited = new boolean[od.length];
                    makeMenu(od, 0, 0, course[j], visited);
                }
            }
        }
        
        //생성된 메뉴 조합 정렬
        List<Entry<String, Integer>> entrys = new ArrayList<Entry<String, Integer>>(menuMap.entrySet());
        Collections.sort(entrys, new Comparator<Entry<String, Integer>>() {
            //주문 횟수 기준으로 내림차순 정렬
            //메뉴 길이 기준 오름차순 정렬
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                if(e1.getKey().length() == e2.getKey().length()) return e2.getValue() - e1.getValue();
                else return e1.getKey().length() - e2.getKey().length();
            }
        });
        
        //for(Entry<String, Integer> en: entrys) { System.out.println(en.getKey() + ": " + en.getValue()); }
        
        ArrayList<String> res = new ArrayList<String>();
        int len = 0; //현재 메뉴 구성 길이
        int cnt = 0; //현재 메뉴 구성 주문 횟수
        
        for(Entry<String, Integer> en: entrys) {
            if(en.getValue() < 2) continue; //주문 횟수가 2회 미만이면 추가하지 않음
            
            //Entry의 첫번째 원소인 경우
            if(len == 0 && cnt == 0) {
                res.add(en.getKey()); //리스트에 저장
                len = en.getKey().length(); //메뉴 길이 저장
                cnt = en.getValue(); //주문 횟수 저장
            }
            //두번째 원소부터
            else {
                //메뉴 길이가 길어지면 다음 조합으로 넘어간 것
                if(len < en.getKey().length()) {
                    res.add(en.getKey()); //리스트에 저장
                    len = en.getKey().length(); //메뉴 길이 저장
                    cnt = en.getValue(); //주문 횟수 저장
                }
                //메뉴 길이가 같은 경우
                else {
                    //주문 횟수가 같은 경우에만 저장
                    if(cnt == en.getValue()) res.add(en.getKey());
                    else continue;
                }
            }
        }
        
        Collections.sort(res); //오름차순 정렬
        
        //정답 생성
        answer = new String[res.size()];
        for(int i=0; i<res.size(); i++) {
            answer[i] = res.get(i);
        }
        
        return answer;
    }
}