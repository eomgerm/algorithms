import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1374 강의실
  https://www.acmicpc.net/problem/1374
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.

      int N = Integer.parseInt(br.readLine());

      int[][] lectures = new int[N][3];
      for (int i = 0; i < N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          lectures[i][0] = Integer.parseInt(st.nextToken());
          lectures[i][1] = Integer.parseInt(st.nextToken());
          lectures[i][2] = Integer.parseInt(st.nextToken());
      }

      bw.write(new Solution().solution(lectures) + "");
      bw.flush();
      bw.close();
  }
}

class Solution {
    int solution(int[][] lectures) {
        int answer = 0;

        Arrays.sort(lectures, Comparator.comparingInt(i -> i[1]));


        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));

        for (int[] lecture : lectures) {
            if (!queue.isEmpty()) {
                int[] cur = queue.poll();

                if (lecture[1] < cur[2]) {
                    queue.add(cur);
                }
            }

            queue.add(lecture);

            answer = Math.max(answer, queue.size());
        }

        return answer;
    }
}