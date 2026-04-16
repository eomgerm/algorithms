import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    SWEA 2477번 차량 정비소
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int[] recTime = new int[N];
            int[] repTime = new int[M];
            int[] customers = new int[K];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                recTime[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                repTime[i] = Integer.parseInt(st.nextToken());
            }

            PriorityQueue<int[]> recQueue = new PriorityQueue<>(Comparator.<int[]>comparingInt(c -> c[1]).thenComparingInt(c -> c[0]));
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                recQueue.add(new int[]{i, Integer.parseInt(st.nextToken())});
            }

            PriorityQueue<int[]> repQueue = new PriorityQueue<>(Comparator.<int[]>comparingInt(c -> c[1]).thenComparingInt(c -> c[2]));

            int[][] receptions = new int[N][];
            int[][] repairs = new int[M][];

            int elapsed = 0;
            int done = 0;

            int answer = 0;
            while (done != K) {
                for (int i = 0; i < N; i++) {
                    if (receptions[i] != null) {
                        receptions[i][1]--;

                        if (receptions[i][1] == 0) {
                            repQueue.add(new int[]{receptions[i][0], elapsed, i});
                            receptions[i] = null;
                        }
                    }
                }

                for (int i = 0; i < M; i++) {
                    if (repairs[i] != null) {
                        repairs[i][1]--;

                        if (repairs[i][1] == 0) {
                            if (repairs[i][2] + 1 == A && i + 1 == B) {
                                answer += repairs[i][0];
                            }

                            repairs[i] = null;
                            done++;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    if (receptions[i] == null) {
                        if (!recQueue.isEmpty() && recQueue.peek()[1] <= elapsed) {
                            int[] customer = recQueue.poll();
                            receptions[i] = new int[]{customer[0], recTime[i]};
                        }
                    }
                }

                for (int i = 0; i < M; i++) {
                    if (repairs[i] == null) {
                        if (!repQueue.isEmpty()) {
                            int[] customer = repQueue.poll();
                            repairs[i] = new int[]{customer[0], repTime[i], customer[2]};
                        }
                    }
                }

                elapsed++;
            }

            sb.append("#").append(t + 1).append(" ").append(answer == 0 ? -1 : answer).append("\n");
        }

        System.out.println(sb);
    }
}
