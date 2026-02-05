import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 22251 빌런 호석
  https://www.acmicpc.net/problem/22251
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 코드를 작성하세요.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        bw.write(new Solution().solution(N, K, P, X) + "");
        bw.flush();
        bw.close();
    }
}

class Solution {

    int[] segments = {
        0b1111110, // 0: a b c d e f
        0b0110000, // 1:   b c
        0b1101101, // 2: a b   d e   g
        0b1111001, // 3: a b c d     g
        0b0110011, // 4:   b c     f g
        0b1011011, // 5: a   c d   f g
        0b1011111, // 6: a   c d e f g
        0b1110000, // 7: a b c
        0b1111111, // 8: a b c d e f g
        0b1111011  // 9: a b c d   f g
    };

    int solution(int maxFloor, int maxDigit, int maxConversion, int floor) {
        int[] floorDigits = convertToDigits(floor, maxDigit);

        int answer = 0;
        for (int n = 1; n <= maxFloor; n++) {
            if (n == floor) {
                continue;
            }

            int[] nDigits = convertToDigits(n, maxDigit);

            if (checkValid(floorDigits, nDigits, maxConversion)) {
                answer++;
            }
        }

        return answer;
    }

    int[] convertToDigits(int num, int maxDigit) {
        int[] digits = new int[maxDigit];

        String strNum = String.valueOf(num);

        for (int i = strNum.length(); i < maxDigit; i++) {
            strNum = "0" + strNum;
        }

        String[] strDigits = strNum.split("");

        for (int i = 0; i < maxDigit; i++) {
            digits[i] = Integer.parseInt(strDigits[i]);
        }

        return digits;
    }

    boolean checkValid(int[] a, int[] b, int maxConversion) {
        int difference = 0;
        for (int i = 0; i < a.length; i++) {
            int k = segments[a[i]];
            int j = segments[b[i]];

            difference += Integer.bitCount(k ^ j);
        }

        return difference <= maxConversion;
    }
}