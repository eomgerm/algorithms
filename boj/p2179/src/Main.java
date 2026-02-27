import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2179 비슷한 단어
  https://www.acmicpc.net/problem/2179
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.

    int N = Integer.parseInt(br.readLine());
    List<String> words = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      words.add(br.readLine());
    }

    int maxM = 0;
    int sIndex = 0;
    int tIndex = 0;

    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        String s1 = words.get(i);
        String s2 = words.get(j);

        int min = Math.min(s1.length(), s2.length());
        int cnt = 0;
        for (int k = 0; k < min; k++) {
          if (s1.charAt(k) == s2.charAt(k)) cnt++;
          else break;
        }

        if (cnt > maxM) {
          maxM = cnt;
          sIndex = i;
          tIndex = j;
        }
      }
    }

    System.out.println(words.get(sIndex));
    System.out.println(words.get(tIndex));
  }
}