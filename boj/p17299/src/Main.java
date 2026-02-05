import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17299 오등큰수
  https://www.acmicpc.net/problem/17299
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());

    int[] A = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
        A[i] = Integer.parseInt(st.nextToken());
    }

    int[] answer = new Solution().solution(A);

    for (int i : answer) {
        bw.write(i + " ");
    }

    bw.flush();
    bw.close();
  }
}

class Solution {
    int[] solution(int[] A) {
        int[] answer = new int[A.length];
        Arrays.fill(answer, -1);

        Map<Integer, Integer> freq = new HashMap<>();

        for (int i : A) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        Deque<Integer> s = new ArrayDeque<>();

        for (int i = 0; i < A.length; i++) {
            while (!s.isEmpty() && freq.get(A[s.peekLast()]) < freq.get(A[i])) {
                int j = s.removeLast();
                answer[j] = A[i];
            }

            s.addLast(i);
        }

        return answer;
    }
}