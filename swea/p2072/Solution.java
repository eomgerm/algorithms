import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int[] nums = new int[10];
            for (int i = 0; i < 10; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            bw.write("#" + (t + 1) + " " + new Sol().solution(nums) + "\n");
        }

        bw.flush();
        bw.close();
    }
}

class Sol {
    int solution(int[] nums) {
        int answer = 0;

        for (int n : nums) {
            if (n % 2 != 0) {
                answer += n;
            }
        }

        return answer;
    }
}