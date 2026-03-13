import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            char[] nums = br.readLine().toCharArray();
            List<Character> numCharList = new ArrayList<>();
            for (char c : nums) {
                numCharList.add(c);
            }

            Set<Integer> numSet = new HashSet<>();
            for (int i = 0; i < N / 4; i++) {
                Collections.rotate(numCharList, 1);

                for (int j = 0; j < N; j += N / 4) {
                    String num = "";
                    for (int k = 0; k < N / 4; k++) {
                        num += numCharList.get(j + k);
                    }
                    numSet.add(Integer.parseInt(num, 16));
                }
            }
            List<Integer> numList = new ArrayList<>(numSet);
            numList.sort(Comparator.reverseOrder());

            sb.append("#").append(t + 1).append(" ").append(numList.get(K - 1)).append("\n");
        }

        System.out.println(sb);
    }

}
