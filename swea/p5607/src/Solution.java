import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int p = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        long[] fact = new long[1000001];
        fact[0] = 1;
        for (int i = 1; i <= 1000000; i++) {
            fact[i] = (fact[i - 1] * i) % p;
        }

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long numerator = fact[N];
            long denominator = (fact[R] * fact[N - R]) % p;
            long inverse = power(denominator, p - 2);

            sb.append("#").append(t + 1).append(" ").append((numerator * inverse) % p).append("\n");
        }

        System.out.println(sb);
    }

    static long power(long b, long e) {
        long result = 1;
        b %= p;

        while (e > 0) {
            if (e % 2 == 1) {
                result = (result * b) % p;
            }
            b = (b * b) % p;
            e /= 2;
        }

        return result;
    }
}
