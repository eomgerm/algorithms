import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1786 찾기
  https://www.acmicpc.net/problem/1786
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.
        String T = br.readLine();
        String P = br.readLine();

        int[] pi = new int[P.length()];
        int j = 0;

        for (int i = 1; i < P.length(); i++) {
            while (j > 0 && P.charAt(i) != P.charAt(j)) {
                j = pi[j - 1];
            }

            if (P.charAt(i) == P.charAt(j)) {
                pi[i] = ++j;
            }
        }

        List<Integer> matchIdx = new ArrayList<>();
        j = 0;
        for (int i = 0; i < T.length(); i++) {
            while (j > 0 && T.charAt(i) != P.charAt(j)) {
                j = pi[j - 1];
            }

            if (T.charAt(i) == P.charAt(j)) {
                if (j == P.length() - 1) {
                    matchIdx.add(i - P.length() + 2);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(matchIdx.size());
        for (int idx : matchIdx) {
            System.out.println(idx);
        }
    }
}