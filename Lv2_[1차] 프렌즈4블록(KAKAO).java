import java.util.*;

class Solution {

  private static String[][] graph; //게임판 복사 배열

  //블록이 아래로 내려갈 공간이 있는지 확인
  public boolean isDown(String[][] board) {
    for (int y = 0; y < board[0].length; y++) {
      for (int x = 0; x < board.length - 1; x++) {
        if (!board[x][y].equals("#") && board[x + 1][y].equals("#")) {
          return true;
        }
      }
    }
    return false;
  }

  //아래로 이동
  public void moveDown(String[][] board) {
    for (int y = 0; y < board[0].length; y++) {
      for (int x = 0; x < board.length - 1; x++) {
        if (!board[x][y].equals("#") && board[x + 1][y].equals("#")) {
          board[x + 1][y] = board[x][y];
          board[x][y] = "#";
        }
      }
    }
  }

  //4블록이 존재하는지 확인
  public boolean checkBlock(String[][] board) {
    for (int i = 0; i < board.length - 1; i++) {
      for (int j = 0; j < board[i].length - 1; j++) {
        if (board[i][j].equals("#")) continue;

        if (board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 1][j + 1]))
            return true;
      }
    }
    return false;
  }

  //4블록 지우기
  public void removeBlock(String[][] board, List<int[]> point) {
    for (int i = 0; i < point.size(); i++) {
      int[] p = point.get(i);
      int x = p[0];
      int y = p[1];
      board[x][y] = "#";
    }
  }

  //사라진 블록 개수 카운트
  public int countRemoveBlock(String[][] board) {
    int count = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j].equals("#")) count++;
      }
    }

    return count;
  }

  public int solution(int m, int n, String[] board) {
    int answer = 0;
    graph = new String[m][n];

    //게임판 복사
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        graph[i][j] = Character.toString(board[i].charAt(j));
      }
    }

    //4블록 진행
    while (true) {
      if (!checkBlock(graph)) break; //4블록이 없으면 탈출
      else {
        List<int[]> point = new ArrayList<int[]>(); //4블록 좌표 저장 리스트

        //4블록 확인해서 리스트에 좌표 저장
        for (int i = 0; i < graph.length - 1; i++) {
          for (int j = 0; j < graph[i].length - 1; j++) {
            if (graph[i][j].equals(graph[i][j + 1]) && graph[i][j].equals(graph[i + 1][j]) && graph[i][j].equals(graph[i + 1][j + 1])) {
              point.add(new int[] { i, j });
              point.add(new int[] { i, j + 1 });
              point.add(new int[] { i + 1, j });
              point.add(new int[] { i + 1, j + 1 });
            }
          }
        }

        //해당 좌표 블록 지우기
        removeBlock(graph, point);

        //남은 블록 아래로 이동
        while (isDown(graph)) { 
            moveDown(graph);
        }
      }
    }

    //사라진 블록 개수 세기
    answer = countRemoveBlock(graph);

    return answer;
  }
}