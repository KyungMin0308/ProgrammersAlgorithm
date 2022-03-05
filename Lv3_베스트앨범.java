import java.util.*;
import java.util.Map.*;

class Solution {
    
    //노래 클래스
    class Song implements Comparable<Song> {
        private int id; //노래 고유 번호
        private int plays; //재생 횟수
        
        public Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
        
        //재생 횟수 기준 내림차순 정렬
        public int compareTo(Song s1) {
            return s1.plays - this.plays;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        //장르별 노래 정보(id, plays) 저장 해시맵
        HashMap<String, ArrayList<Song>> hm = new HashMap<String, ArrayList<Song>>();
        //장르별 총 재생 횟수 저장 해시맵
        HashMap<String, Integer> playsHm = new HashMap<String, Integer>();
        
        //해시맵 생성
        //1. hm에 장르별 노래 정보 저장
        for(int i=0; i<genres.length; i++) {
            String key = genres[i]; //장르
            int id = i; //노래 고유 번호
            int play = plays[i]; //노래 재생 횟수
            
            //해시맵에 장르가 없다면
            if(!hm.containsKey(key)) {
                ArrayList<Song> lst = new ArrayList<Song>(); //리스트 생성
                lst.add(new Song(id, play)); //리스트에 노래 정보 삽입
                hm.put(key, lst); //해시맵에 삽입
            }
            else {
                //해시맵에 장르가 있다면
                ArrayList<Song> lst = hm.get(key); //리스트를 가져옴
                lst.add(new Song(id, play)); //리스트에 노래 정보 삽입
                hm.put(key, lst); //해시맵에 삽입
            }
        }
        
        //2. playsHm에 장르별 총 재생 횟수 저장
        for(int i=0; i<genres.length; i++) {
        	String key = genres[i];
        	int value = plays[i];
        	
        	if(!playsHm.containsKey(key)) {
        		playsHm.put(key, value);
        	}
        	else {
        		playsHm.put(key, value + playsHm.get(key));
        	}
        }
        
        //정렬 수행
        //1. hm의 노래 정보 리스트를 재생 횟수 기준 내림차순으로 정렬
        for(String keys: hm.keySet()) {
            List<Song> lst = hm.get(keys);
            Collections.sort(lst);
        }
        
        //2. playsHm의 장르를 재생 횟수 기준 내림차순으로 정렬
        List<Entry<String, Integer>> entrys = new ArrayList<Entry<String, Integer>>(playsHm.entrySet());
        Collections.sort(entrys, new Comparator<Entry<String, Integer>>() {
            //재생 횟수 기준 내림차순으로 정렬
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        });
        
        //정답 배열 생성
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0; i<entrys.size(); i++) {
        	List<Song> lst = hm.get(entrys.get(i).getKey());
        	if(lst.size() > 1) {
        		for(int j=0; j<2; j++) {
            		res.add(lst.get(j).id);
            	}
        	}
        	else {
        		res.add(lst.get(0).id);
        	}
        }
        
        //answer 배열로 복사
        answer = new int[res.size()];
        for(int i=0; i<answer.length; i++) {
        	answer[i] = res.get(i);
        }
        
        return answer;
    }
}