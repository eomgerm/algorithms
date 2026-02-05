import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1715 카드 정렬하기
  https://www.acmicpc.net/problem/1715
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.

      int N = Integer.parseInt(br.readLine());

      int[] decks = new int[N];
      for (int i = 0; i < N; i++) {
          decks[i] = Integer.parseInt(br.readLine());
      }

      bw.write(new Solution().solution(decks) + "");
      bw.flush();
      bw.close();
  }
}

class Solution {
    int solution(int[] decks) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : decks) {
            pq.add(i);
        }
        
        while (pq.size() > 1) {
            int A = pq.poll();
            int B = pq.poll();

            answer += A + B;

            pq.add(A + B);
        }

        return answer;
    }
}