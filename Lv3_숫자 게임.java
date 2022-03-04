import java.util.*;

class Solution {
    
    public int solution(int[] A, int[] B) {
        int answer = 0; //승점
        ArrayList<Integer> aList = new ArrayList<Integer>(); //A팀 리스트
        ArrayList<Integer> bList = new ArrayList<Integer>(); //B팀 리스트
        Queue<Integer> aQueue = new LinkedList<Integer>(); //A팀 큐(승점 계산)
        Queue<Integer> bQueue = new LinkedList<Integer>(); //B팀 큐(승점 계산)
        int aIdx = 0, bIdx = 0; //A, B팀 리스트 인덱스
        
        //리스트 초기화
        for(int i=0; i<A.length; i++) {
            aList.add(A[i]);
            bList.add(B[i]);
        }
        
        //내림차순 정렬
        Collections.sort(aList, Collections.reverseOrder());
        Collections.sort(bList, Collections.reverseOrder());
        
        //A, B팀 큐에 각 리트슽의 첫번째 원소 삽입
        aQueue.offer(aList.get(aIdx++));
        bQueue.offer(bList.get(bIdx++));
        while(true) {
            //B팀의 숫자가 A팀보다 크면
            if(aQueue.peek() < bQueue.peek()) {
                answer++; //승점 증가
                //각 팀 큐에서 제거
                aQueue.poll();
                bQueue.poll();
                //큐에 각 리스트의 다음 값 삽입
                if(aIdx < aList.size()) aQueue.offer(aList.get(aIdx++));
                if(bIdx < bList.size()) bQueue.offer(bList.get(bIdx++));
            }
            //A팀의 숫자가 B팀보다 크면
            else {
                //A팀 숫자만 큐에서 제거
                aQueue.poll();
                //A팀 큐에 다음 숫자 삽입
                if(aIdx < aList.size()) aQueue.offer(aList.get(aIdx++));
            }
            
            //A, B팀의 인덱스 중 하나라도 리스트 크기와 같아지면
            if(aIdx >= aList.size() || bIdx >= bList.size()) {
                //각 팀의 큐가 비어있지 않다면
            	if(!aQueue.isEmpty() && !bQueue.isEmpty()) {
                    //A팀 마지막 숫자와 B팀 마지막 숫자를 비교하여 승점 카운트
            		if(aQueue.poll() < bQueue.poll()) answer++;
            	}
                //무한루프 탈출
            	break;
            }
        }
        
        return answer;
    }
}