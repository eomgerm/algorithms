import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 16724 피리 부는 사나이
  https://www.acmicpc.net/problem/16724
*/

public class Main {
    static int[][] parents;
    static int R, C;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        answer = R * C;
        boolean[][] v = new boolean[R][C];
        parents = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                parents[r][c] = r * C + c;
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (v[r][c]) {
                    continue;
                }
                int cur = r * C + c;

                while (true) {
                    int curR = cur / C;
                    int curC = cur % C;

                    int nextR = curR, nextC = curC;
                    switch (map[curR][curC]) {
                        case 'U':
                            nextR -= 1;
                            break;
                        case 'D':
                            nextR += 1;
                            break;
                        case 'R':
                            nextC += 1;
                            break;
                        case 'L':
                            nextC -= 1;
                            break;
                    }

                    int next = nextR * C + nextC;
                    if (v[nextR][nextC]) {
                        union(cur, next);
                        break;
                    }
                    v[nextR][nextC] = true;
                    union(cur, next);
                    cur = next;
                }
            }
        }

        System.out.println(answer);
    }

    static int find(int x) {
        int r = x / C;
        int c = x % C;
        if (x != parents[r][c]) {
            parents[r][c] = find(parents[r][c]);
        }

        return parents[r][c];
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot != yRoot) {
            int xR = xRoot / C;
            int xC = xRoot % C;
            int yR = yRoot / C;
            int yC = yRoot % C;

            parents[xR][xC] = parents[yR][yC];
            answer--;
        }
    }
}