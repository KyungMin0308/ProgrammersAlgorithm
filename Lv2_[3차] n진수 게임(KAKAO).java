import java.util.*;

class Solution {

  //num을 n진법으로 변환한 문자열 리턴하는 함수
  public String getNum(int num, int n) {
    StringBuffer sb = new StringBuffer();

    while (num > 0) {
      if (num % n > 9) {
        if (num % n == 10) sb.append("A");
        else if (num % n == 11) sb.append("B");
        else if (num % n == 12) sb.append("C");
        else if (num % n == 13) sb.append("D");
        else if (num % n == 14) sb.append("E");
        else if (num % n == 15) sb.append("F");
      } 
      else {
        sb.append(String.valueOf(num % n));
      }
      num /= n;
    }

    return sb.reverse().toString();
  }

  public String solution(int n, int t, int m, int p) {
    String answer = "";
    ArrayList<String> lst = new ArrayList<String>(); //n진수로 변환한 숫자를 한자리씩 저장할 리스트
    lst.add("0"); //숫자 0은 n진법으로 변환해도 0

    //1~(t*m)까지 숫자를 n진법으로 변환후 리스트에 추가
    for (int i = 1; i <= t * m; i++) {
      String str = getNum(i, n);

      //변환된 문자열의 길이가 1보다 크면
      if (str.length() > 1) {
        String[] s = str.split(""); //한개씩 나누고
        for (int j = 0; j < s.length; j++) { //리스트에 추가
          lst.add(s[j]);
        }
      }
      //변환된 문자열의 길이가 1이면 바로 리스트에 추가
      else {
        lst.add(str);
      }
    }

    //튜브가 말해야할 숫자 구하기
    for (int i = p - 1; i < lst.size(); i += m) {
      answer += lst.get(i);

      //answer의 길이가 t와 같아지면 탈출
      if (answer.length() == t) break;
    }

    return answer;
  }
}