import java.util.*;

class Solution {
    
    //좌표 클래스
    class Point implements Comparable<Point> {
        private int v; //노드 번호
        private int x; 
        private int y;
        
        public Point(int v, int x, int y) {
            this.v = v;
            this.x = x;
            this.y = y;
        }
        
        public int getV() { return this.v; }
        public int getX() { return this.x; }
        
        //y좌표 기준으로 내림차순 정렬
        //만약 y좌표가 같다면 x좌표 기준으로 오름차순 정렬
        @Override
        public int compareTo(Point p) {
            if(this.y == p.y) return this.x - p.x;
            else return p.y - this.y;
        }
    }
    
    //노드 클래스
    class Node {
        private int v; //노드 번호
        private int data; //가중치(Point의 x값)
        private Node left; //좌측 자식
        private Node right; //우측 자식
        
        public Node(int v, int data) {
            this.v = v;
            this.data = data;
            this.left = null;
            this.right = null;
        }
        
        public int getV() { return this.v; }
        public int getData() { return this.data; }
        public Node getLeft() { return this.left; }
        public Node getRight() { return this.right; }
        
        public void setLeft(Node node) { this.left = node; }
        public void setRight(Node node) { this.right = node; }
    }
    
    //이진 트리에 노드 추가
    public void insert(Point p) {
        //루트 노드가 null이면
        if(rootNode == null) {
            rootNode = new Node(p.getV(), p.getX()); //루트 노드 셋팅
        }
        else {
            Node head = rootNode;
            Node curNode;
            
            while(true) {
                curNode = head;
                
                //현재 루트의 X좌표 값보다 작은 경우
                if(head.getData() > p.getX()) { //왼쪽으로 추가
                    head = head.getLeft();
                    
                    if(head == null) { //null이면 새롭게 노드를 추가 후 탈출
                        curNode.setLeft(new Node(p.getV(), p.getX()));
                        break;
                    }
                }
                //현재 루트의 X좌표 값보다 큰 경우
                else { //오른쪽으로 추가
                    head = head.getRight();
                   
                    if(head == null) { //null이면 새롭게 노드를 추가 후 탈출
                        curNode.setRight(new Node(p.getV(), p.getX()));
                        break;
                    }
                }
            }
        }
    }
        
    //전위 순회: 루트->왼쪽->오른쪽
    public void preorder(Node node) {
        if(node != null) {
            pre.add(node.getV()); //루트 방문
            preorder(node.left); //왼쪽 탐색
            preorder(node.right); //오른쪽 탐색
        }
    }
        
    //후위 순회: 왼쪽->오른쪽->루트
    public void postorder(Node node) {
        if(node != null) {
            postorder(node.left); //왼쪽 탐색
            postorder(node.right); //오른쪽 탐색
            post.add(node.getV()); //루트 방문
        }
    }
    
    private ArrayList<Integer> pre = new ArrayList<Integer>(); //전위 방문 리스트
    private ArrayList<Integer> post = new ArrayList<Integer>(); //후위 방문 리스트
    private Node rootNode = null; //루트 노드
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        
        //nodeinfo를 바탕으로 노드 번호와 이진 트리 생성 순서 지정
        PriorityQueue<Point> pq = new PriorityQueue<Point>();
        
        //제일 큰 y좌표를 갖는 좌표가 루트 노드
        for(int i=0; i<nodeinfo.length; i++) {
            //우선순위 큐에 Point(노드번호, x좌표, y좌표) 저장
            pq.offer(new Point(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        
        //이진 트리 생성
        while(!pq.isEmpty()) {
            insert(pq.poll());
        }
        
        preorder(rootNode); //전위 순회
        postorder(rootNode); //후위 순회
        
        //정답 배열 생성
        for(int i=0; i<pre.size(); i++) {
            answer[0][i] = pre.get(i);
            answer[1][i] = post.get(i);
        }
        
        return answer;
    }
}