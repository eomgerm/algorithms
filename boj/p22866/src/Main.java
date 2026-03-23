import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 22866 탑 보기
  https://www.acmicpc.net/problem/22866
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] towers = new int[N];
        for (int i = 0; i < N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[N];
        int[] nearest = new int[N];

        // 현재 탑에서 왼쪽을 봤을 때
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && towers[stack.peekLast()] <= towers[i]) {
                stack.removeLast();
            }
            count[i] = stack.size();
            if (!stack.isEmpty()) {
                nearest[i] = stack.peekLast();
            } else {
                nearest[i] = -1;
            }
            stack.addLast(i);
        }

        stack.clear();

        // 현재 탑에서 오른쪽을 봤을 때
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && towers[stack.peekLast()] <= towers[i]) {
                stack.removeLast();
            }
            count[i] += stack.size();
            if (!stack.isEmpty()) {
                if (nearest[i] != -1) {
                    if (i - nearest[i] > stack.peekLast() - i) {
                        nearest[i] = stack.peekLast();
                    }
                } else {
                    nearest[i] = stack.peekLast();
                }
            }

            stack.addLast(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(count[i]);
            if (count[i] != 0) {
                sb.append(" ").append(nearest[i] + 1);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}