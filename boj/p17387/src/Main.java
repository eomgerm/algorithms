import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17387 선분 교차 2
  https://www.acmicpc.net/problem/17387
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        StringTokenizer st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken()), y1 = Long.parseLong(st.nextToken()), x2 = Long.parseLong(
            st.nextToken()), y2 = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken()), y3 = Long.parseLong(st.nextToken()), x4 = Long.parseLong(
            st.nextToken()), y4 = Long.parseLong(st.nextToken());

        System.out.println(isIntersect(x1, y1, x2, y2, x3, y3, x4, y4) ? 1 : 0);
    }

    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long s = x1 * y2 + x2 * y3 + x3 * y1 - (x2 * y1 + x3 * y2 + x1 * y3);

        return Long.compare(s, 0);
    }

    static boolean isIntersect(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        int p1p2 = ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4);
        int p3p4 = ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2);

        if (p1p2 == 0 && p3p4 == 0) {
            return Math.min(x1, x2) <= Math.max(x3, x4) &&
                Math.min(x3, x4) <= Math.max(x1, x2) &&
                Math.min(y1, y2) <= Math.max(y3, y4) &&
                Math.min(y3, y4) <= Math.max(y1, y2);
        }

        return p1p2 <= 0 && p3p4 <= 0;
    }
}