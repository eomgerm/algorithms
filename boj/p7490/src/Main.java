import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 7490 0 만들기
  https://www.acmicpc.net/problem/7490
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
      int T = Integer.parseInt(br.readLine());
      for (int i = 0; i < T; i++) {
          int N = Integer.parseInt(br.readLine());

          String[] answer = new Solution().solution(N);
          for (String a : answer) {
              bw.write(a + "\n");
          }
          bw.write("\n");
      }
      bw.flush();
      bw.close();
  }
}

class Solution {
    int N;
    String[] solution(int N) {
        this.N = N;

        List<String> result = new ArrayList<>();
        combination(1, "", result);

        List<String> answer = validate(result);
        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }

    void combination(int n, String str, List<String> result) {
        if (n == N) {
            result.add(str + n);
            return;
        }

        combination(n + 1,str + n + "+", result);
        combination(n + 1, str + n + "-", result);
        combination(n + 1 , str + n + " ", result);
    }

    List<String> validate(List<String> combinations) {
        List<String> validated = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+|[+\\-]");

        for (String str : combinations) {
            String pStr = str.replace(" ", "");

            Matcher m = p.matcher(pStr);

            List<String> tokens = new ArrayList<>();
            while (m.find()) {
                tokens.add(m.group());
            }

            int result = Integer.parseInt(tokens.get(0));
            for (int i = 1; i < tokens.size() - 1; i += 2) {
                String t = tokens.get(i);

                if (t.equals("+")) {
                    result += Integer.parseInt(tokens.get(i+1));
                } else {
                    result -= Integer.parseInt(tokens.get(i+1));
                }
            }

            if (result == 0) {
                validated.add(str);
            }
        }

        return validated;
    }
}