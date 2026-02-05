import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dr = {0, 0, 1};
        int[] dc = {-1, 1, 0};

        for (int t = 0; t < 10; t++) {
            br.readLine();
            int[][] ladder = new int[100][100];

            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for (int c = 0; c < 100; c++) {
                if (ladder[0][c] == 0) continue;

                boolean[][] visited = new boolean[100][100];
                visited[0][c] = true;
                int[] cur = {0, c};
                while (true) {
                    int curR = cur[0];
                    int curC = cur[1];

                    if (ladder[curR][curC] == 2) {
                        answer = c;
                        break;
                    }

                    if (curR == 99) break;

                    for (int i = 0; i < 3; i++) {
                        int nextR = curR + dr[i];
                        int nextC = curC + dc[i];

                        if (nextC < 0 || nextC >= 100) continue;
                        if (visited[nextR][nextC]) continue;
                        if (ladder[nextR][nextC] == 0) continue;
                        cur = new int[] {nextR, nextC};
                        visited[nextR][nextC] = true;
                        break;
                    }
                }

            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

}
