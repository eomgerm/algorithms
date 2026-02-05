import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17298 오큰수
  https://www.acmicpc.net/problem/17298
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] A = new int[N];
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

        ArrayDeque<Integer> s = new ArrayDeque<>();
        Arrays.fill(answer, -1);

        for (int i = 0; i < A.length; i++) {
            while (!s.isEmpty() && A[s.peekLast()] < A[i]) {
                int  j = s.removeLast();
                answer[j] = A[i];
            }

            s.addLast(i);
        }

        return answer;
    }
}