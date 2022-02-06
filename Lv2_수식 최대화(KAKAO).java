import java.util.*;

class Solution {
    
	private ArrayList<ArrayList<Integer>> per = new ArrayList<ArrayList<Integer>>(); //연산자 경우의 수 저장 리스트
	private int perIdx = 0; //리스트 인덱스 컨트롤 변수

	//순열, 모든 경우의 수 구하기
	private void permutation(int n, int r, LinkedList<Integer> perArr, int[] perCheck) {
		if (perArr.size() == r) {
			for (int i : perArr) {
				per.get(perIdx).add(i);
			}
			perIdx++;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (perCheck[i] == 0) {
				perArr.add(i);
				perCheck[i] = 1;
				permutation(n, r, perArr, perCheck);
				perCheck[i] = 0;
				perArr.removeLast();
			}
		}
	}

	public long solution(String expression) {
		long answer = 0;
		ArrayList<ArrayList<String>> exp = new ArrayList<ArrayList<String>>(); //수식을 숫자와 연산자로 나눠 저장할 리스트
		ArrayList<Character> operator = new ArrayList<Character>(); //수식에 있는 연산자 종류

		//연산자 종류 파악
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9')
				continue;
			if (!operator.contains(expression.charAt(i)))
				operator.add(expression.charAt(i));
		}

		//순열을 사용하기 위한 변수(n, r, lst, check)
		int n = operator.size();
		int r = operator.size();

		LinkedList<Integer> lst = new LinkedList<Integer>();
		int[] check = new int[n];

		//모든 경우의 수 구하기(nPr)
		int size = 1;
		for (int i = 1; i <= n; i++) { size *= i; }

		//리스트 초기화
		for (int i = 0; i < size; i++) {
			exp.add(new ArrayList<String>());
			per.add(new ArrayList<Integer>());
		}

		//경우의 수 개수만큼 수식을 숫자와 연산자로 분리하여 2차원 리스트에 저장
		for (int i = 0; i < exp.size(); i++) {
			String num = "";
			for (int j = 0; j < expression.length(); j++) {
				if (expression.charAt(j) >= '0' && expression.charAt(j) <= '9')
					num += Character.toString(expression.charAt(j));
				else {
					exp.get(i).add(num);
					exp.get(i).add(Character.toString(expression.charAt(j)));
					num = "";
				}
			}
			exp.get(i).add(num); //마지막 숫자 리스트에 삽입
		}

		// 가능한 연산자 우선순위 구하기(인덱스 기준)
		permutation(n, r, lst, check);

		//연산자 우선순위를 토대로 값 계산
		for (int i = 0; i < per.size(); i++) {
			for (int j = 0; j < per.get(i).size(); j++) {
				char oper = operator.get(per.get(i).get(j));
				while(exp.get(i).contains(Character.toString(oper))) {
					for (int m = 0; m < exp.get(i).size(); m++) {
						if (exp.get(i).get(m).equals(Character.toString(oper))) {
							long res = 0;
							if (oper == '+') {
								res = Long.parseLong(exp.get(i).get(m - 1)) + Long.parseLong(exp.get(i).get(m + 1));
							}
							else if (oper == '-') {
								res = Long.parseLong(exp.get(i).get(m - 1)) - Long.parseLong(exp.get(i).get(m + 1));
							}
							else if (oper == '*') {
								res = Long.parseLong(exp.get(i).get(m - 1)) * Long.parseLong(exp.get(i).get(m + 1));
							}

							exp.get(i).set(m - 1, String.valueOf(res));
							exp.get(i).remove(m);
							exp.get(i).remove(m);
							break;
						}
					}
				}
			}
		}

		//절대값이 가장 큰 변수 answer에 저장
		for (int i = 0; i < exp.size(); i++) {
			for (int j = 0; j < exp.get(i).size(); j++) {
				answer = Math.max(answer, Math.abs(Long.parseLong(exp.get(i).get(j))));
			}
		}

		return answer;
	}
}