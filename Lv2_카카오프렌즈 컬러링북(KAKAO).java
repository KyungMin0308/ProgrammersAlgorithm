class Solution {

  private static int[][] graph; //picture 배열을 복사한 그래프 배열
  private static boolean[][] visited; //방문표시 배열
  private static int size = 0; //영역의 크기
  private int[] dx = { -1, 0, 1, 0 }; //x축 이동
  private int[] dy = { 0, 1, 0, -1 }; //y축 이동

  //curNum으로 되어있는 영역의 크기를 구하는 DFS 함수
  public void dfs(int x, int y, int curNum) {
    visited[x][y] = true; //해당 좌표 방문 처리
    size++; //영역 크기 증가

    //System.out.println("[" + x + " " + y + "](" + curNum  + "): " + size);

    //x축, y축 방향으로 이동하면서 영역 확인
    for (int i = 0; i < dx.length; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx < 0 || nx > graph.length - 1 || ny < 0 || ny > graph[0].length - 1) continue; //그래프 범위를 넘어가면 continue
      if (graph[nx][ny] == 0) continue; //해당 위치의 값이 0이면 continue

      //움직인 위치가 curNum과 동일하고 그 위치를 방문하지 않았다면 방문
      if (graph[nx][ny] == curNum && !visited[nx][ny]) {
        dfs(nx, ny, curNum);
      }
    }
  }

  public int[] solution(int m, int n, int[][] picture) {
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;

    visited = new boolean[picture.length][picture[0].length];
    graph = new int[picture.length][picture[0].length];

    //배열 복사
    for (int i = 0; i < picture.length; i++) {
      for (int j = 0; j < picture[i].length; j++) {
        graph[i][j] = picture[i][j];
      }
    }

    //graph를 돌면서 영역 구하기
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[i].length; j++) {
        if (graph[i][j] != 0 && !visited[i][j]) {
          numberOfArea++; //영역의 수 카운트
          dfs(i, j, graph[i][j]); //해당 좌표에서 dfs 수행
          maxSizeOfOneArea = Math.max(size, maxSizeOfOneArea); //가장 큰 영역의 크기 갱신
          size = 0; //size 변수 초기화
        }
      }
    }

    int[] answer = new int[2];
    answer[0] = numberOfArea;
    answer[1] = maxSizeOfOneArea;
    return answer;
  }
}
