import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainFenwick {
    static long[] A;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 코드를 작성하세요.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        A = new long[N + 1];
        tree = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(br.readLine());
            update(i, A[i]);
        }

        for (int i = 0; i < M + K ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                update(b, c - A[b]);
                A[b] = c;
            } else {
                int c = Integer.parseInt(st.nextToken());
                long sum = sum(c) - sum(b - 1);
                sb.append(sum).append("\n");
            }
        }

        System.out.println(sb);
    }

    static long sum(int i) {
        long sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & -i);
        }
        return sum;
    }

    static void update(int i, long diff) {
        while (i < tree.length) {
            tree[i] += diff;
            i += (i & -i);
        }
    }
}
