import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2143 두 배열의 합
  https://www.acmicpc.net/problem/2143
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        long T = Long.parseLong(br.readLine());
        int n = Integer.parseInt(br.readLine());

        long[] A = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        long[] B = new long[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        long[] sumA = new long[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            sumA[idx] = A[i];
            idx++;
            for (int j = i + 1; j < n; j++) {
                sumA[idx] = sumA[idx - 1] + A[j];
                idx++;
            }
        }

        long[] sumB = new long[m * (m + 1) / 2];
        idx = 0;
        for (int i = 0; i < m; i++) {
            sumB[idx] = B[i];
            idx++;
            for (int j = i + 1; j < m; j++) {
                sumB[idx] = sumB[idx - 1] + B[j];
                idx++;
            }
        }

        Arrays.sort(sumB);

        long answer = 0;
        for (long s : sumA) {
            long t = T - s;

            int left = 0;
            int right = sumB.length;

            // lowerBound
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (sumB[mid] >= t) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int lb = left;

            left = 0;
            right = sumB.length;

            // upper bound
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (sumB[mid] > t) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int ub = left;

            answer += ub - lb;
        }

        System.out.println(answer);
    }
}