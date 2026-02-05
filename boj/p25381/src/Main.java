import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 25381 ABBC
  https://www.acmicpc.net/problem/25381
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
    String S = br.readLine();

    bw.write(new Solution().solution(S)+"");
    bw.flush();
    bw.close();

  }
}

class Solution {
    int solution(String S) {
        char[] s = S.toCharArray();
        int l = s.length, answer = 0;

        boolean[] usedB = new boolean[l];
        int c = 0;
        for (int i = l - 1; i >= 0; i--) {
            if (s[i] == 'C') c++;
            else if (s[i] == 'B' && c > 0) {
                answer++;
                c--;
                usedB[i] = true;
            }
        }

        int a = 0;
        for (int i = 0; i < l; i++) {
            if (s[i] == 'A') a++;
            else if (s[i] == 'B' && !usedB[i] && a > 0) {
                answer++;
                a--;
            }
        }
        return answer;
    }
}