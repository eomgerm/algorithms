import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 4195 친구 네트워크
  https://www.acmicpc.net/problem/4195
*/

public class Main {

    static Map<String, String> parents = new HashMap<>();
    static Map<String, Integer> netSizes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 코드를 작성하세요.

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            parents = new HashMap<>();
            netSizes = new HashMap<>();

            int F = Integer.parseInt(br.readLine());
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                parents.putIfAbsent(a, a);
                netSizes.putIfAbsent(a, 1);
                parents.putIfAbsent(b, b);
                netSizes.putIfAbsent(b, 1);

                union(a, b);

                sb.append(netSizes.get(find(a))).append("\n");
            }
        }

        System.out.println(sb);
    }

    static String find(String x) {
        if (!parents.get(x).equals(x)) {
            parents.replace(x, find(parents.get(x)));
        }

        return parents.get(x);
    }

    static void union(String x, String y) {
        String xR = find(x);
        String yR = find(y);

        if (!xR.equals(yR)) {
            parents.replace(xR, yR);
            netSizes.replace(yR, netSizes.get(yR) + netSizes.get(xR));
        }
    }
}