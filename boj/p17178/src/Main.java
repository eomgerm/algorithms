import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17178 줄서기
  https://www.acmicpc.net/problem/17178
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());

      String[][] fans = new String[N][5];
      for (int i = 0; i < N; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j = 0; j < 5; j++) {
              fans[i][j] = st.nextToken();
          }
      }

      String answer = new Solution().solution(fans);

      bw.write(answer);
      bw.flush();
      bw.close();
  }
}

class Solution {
    String solution(String[][] fans) {
        String answer = "GOOD";

        List<String> fansList = new ArrayList<>();
        List<String> queue = new ArrayList<>();
        for (String[] fan : fans) {
            fansList.addAll(Arrays.asList(fan));
            queue.addAll(Arrays.asList(fan));
        }

        fansList.sort(Comparator.comparing((String i) -> i.substring(0, 1))
                                .thenComparing(i -> Integer.parseInt(i.substring(2, Math.min(i.length(), 6)))));

        Deque<String> w = new ArrayDeque<>();

        while (!fansList.isEmpty()) {
            if (!queue.isEmpty() && fansList.get(0).equals(queue.get(0))) {
                queue.remove(0);
                fansList.remove(0);
            } else if (!w.isEmpty() && fansList.get(0).equals(w.peekLast())) {
                w.removeLast();
                fansList.remove(0);
            } else if (!queue.isEmpty()) {
                w.addLast(queue.remove(0));
            } else {
                answer = "BAD";
                break;
            }
        }

        return answer;
    }
}