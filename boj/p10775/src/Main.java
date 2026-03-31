import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 10775 공항
  https://www.acmicpc.net/problem/10775
*/

public class Main {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 코드를 작성하세요.

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parents = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parents[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());

            int p = find(g);

            if (p == 0) {
                System.out.println(answer);
                return;
            }

            union(p, p - 1);
            answer++;
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (x != parents[x]) {
            parents[x] = find(parents[x]);
        }

        return parents[x];
    }

    static void union(int x, int y) {
        int rX = find(x);
        int rY = find(y);

        if (rX != rY) {
            parents[rX] = rY;
        }
    }
}