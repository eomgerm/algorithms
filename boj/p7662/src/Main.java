import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 7662 이중 우선순위 큐
  https://www.acmicpc.net/problem/7662
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.

      int T = Integer.parseInt(br.readLine());
      for (int i = 0; i < T; i++) {
          int k = Integer.parseInt(br.readLine());
          String[] cmd = new String[k];
          for (int j = 0; j < k; j++) {
              cmd[j] = br.readLine();
          }
          bw.write(new Solution().solution(cmd) + "\n");
      }

      bw.flush();
      bw.close();
  }
}

class Solution {
    String solution(String[] cmd) {
        String answer;

        PriorityQueue<int[]> minQ = new PriorityQueue<>(Comparator.comparingInt(v -> v[0]));
        PriorityQueue<int[]> maxQ = new PriorityQueue<>(Comparator.comparingInt(v -> -v[0]));

        int key = 0;
        boolean[] isDeleted = new boolean[cmd.length];

        for (String c : cmd) {
            String[] split = c.split(" ");

            String op = split[0];
            int v = Integer.parseInt(split[1]);

            switch (op) {
                case "I": {
                    minQ.add(new int[] {v, key});
                    maxQ.add(new int[] {v, key});

                    key++;

                    break;
                }

                case "D": {
                    if (v == 1) {
                        while (!maxQ.isEmpty() && isDeleted[maxQ.peek()[1]]) {
                            maxQ.poll();
                        }

                        if (!maxQ.isEmpty()) {
                            isDeleted[maxQ.poll()[1]] = true;
                        }
                    } else {
                        while (!minQ.isEmpty() && isDeleted[minQ.peek()[1]]) {
                            minQ.poll();
                        }

                        if (!minQ.isEmpty()) {
                            isDeleted[minQ.poll()[1]] = true;
                        }

                    }

                    break;
                }
            }
        }

        while (!maxQ.isEmpty() && isDeleted[maxQ.peek()[1]]) {
            maxQ.poll();
        }
        while (!minQ.isEmpty() && isDeleted[minQ.peek()[1]]) {
            minQ.poll();
        }

        if (minQ.isEmpty() || maxQ.isEmpty()) {
            answer = "EMPTY";
        } else {
            int min = minQ.peek()[0];
            int max = maxQ.peek()[0];

            answer = max + " " + min;
        }

        return answer;
    }
}