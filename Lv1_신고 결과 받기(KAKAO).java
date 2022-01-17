import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

		//신고횟수 저장 배열
		int[] count = new int[id_list.length];
		
		//이용자별 신고한 사람 저장 2차원 리스트
		ArrayList<ArrayList<String>> lst = new ArrayList<ArrayList<String>>();

		//리스트 초기화
		for (int i = 0; i < id_list.length; i++)
			lst.add(new ArrayList<String>());
		
		//이용자별 신고한 사람 및 신고 당한 횟수 카운트
		for(int i=0; i<report.length; i++) {
			int keyIdx = 0; //신고한 사람
			int valueIdx = 0; //신고 당한 사람
			
			String [] str = report[i].split(" ");
			String key = str[0]; //신고한 사람
			String value = str[1]; //신고 당한 사람
			
			for(int j=0; j<id_list.length; j++) {
				if(id_list[j].equals(key)) {
					keyIdx = j;
				}
				if(id_list[j].equals(value)) {
					valueIdx = j;
				}
			}
	
			//동일한 사람을 신고하였는지 확인하면서 카운트
			if(!lst.get(keyIdx).contains(value)) {
				lst.get(keyIdx).add(value);
				count[valueIdx]++; //신고 당한 횟수 카운트
			}
		}
		
		//이용자별로 보내야 하는 메시지 수 카운트
		for(int i=0; i<count.length; i++) {
			if(count[i] >= k) {
				for(int j=0; j<lst.size(); j++) {
					if(lst.get(j).contains(id_list[i])) {
						answer[j]++;
					}
				}
			}
		}

		return answer;
    }
}