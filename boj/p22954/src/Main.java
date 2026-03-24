import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 22954 그래프 트리 분할
  https://www.acmicpc.net/problem/22954
*/

public class Main {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 코드를 작성하세요.

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if (N == 1 || N == 2) {
            System.out.println(-1);
            return;
        }

        List<int[]>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        parents = new int[N + 1];
        for(int i = 1; i <= N; i++) parents[i] = i;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, i});
            graph[b].add(new int[] {a, i});

            union(a, b);
        }

        Set<Integer> roots = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            roots.add(find(i));
        }

        if (roots.size() == 1) {
            int start = 1;

            List<Integer> selected = new ArrayList<>();

            boolean[] v = new boolean[N + 1];
            Deque<Integer> stack = new ArrayDeque<>();

            v[start] = true;
            stack.addLast(start);

            int[] degree = new int[N + 1];
            while (!stack.isEmpty()) {
                int cur = stack.removeLast();

                for (int[] e : graph[cur]) {
                    int next = e[0];
                    int eIdx = e[1];

                    if (v[next]) continue;
                    degree[cur]++;
                    degree[next]++;
                    selected.add(eIdx);
                    v[next] = true;
                    stack.addLast(next);
                }
            }

            int leaf = -1;
            for (int i = 1; i <= N; i++) {
                if (degree[i] == 1) {
                    leaf = i;
                    break;
                }
            }

            int targetE = -1;
            for (int[] e : graph[leaf]) {
                int eIdx = e[1];
                if (selected.contains(eIdx)) {
                    targetE = eIdx;
                    break;
                }
            }

            sb.append(1).append(" ").append(N - 1).append("\n") // 1
                .append(leaf).append("\n") // 2
                .append("\n"); // 3
            for (int i = 1; i <= N; i++) {
                if (i == leaf) continue;
                sb.append(i).append(" ");
            }
            sb.append("\n"); // 4
            for (int i : selected) {
                if (i == targetE) continue;
                sb.append(i).append(" ");
            }

            System.out.println(sb);
        } else if (roots.size() == 2) {
            List<Integer> n1SelectedEdges = new ArrayList<>();
            List<Integer> n2SelectedEdges = new ArrayList<>();
            List<Integer> n1SelectedNodes = new ArrayList<>();
            List<Integer> n2SelectedNodes = new ArrayList<>();

            int cnt = 0;
            for (int start : roots) {
                boolean[] v = new boolean[N + 1];
                Deque<Integer> stack = new ArrayDeque<>();

                v[start] = true;
                stack.addLast(start);

                while (!stack.isEmpty()) {
                    int cur = stack.removeLast();
                    if (cnt == 0) {
                        n1SelectedNodes.add(cur);
                    } else {
                        n2SelectedNodes.add(cur);
                    }

                    for (int[] e : graph[cur]) {
                        int next = e[0];
                        int eIdx = e[1];

                        if (v[next]) continue;
                        if (cnt == 0) {
                            n1SelectedEdges.add(eIdx);
                        } else {
                            n2SelectedEdges.add(eIdx);
                        }
                        v[next] = true;
                        stack.addLast(next);
                    }
                }
                cnt++;
            }
            if (n1SelectedNodes.size() == n2SelectedNodes.size()) {
                System.out.println(-1);
                return;
            }

            sb.append(n1SelectedNodes.size()).append(" ").append(n2SelectedNodes.size()).append("\n"); // 1
            for (int i : n1SelectedNodes) {
                sb.append(i).append(" ");
            }
            sb.append("\n"); // 2
            for (int i : n1SelectedEdges) {
                sb.append(i).append(" ");
            }
            sb.append("\n"); // 3
            for (int i : n2SelectedNodes) {
                sb.append(i).append(" ");
            }
            sb.append("\n"); // 4
            for (int i : n2SelectedEdges) {
                sb.append(i).append(" ");
            }
            sb.append("\n"); // 5

            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    static int find(int x) {
        if (parents[x] == x) {
            return parents[x];
        }

        parents[x] = find(parents[x]);

        return parents[x];
    }

    static void union(int x, int y) {
        int rX = find(x);
        int rY = find(y);

        parents[rX] = rY;
    }
}