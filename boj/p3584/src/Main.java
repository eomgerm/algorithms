import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 3584 가장 가까운 공통 조상
  https://www.acmicpc.net/problem/3584
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 코드를 작성하세요.
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[][] edges = new int[N - 1][2];
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(new Solution().solution(N, edges, a, b) + "\n");
        }

        bw.flush();
        bw.close();
    }
}

class Solution {

    int solution(int N, int[][] edges, int a, int b) {
        // 1. Tree 구성
        List<Integer>[] tree = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        boolean[] hasParent = new boolean[N + 1];
        for (int[] edge : edges) {
            int A = edge[0];
            int B = edge[1];

            hasParent[B] = true;

            tree[A].add(B);
            tree[B].add(A);
        }

        // 문제 한정) 루트 노드 찾기
        int ROOT = 0;
        for (int i = 1; i <= N; i++) {
            if (!hasParent[i]) {
                ROOT = i;
                break;
            }
        }

        // 2. depth, parent 배열 채우기
        // 2-1. DFS 돌면서 depth, parent 초기화
        int MAX_DEPTH = (int) (Math.log(N) / Math.log(2)) + 1;
        int[] depth = new int[N + 1];
        int[][] parent = new int[MAX_DEPTH][N + 1];

        Deque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.addLast(new int[]{ROOT, 0});
        visited[ROOT] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            int node = cur[0];
            int d = cur[1];

            depth[node] = d;

            for (int child : tree[node]) {
                if (visited[child]) {
                    continue;
                }

                parent[0][child] = node;

                visited[child] = true;
                queue.addLast(new int[]{child, d + 1});
            }
        }

        // 2-2. parent 배열 완성
        for (int k = 1; k < MAX_DEPTH; k++) {
            for (int x = 1; x < N + 1; x++) {
                parent[k][x] = parent[k - 1][parent[k - 1][x]];
            }
        }

        // 3. 두 노드를 동일한 depth로 맞추기
        int u = depth[a] > depth[b] ? a : b;
        int v = depth[a] > depth[b] ? b : a;

        int diff = depth[u] - depth[v];
        for (int k = 0; k < MAX_DEPTH; k++) {
            if ((diff & (1 << k)) != 0) {
                u = parent[k][u];
            }
        }

        // 이미 둘이 같은 노드라면 조기 종료
        if (u == v) {
            return u;
        }

        // 4. LCA 찾기
        for (int k = MAX_DEPTH - 1; k >= 0; k--) {
            if (parent[k][u] != parent[k][v]) {
                u = parent[k][u];
                v = parent[k][v];
            }
        }

        return parent[0][u];
    }
}