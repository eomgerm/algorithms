import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, X, M;
    static int[] cages;
    static int[][] records;
    static int[] answer;
    static int answerSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            cages = new int[N];
            answer = null;
            answerSum = -1;

            records = new int[M][3];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                records[i][0] = Integer.parseInt(st.nextToken()) - 1;
                records[i][1] = Integer.parseInt(st.nextToken()) - 1;
                records[i][2] = Integer.parseInt(st.nextToken());
            }

            rPerm(0);

            sb.append("#").append(t + 1).append(" ");
            if (answer == null) sb.append(-1);
            else {
                for (int i : answer) {
                    sb.append(i).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void rPerm(int cnt) {
        if (cnt == N) {
            for (int[] r : records) {
                int sum = Arrays.stream(Arrays.copyOfRange(cages, r[0], r[1] + 1)).sum();
                if (sum != r[2]) {
                    return;
                }
            }

            int sum = Arrays.stream(cages).sum();
            if (sum > answerSum) {
                answer = cages.clone();
                answerSum = sum;
            } else if (sum == answerSum) {
                for (int i = 0; i < answer.length; i++) {
                    if (cages[i] != answer[i]) {
                        if (cages[i] < answer[i]) {
                            answer = cages.clone();
                        } else break;
                    }
                }
            }

            return;
        }

        for (int i = 0; i <= X; i++) {
            cages[cnt] = i;
            rPerm(cnt + 1);
        }
    }
}
