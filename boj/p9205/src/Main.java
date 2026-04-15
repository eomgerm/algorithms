import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 9205 맥주 마시면서 걸어가기
  https://www.acmicpc.net/problem/9205
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 코드를 작성하세요.

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            int[][] nodes = new int[n + 2][2];
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                nodes[i][0] = Integer.parseInt(st.nextToken());
                nodes[i][1] = Integer.parseInt(st.nextToken());
            }

            Deque<int[]> queue = new ArrayDeque<>();
            boolean[] v = new boolean[n + 2];
            queue.addLast(nodes[0]);
            v[0] = true;

            boolean arrived = false;
            while (!queue.isEmpty()) {
                int[] cur = queue.removeFirst();

                if (cur == nodes[n + 1]) {
                    arrived = true;
                    break;
                }

                for (int i = 1; i < n + 2; i++) {
                    if (v[i]) continue;
                    if (Math.abs(nodes[i][0] - cur[0]) + Math.abs(nodes[i][1] - cur[1]) > 50 * 20) continue;

                    v[i] = true;
                    queue.addLast(nodes[i]);
                }
            }

            sb.append(arrived ? "happy" : "sad").append("\n");
        }

        System.out.println(sb);
    }
}