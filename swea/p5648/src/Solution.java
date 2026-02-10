import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[][] atoms = new int[N][4];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                atoms[i][0] = Integer.parseInt(st.nextToken()) * 2 + 2000;
                atoms[i][1] = Integer.parseInt(st.nextToken()) * 2 + 2000;
                atoms[i][2] = Integer.parseInt(st.nextToken());
                atoms[i][3] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;
            int[] dy = {1, -1, 0, 0};
            int[] dx = {0, 0, -1, 1};

            int aliveCnt = N;
            int[][] grid = new int[4001][4001];

            while (aliveCnt > 1) {
                for (int i = 0; i < N; i++) {
                    if (atoms[i][3] == 0) continue;

                    atoms[i][0] += dx[atoms[i][2]];
                    atoms[i][1] += dy[atoms[i][2]];

                    int x = atoms[i][0];
                    int y = atoms[i][1];

                    if (x < 0 || x > 4000 || y < 0 || y > 4000) {
                        atoms[i][3] = 0;
                        aliveCnt--;
                        continue;
                    }

                    grid[y][x]++;
                }

                for (int i = 0; i < N; i++) {
                    if (atoms[i][3] == 0) continue;

                    int x = atoms[i][0];
                    int y = atoms[i][1];

                    if (grid[y][x] > 1) {
                        answer += atoms[i][3];
                        atoms[i][3] = 0;
                        aliveCnt--;
                    }
                }

                for (int i = 0; i < N; i++) {
                    int x = atoms[i][0];
                    int y = atoms[i][1];

                    if (x < 0 || x > 4000 || y < 0 || y > 4000) continue;
                    grid[y][x] = 0;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
