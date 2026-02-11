import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

//        int T = Integer.parseInt(br.readLine());
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
//            int N = Integer.parseInt(br.readLine());
            int N = sc.nextInt();

            int[] mts = new int[N];4796
//            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                mts[i] = sc.nextInt();
            }

            int asc = 0;
            int desc = 0;
            int answer = 0;
            for (int i = 1; i < N; i++) {
                if (mts[i - 1] < mts[i]) {
                    if (desc > 0) {
                        answer += asc * desc;
                        asc = 0;
                        desc = 0;
                    }
                    asc++;
                } else {
                    desc++;
                }
            }
            answer += asc * desc;

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

}
