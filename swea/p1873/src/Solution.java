import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int H, W;
    static char[][] map;
    static int[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T;t ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            pos = new int[2];
            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == '^' || map[i][j] == '>' || map[i][j] == 'v' || map[i][j] == '<') {
                        pos[0] = i;
                        pos[1] = j;
                    }
                }
            }

            br.readLine();
            char[] cmds = br.readLine().toCharArray();
            for (char cmd : cmds) {
                switch (cmd) {
                    case 'U':
                        moveUp();
                        break;
                    case 'D':
                        moveDown();
                        break;
                    case 'L':
                        moveLeft();
                        break;
                    case 'R':
                        moveRight();
                        break;
                    case 'S':
                        shoot();
                        break;
                }
            }

            sb.append("#").append(t + 1).append(" ");
            for (char[] r : map) {
                sb.append(r).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void moveUp() {
        int h = pos[0];
        int w = pos[1];
        map[h][w] = '^';

        int nH = h - 1;
        int nW = w;
        if (!isInMap(nH, nW)) return;
        if (!isFlat(nH, nW)) return;

        map[h][w] = '.';
        map[nH][nW] = '^';
        pos[0] = nH;
        pos[1] = nW;
    }

    static void moveDown() {
        int h = pos[0];
        int w = pos[1];
        map[h][w] = 'v';

        int nH = h + 1;
        int nW = w;
        if (!isInMap(nH, nW)) return;
        if (!isFlat(nH, nW)) return;

        map[h][w] = '.';
        map[nH][nW] = 'v';
        pos[0] = nH;
        pos[1] = nW;
    }

    static void moveLeft() {
        int h = pos[0];
        int w = pos[1];
        map[h][w] = '<';

        int nH = h;
        int nW = w - 1;
        if (!isInMap(nH, nW)) return;
        if (!isFlat(nH, nW)) return;

        map[h][w] = '.';
        map[nH][nW] = '<';
        pos[0] = nH;
        pos[1] = nW;
    }

    static void moveRight() {
        int h = pos[0];
        int w = pos[1];
        map[h][w] = '>';

        int nH = h;
        int nW = w + 1;
        if (!isInMap(nH, nW)) return;
        if (!isFlat(nH, nW)) return;

        map[h][w] = '.';
        map[nH][nW] = '>';
        pos[0] = nH;
        pos[1] = nW;
    }

    static void shoot() {
        int h = pos[0];
        int w = pos[1];

        switch (map[h][w]) {
            case '^':
                for (int i = h; i >= 0; i--) {
                    if (isSteelWall(i, w)) break;
                    if (isBrickWall(i, w)) {
                        map[i][w] = '.';
                        break;
                    }
                }
                break;
            case '>':
                for (int i = w; i < W; i++) {
                    if (isSteelWall(h, i)) break;
                    if (isBrickWall(h, i)) {
                        map[h][i] = '.';
                        break;
                    }
                }
                break;
            case 'v':
                for (int i = h; i < H; i++) {
                    if (isSteelWall(i, w)) break;
                    if (isBrickWall(i, w)) {
                        map[i][w] = '.';
                        break;
                    }
                }
                break;
            case '<':
                for (int i = w; i >= 0; i--) {
                    if (isSteelWall(h, i)) break;
                    if (isBrickWall(h, i)) {
                        map[h][i] = '.';
                        break;
                    }
                }
                break;
        }
    }

    static boolean isInMap(int h, int w) {
        return 0 <= h && h < H && 0 <= w && w < W;
    }

    static boolean isFlat(int h, int w) {
        return map[h][w] == '.';
    }

    static boolean isBrickWall(int h, int w) {
        return map[h][w] == '*';
    }

    static boolean isSteelWall(int h, int w) {
        return map[h][w] == '#';
    }
}
