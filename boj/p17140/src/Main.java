import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17140 이차원 배열과 연산
  https://www.acmicpc.net/problem/17140
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
      StringTokenizer st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());


      int[][] metrics = new int[3][3];
      for (int i = 0; i < 3; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j = 0; j < 3; j++) {
              metrics[i][j] = Integer.parseInt(st.nextToken());
          }
      }

      bw.write(new Solution().solution(r, c, k, metrics) + "");
      bw.flush();
      bw.close();
  }
}

class Solution {
    int solution(int r, int c, int k, int[][] m) {
        r -= 1;
        c -= 1;

        List<List<Integer>> metrics = new ArrayList<>();
        for (int[] rArr : m) {
            List<Integer> row = new ArrayList<>();
            for (int v : rArr) {
                row.add(v);
            }
            metrics.add(row);
        }

        int t = 0;
        while (t <= 100) {
            // 크기가 100을 넘으면 무시
            int R = Math.min(metrics.size(), 100);
            int C = Math.min(metrics.get(0).size(), 100);

            if (0 <= r && r < R && 0 <= c && c < C && metrics.get(r).get(c) == k) {
                return t;
            }

            boolean cOperation = R < C;
            if (cOperation) {
                metrics = transposeMetrics(metrics);
                R = metrics.size();
                C = metrics.get(0).size();
            }

            int maxLen = 0;
            for (int i = 0; i < R; i++) {
                Map<Integer, Integer> count = new HashMap<>();

                List<Integer> row = metrics.get(i);
                for (int v : row) {
                    if (v == 0) continue; // 0 무시
                    count.put(v, count.getOrDefault(v, 0) + 1); // 각 숫자 count
                }

                List<Integer> keySet = new ArrayList<>(count.keySet()); // 등장한 수의 모음
                // 등장 횟수 비교
                // 등장 횟수가 같지 않으면 등장 횟수로 정렬
                // 등장 횟수가 같으면 등장한 수로 비교
                keySet.sort(Comparator.comparingInt((Integer key) -> count.get(key)).thenComparingInt(key -> key));

                List<Integer> newR = new ArrayList<>(); // 새로운 행
                for (int key : keySet) {
                    newR.add(key); // 등장한 수 먼저 추가
                    newR.add(count.get(key)); // 이후 등장 횟수 추가
                }

                metrics.set(i, newR); // 새로운 행으로 설정
                maxLen = Math.max(maxLen, newR.size()); // 가장 길이가 긴 행 찾기
            }

            maxLen = Math.min(maxLen, 100);

            for (List<Integer> row : metrics) {
                while (row.size() < maxLen) {
                    row.add(0);
                }
            }

            if (cOperation) {
                metrics = transposeMetrics(metrics);
            }

            t++;
        }

        return -1;
    }

    List<List<Integer>> transposeMetrics(List<List<Integer>> metrics) {
        int R = metrics.size();
        int C = metrics.get(0).size();

        List<List<Integer>> transposed = new ArrayList<>();
        for (int c = 0; c < C; c++) {
            List<Integer> newR = new ArrayList<>();
            for (int r = 0; r < R; r++) {
                newR.add(metrics.get(r).get(c));
            }
            transposed.add(newR);
        }

        return transposed;
    }
}