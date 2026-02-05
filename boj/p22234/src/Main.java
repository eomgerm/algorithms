import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 22234 가희와 은행
  https://www.acmicpc.net/problem/22234
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int T = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());

      int[][] c1 = new int[N][2];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        c1[i][0] = Integer.parseInt(st.nextToken());
        c1[i][1] = Integer.parseInt(st.nextToken());
      }

      int M = Integer.parseInt(br.readLine());
      int[][] c2 = new int[M][3];

      for (int i =0; i < M; i++) {
          st = new StringTokenizer(br.readLine());

          c2[i][0] = Integer.parseInt(st.nextToken());
          c2[i][1] = Integer.parseInt(st.nextToken());
          c2[i][2] = Integer.parseInt(st.nextToken());
      }

      int[] answer = new Solution().solution(T, W, c1, c2);

//      for (int i : answer) {
//          bw.write(i + "\n");
//      }
//      bw.flush();
//      bw.close();
  }
}

class Solution {
    int[] solution(int T, int W, int[][] c1, int[][] c2) {
        int[] answer = {};

        int t = 0;

        ArrayDeque<int[]> queue = new ArrayDeque<>(Arrays.asList(c1));
        ArrayDeque<int[]> aQueue = new ArrayDeque<>(Arrays.asList(c2));

        int[] cur = null;
        List<Integer> working = new ArrayList<>();
        int cnt = 0;

        while (t < W) {
            if (!aQueue.isEmpty() && aQueue.peek()[2] == t) {
                queue.add(aQueue.removeFirst());
            }

            if (cur == null && !queue.isEmpty()) {
                cur = queue.removeFirst();
            }

            working.add(cur[0]);

            System.out.println();

            cur[1] -= 1;
            cnt++;

            if (cur[1] == 0 || cnt % T == 0) {
                cur = null;
                cnt = 0;
            }

            t++;
        }

        answer = new int[working.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = working.get(i);
        }

        return answer;
    }
}