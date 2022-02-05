import java.util.*;

class Solution {
    
    private static Map<String, ArrayList<Integer>> applicant; //지원자 정보로 생성 가능한 모든 key와 점수의 쌍을 저장
	private static ArrayList<Integer> scores; //지원자 점수를 저장
	
	//지원자 정보로 만들 수 있는 모든 key와 score의 쌍 생성
	private void makeKeys(String [] info, String key, int depth, int score) {
		if(depth == 4) {
			if(applicant.containsKey(key)) {
				applicant.get(key).add(score);
			}
			else {
				scores = new ArrayList<Integer>();
				scores.add(score);
				applicant.put(key, scores);
			}
			return;
		}
		
		makeKeys(info, key+"-", depth+1, score);
		makeKeys(info, key+info[depth], depth+1, score);
	}
	
	//순위 검색 함수(이진 탐색)
	private int searchRank(ArrayList<Integer> lst, int score, int low, int high) {
		int mid = 0;
		
		while(low <= high) {
			mid = (low+high) / 2;
			
			if(lst.get(mid) < score) {
				low = mid+1;
			}
			else {
				high = mid-1;
			}
		}
		
		return lst.size()-low;
	}

	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		applicant = new HashMap<String, ArrayList<Integer>>();
		
        //지원자 정보로 생성 가능한 모든 Key와 점수의 쌍 생성 및 HashMap에 저장
		for(int i=0; i<info.length; i++) {
			String [] in = info[i].split(" ");
			int score = Integer.parseInt(in[4]);
			
			makeKeys(in, "", 0, score);
		}
		
		//for(String keys: applicant.keySet()) { System.out.println(keys + " " + applicant.get(keys)); }
		
		//점수를 오름차순으로 정렬
		for(String key: applicant.keySet()) {
			ArrayList<Integer> lst = applicant.get(key);
			Collections.sort(lst);
		}
		
		//for(String keys: applicant.keySet()) { System.out.println(keys + " " + applicant.get(keys)); }
		
		for(int i=0; i<query.length; i++) {
			query[i] = query[i].replaceAll(" and ", "");
			String [] key = query[i].split(" ");
			int score = Integer.parseInt(key[1]);
			
			if(!applicant.containsKey(key[0])) answer[i] = 0; //HashMap에 key[0]가 없다면 0
			else answer[i] = searchRank(applicant.get(key[0]), score, 0, applicant.get(key[0]).size()-1);
		}
		
		return answer;
	}
}