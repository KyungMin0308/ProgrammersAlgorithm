import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> minPq = new PriorityQueue<Integer>(); //최소힙
        PriorityQueue<Integer> maxPq = new PriorityQueue<Integer>(Collections.reverseOrder()); //최대힙
        
        for(int i=0; i<operations.length; i++) {
            String [] oper = operations[i].split(" "); //명령어 데이터 분리
            String command = oper[0]; //명령어
            
            //명령어 수행
            if(command.equals("I")) { //삽입
            	int num = Integer.parseInt(oper[1]);
            	minPq.offer(num);
            	maxPq.offer(num);
            }
            else if(command.equals("D")) { //삭제
            	if(oper[1].equals("1")) { //1이면 최대힙의 루트값을 뽑고 그 값을 최소힙에서도 제거
            		minPq.remove(maxPq.poll());
            	}
            	else if(oper[1].equals("-1")) { //-1이면 최소힙의 루트값을 뽑고 그 값을 최대힙에서도 제거
            		maxPq.remove(minPq.poll());
            	}
            }
        }
        
        //이중 우선순위 큐가 비어있지 않다면
        if(!minPq.isEmpty() && !maxPq.isEmpty()) {
            answer[0] = maxPq.poll(); //최대값
            answer[1] = minPq.poll(); //최소값
        }
        else { //비어있다면
        	answer[0] = 0;
        	answer[1] = 0;
        }
        
        return answer;
    }
}