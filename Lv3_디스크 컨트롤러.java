import java.util.*;

class Solution {
    
    //작업 클래스
    class Task implements Comparable<Task> {
        private int in; //요청시간
        private int time; //소요시간
        
        public Task(int in, int time) {
            this.in = in;
            this.time = time;
        }
        
        //요청시간 순으로 오름차순 정렬
        @Override
        public int compareTo(Task t) {
            //요청시간이 같다면 소요시간 순으로 정렬
            if(this.in == t.in) return this.time - t.time;
            else return this.in - t.in;
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<Task> pq = new PriorityQueue<Task>(); //작업 우선순위큐
        ArrayList<Task> disks = new ArrayList<Task>(); //디스크
        
        //우선순위큐에 job 추가
        for(int i=0; i<jobs.length; i++) {
            pq.offer(new Task(jobs[i][0], jobs[i][1]));
        }
        
        int total = 0; //총 작업시간
        Task cur = null; //현재 요청 작업
        while(true) {
            //현재 들어온 작업요청
            if(!pq.isEmpty()) cur = pq.peek();
            
            //디스크가 비어있다면 먼저 들어온 요청을 처리
            if(disks.isEmpty()) {
                cur = pq.poll();
                total += ((cur.in - total) + cur.time); //총 작업시간
                answer += (total - cur.in); //작업 요청부터 종료까지 걸린 시간
            }
            //디스크가 비어있지 않다면
            else {
            	//현재 disks의 작업을 소요시간 순으로 정렬
                Comparator<Task> com = new Comparator<Task>() {
                    @Override
                    public int compare(Task t1, Task t2) {
                        return t1.time - t2.time;
                    } 
                };
                Collections.sort(disks, com);
                
                //disks의 첫번째 작업 처리
                cur = disks.get(0);
                total += cur.time;
                answer += (total - cur.in);
                disks.remove(0);
            }
            
            //현재까지 진행된 시간을 기준으로 요청된 작업이 있는지 확인
            Task next = null; //다음 작업
            while(true) {
            	//작업 리스트가 비어있지 않다면 다음 작업 불러옴
                if(!pq.isEmpty()) next = pq.peek();
                
                //작업 리스트가 비어있다면 next가 null이므로 break
                if(next == null) break;
                
                //다음 작업이 현재 총 진행시간보다 작거나 같다면
                if(next.in <= total) {
                	//다음 작업을 디스크에 추가
                    next = pq.poll();
                    disks.add(next);
                }
                
                //작업 리스트가 비었거나 다음 작업의 요청시간이 총 작업시간보다 크면 break
                if(pq.isEmpty() || next.in > total) break;
            }
            
            //작업 리스트와 디스크가 모두 비었다면 break
            if(pq.isEmpty() && disks.isEmpty()) break;
        }
        
        answer /= jobs.length; //작업 평균
        
        return answer;
    }
}