import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 16235 나무 재테크
  https://www.acmicpc.net/problem/16235
*/

public class Main {

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] farm = new int[N + 1][N + 1];
        for (int[] f : farm) {
            Arrays.fill(f, 5);
        }

        int[][] A = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<Integer>[][] farmTrees = new ArrayDeque[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                farmTrees[r][c] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            farmTrees[x][y].addLast(z);
        }
        int answer = M;

        while (K-- > 0) {
            // 봄 & 여름 & 겨울
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    Deque<Integer> trees = farmTrees[r][c];

                    int size = trees.size();
                    int n = 0;
                    while (size-- > 0) {
                        int tree = trees.removeFirst();

                        if (farm[r][c] >= tree) {
                            farm[r][c] -= tree;
                            trees.addLast(tree + 1);
                        } else {
                            n += tree / 2;
                            answer--;
                        }
                    }

                    farm[r][c] += n + A[r][c];
                }
            }

            // 가을
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    for (int tree : farmTrees[r][c]) {
                        if (tree % 5 != 0 ) continue;

                        for (int i = 0; i < 8; i++) {
                            int nR = r + dr[i];
                            int nC = c + dc[i];

                            if (nR < 1 || nR > N || nC < 1 || nC > N) continue;
                            farmTrees[nR][nC].addFirst(1);
                            answer++;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}