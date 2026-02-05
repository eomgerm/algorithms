import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 10;

        for (int t = 0; t < T; t++) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < 8; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }

            int d = 1;
            while (true) {
                int v = queue.removeFirst();
                v = Math.max(v - d, 0);
                queue.addLast(v);
                d = d % 5 + 1;

                if (v == 0) {
                    break;
                }
            }

            sb.append("#").append(t+1).append(" ");
            while (!queue.isEmpty()) {
                sb.append(queue.removeFirst()).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
