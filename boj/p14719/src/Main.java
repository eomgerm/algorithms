import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 14719 빗물
  https://www.acmicpc.net/problem/14719
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 코드를 작성하세요.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] blocks = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(new Solution().solution(H, W, blocks) + "");
        bw.flush();
        bw.close();
    }
}

class Solution {

    int solution(int H, int W, int[] blocks) {
        int answer = 0;

        for (int i = 1; i < W; i++) {
            int cur = blocks[i];

            int left = 0;
            int right = 0;

            for (int j = 0; j < i; j++) {
                left = Math.max(left, blocks[j]);
            }

            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, blocks[j]);
            }

            if (cur < left && cur < right) {
                answer += Math.min(left, right) - cur;
            }
        }

        return answer;
    }
}