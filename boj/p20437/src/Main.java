import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 20437 문자열 게임 2
  https://www.acmicpc.net/problem/20437
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
      int T = Integer.parseInt(br.readLine());
      for (int i = 0; i < T; i++) {
          String W = br.readLine();
          int K = Integer.parseInt(br.readLine());
          bw.write(new Solution().solution(W, K) + "\n");
      }

      bw.flush();
      bw.close();
  }
}

class Solution {
    String solution(String W, int K) {
        Map<Character, List<Integer>> charIndices = new HashMap<>();

        boolean found = false;

        char[] wArr = W.toCharArray();
        for (int i = 0; i < wArr.length; i++) {
            char c = wArr[i];
            charIndices.putIfAbsent(c, new ArrayList<>());
            charIndices.get(c).add(i);

            if (charIndices.get(c).size() == K) {
                found = true;
            }
        }

        if (!found) {
            return "-1";
        }

        int min = W.length();
        int max = 0;
        for (char c : charIndices.keySet()) {
            List<Integer> indices =charIndices.get(c);

            if (indices.size() < K) continue;

            for (int i = 0; i < indices.size() - K + 1; i++) {
                int len = indices.get(i + K - 1) - indices.get(i) + 1;
                min = Math.min(min, len);
                max = Math.max(max, len);
            }
        }


        return min + " " + max;
    }
}