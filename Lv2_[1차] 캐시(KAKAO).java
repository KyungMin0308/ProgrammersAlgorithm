import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
		int hit = 1; //cache hit
		int miss = 5; //cache miss
		
		//소문자로 변환
		for(int i=0; i<cities.length; i++)
			cities[i] = cities[i].toLowerCase();
		
		//캐시 크기가 0이면 모두 cache miss
		if(cacheSize == 0) {
			answer = cities.length * miss;
		}
		else {
			ArrayList<String> lruCache = new ArrayList<String>(); //캐시 리스트
			
			//LRU 방식
			for(int i=0; i<cities.length; i++) {
				if(lruCache.size() < cacheSize) { //캐시에 저장된 데이터가 캐시 크기보다 작다면
					if(!lruCache.contains(cities[i])) {
						answer += miss;
						lruCache.add(cities[i]);
					}
					else { //queue.contains(cities[i])
						answer += hit;
						int idx = lruCache.indexOf(cities[i]);
						lruCache.remove(idx);
						lruCache.add(cities[i]); //가장 최근에 사용된 것을 맨뒤에 추가
					}
				}
				else if(lruCache.size() == cacheSize) { //캐시에 저장된 데이터가 캐시 크기와 같다면
					if(!lruCache.contains(cities[i])) {
						answer += miss;
						lruCache.remove(0); //가장 오랫동안 사용되지 않은 것 제거
						lruCache.add(cities[i]);
					}
					else { //queue.contains(cities[i])
						answer += hit;
						int idx = lruCache.indexOf(cities[i]);
						lruCache.remove(idx);
						lruCache.add(cities[i]); //가장 최근에 사용된 것을 맨뒤에 추가
					}
				}
			}
		}
		
		return answer;
    }
}