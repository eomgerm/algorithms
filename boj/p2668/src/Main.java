import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2668 숫자고르기
  https://www.acmicpc.net/problem/2668
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 코드를 작성하세요.
        int N = Integer.parseInt(br.readLine());

        int[] pairs = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            pairs[i] = Integer.parseInt(br.readLine());
        }

        int[] answer = new Solution().solution(N, pairs);
        for (int i : answer) {
            bw.write(i + "\n");
        }
        bw.flush();
        bw.close();
    }
}

class Solution {

    int[] solution(int N, int[] pairs) {
        List<Integer> selected = new ArrayList<>();

        for (int root = 1; root <= N; root++) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.addLast(root);
            boolean[] visited = new boolean[N + 1];
            visited[root] = true;

            while (!stack.isEmpty()) {
                int cur = stack.removeLast();

                int next = pairs[cur];

                if (next == root) {
                    selected.add(next);
                }

                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                stack.addLast(next);
            }
        }

        int[] answer = new int[selected.size() + 1];
        answer[0] = selected.size();
        selected.sort(Comparator.naturalOrder());
        for (int i = 0; i < selected.size(); i++) {
            answer[i + 1] = selected.get(i);
        }

        return answer;
    }
}