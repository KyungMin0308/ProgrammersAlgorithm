class Solution {

  //N을 K진수로 변환하는 함수
  public String getNum(int n, int k) {
    StringBuffer str = new StringBuffer();

    while (n > 0) {
      str.append(Integer.toString(n % k));
      n /= k;
    }

    return str.reverse().toString();
  }

  //Range까지 소수가 무엇인지 구하는 함수
  public boolean isPrime(String num) {
    if (
      num.equals("0") || num.equals("1")
    ) return false; //0, 1은 소수가 아님

    //num의 제곱근까지 반복문 수행
    for (int i = 2; i <= Math.sqrt(Long.parseLong(num)); i++) {
      if (Long.parseLong(num) % i == 0) { //num이 i로 나눠 떨어지면 소수가 아님
        return false;
      }
    }

    //위에서 걸리는게 없다면 소수이므로 true 리턴
    return true;
  }

  public int solution(int n, int k) {
    int answer = 0;
    String num = "";

    //n을 k 진수로 변환
    num = getNum(n, k);

    //0을 기준으로 num을 분할
    String[] prime = num.split("0");

    //prime 배열을 돌면서 소수가 몇개인지 확인
    for (int i = 0; i < prime.length; i++) {
      if (prime[i].equals("")) continue; //공백 문자면 continue
      if (isPrime(prime[i])) answer++;
    }

    return answer;
  }
}
