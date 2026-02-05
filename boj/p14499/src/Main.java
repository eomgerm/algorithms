import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 14499 주사위 굴리기
  https://www.acmicpc.net/problem/14499
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int[][] map = new int[N][M];
      for (int r = 0; r < N; r++) {
          st = new StringTokenizer(br.readLine());
          for (int c = 0; c < M; c++) {
              map[r][c] = Integer.parseInt(st.nextToken());
          }
      }

      int[] cmds = new int[K];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < K; i++) {
          cmds[i] = Integer.parseInt(st.nextToken());
      }

      int[] answer = new Solution().solution(map, x, y, cmds);

      for (int v : answer) {
          bw.write(v + "\n");
      }

      bw.flush();
      bw.close();
  }
}

class Solution {
    int[] solution(int[][] map, int x, int y, int[] cmds) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        int N = map.length;
        int M = map[0].length;

        int curX = x;
        int curY = y;

        Dice dice = new Dice();
        List<Integer> answerList = new ArrayList<>();

        for (int cmd : cmds) {
            int c = cmd - 1;
            int nextX = curX + dx[c];
            int nextY = curY + dy[c];

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                continue;
            }

            curX = nextX;
            curY = nextY;

            switch (cmd) {
                case 1:
                    dice.rollE();
                    break;
                case 2:
                    dice.rollW();
                    break;
                case 3:
                    dice.rollN();
                    break;
                case 4:
                    dice.rollS();
                    break;
            }

            if (map[curX][curY] == 0) {
                map[curX][curY] = dice.bottom();
            } else {
                dice.setBottom(map[curX][curY]);
                map[curX][curY] = 0;
            }

            answerList.add(dice.top());
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }


        return answer;
    }
}

class Dice {
    int[] f = new int[6];

    void rollE() {
        int T=f[0],B=f[1],N=f[2],S=f[3],W=f[4],E=f[5];
        f[0]=W; f[1]=E; f[4]=B; f[5]=T;
    }

    void rollW() {
        int T=f[0],B=f[1],N=f[2],S=f[3],W=f[4],E=f[5];
        f[0]=E; f[1]=W; f[4]=T; f[5]=B;
    }

    void rollN() {
        int T=f[0],B=f[1],N=f[2],S=f[3],W=f[4],E=f[5];
        f[0]=S; f[1]=N; f[2]=T; f[3]=B;
    }

    void rollS() {
        int T=f[0],B=f[1],N=f[2],S=f[3],W=f[4],E=f[5];
        f[0]=N; f[1]=S; f[2]=B; f[3]=T;
    }

    int top() { return f[0]; }
    int bottom() { return f[1]; }

    void setBottom(int v) {
        f[1] = v;
    }
}