import java.util.*;

class Solution {

	// 파일명 클래스
	class File implements Comparable<File> {
		private String head;
		private String num;
		private String tail;

		public File(String head, String num, String tail) {
			this.head = head;
			this.num = num;
			this.tail = tail;
		}

		// 파일명 생성
		public String getFilename() {
			return this.head + this.num + this.tail;
		}

		// 정렬 기준 재설정
		@Override
		public int compareTo(File f1) {
			// head 부분이 대소문자 관계없이 동일하다면
			if(this.head.toUpperCase().compareTo(f1.head.toUpperCase()) == 0) {
				return Integer.parseInt(this.num) - Integer.parseInt(f1.num); // 숫자로 오름차순 정렬
			} 
			else {
				// head 부분 사전순 정렬
				return this.head.toUpperCase().compareTo(f1.head.toUpperCase());
			}
		}
	}

	public String[] solution(String[] files) {
		String[] answer = new String[files.length];

		// 파일명을 head, num, tail로 나눠 File 객체로 저장할 리스트
		List<File> fileLst = new ArrayList<File>();

		// 파일명에서 숫자가 등장하는 인덱스를 구함
		for(int i=0; i<files.length; i++) {
			String str = files[i];

			// 숫자가 처음으로 등장하는 위치 탐색
			int stIdx = 0;
			for(int j=0; j<str.length(); j++) {
				if(str.charAt(j) >= '0' && str.charAt(j) <= '9') {
					stIdx = j;
					break;
				}
			}

			// 그 위치를 시작으로 5자리 이하로 숫자가 끝나는 위치 탐색
			int endIdx = stIdx;
			for(int j=stIdx; j<str.length(); j++) {
				if(Math.abs(endIdx - stIdx) + 1 == 5) break;

				if (str.charAt(j) < '0' || str.charAt(j) > '9') break;
				else endIdx = j;
			}

			String head = str.substring(0, stIdx);
			String num = str.substring(stIdx, endIdx + 1);
			String tail = str.substring(endIdx + 1, str.length());

			fileLst.add(new File(head, num, tail)); // 리스트에 저장
		}

		Collections.sort(fileLst); // 정렬 수행

		// 정답 배열 생성
		for(int i=0; i<fileLst.size(); i++) {
			answer[i] = fileLst.get(i).getFilename();
		}

		return answer;
	}
}