import java.util.*;

class Solution {
    
    private ArrayList<Integer> res = new ArrayList<Integer>(); //변환 횟수 저장 리스트
    private boolean [] visited; //방문 배열
    
    //두 단어의 차이가 1개인지 판별
    public boolean isOneDistance(String word1, String word2) {
        int dis = 0;
        for(int i=0; i<word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) dis++;
        }
        if(dis == 1) return true;
        else return false;
    }
    
    //변환 과정 DFS
    public void dfs(String begin, String target, int round, int depth, String [] words, boolean [] visited) {
        if(depth == words.length) { //words 배열 끝까지 탐색을 끝낸 경우
            return; //리턴
        }
        if(begin.equals(target)) { //target으로 변환되었다면
            res.add(round); //변환 횟수를 리스트에 추가
            return; //리턴
        }
        
        //words 배열을 돌면서
        for(int i=0; i<words.length; i++) {
            //begin과 words 배열 내 단어의 차이가 1개라면
            if(isOneDistance(begin, words[i])) {
                //해당 word부터 다시 DFS
                if(!visited[i]) {
                    visited[i] = true; //현재 위치 방문 처리
                    dfs(words[i], target, round+1, depth+1, words, visited);
                    visited[i] = false; //탐색 후 다시 미방문으로 변경
                }
            }
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        
        dfs(begin, target, 0, 0, words, visited);
        
        //res의 길이가 0이 아니라면 변환이 가능한 경우로 최소값을 구함
        if(res.size() != 0) answer = Collections.min(res);
        else answer = 0;
        
        return answer;
    }
}