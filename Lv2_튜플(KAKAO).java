import java.util.*;
import java.util.Map.*;

class Solution {

  public int[] solution(String s) {
    int[] answer = {};
    Map<Integer, Integer> hm = new HashMap<Integer, Integer>(); //각 원소가 몇번 등장하는지 저장하기 위한 해시맵

    //각 숫자가 몇번 등장하는지 계산
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
        int key = 0;
        //숫자가 2자리 이상일 수 있기 때문에 숫자가 등장한 위치를 기준으로 다시 탐색
        for (int j = i; j < s.length(); j++) {
          if (s.charAt(j) < '0' || s.charAt(j) > '9') {
            key = Integer.parseInt(s.substring(i, j));
            i = j;
            break;
          }
        }

        //key는 숫자(원소), value는 등장횟수
        if (!hm.containsKey(key)) {
          int value = 1;
          hm.put(key, value);
        } else {
          hm.put(key, hm.get(key) + 1);
        }
      }
    }

    //등장횟수(value)를 기준으로 해시맵을 내림차순으로 정렬
    List<Entry<Integer, Integer>> lst = new ArrayList<Entry<Integer, Integer>>(hm.entrySet());

    Collections.sort(lst, new Comparator<Entry<Integer, Integer>>() {
        public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {
          return e2.getValue() - e1.getValue();
        }
      }
    );

    //정답 배열 생성
    answer = new int[lst.size()];
    for (int i = 0; i < lst.size(); i++) {
      answer[i] = lst.get(i).getKey();
    }

    return answer;
  }
}
