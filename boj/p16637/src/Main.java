import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 16637 괄호 추가하기
  https://www.acmicpc.net/problem/16637
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 코드를 작성하세요.
        int N = Integer.parseInt(br.readLine());
        String exp = br.readLine();

        bw.write(new Solution().solution(exp) + "");
        bw.flush();
        bw.close();

    }
}

class Solution {

    int answer = Integer.MIN_VALUE;
    List<Integer> nums = new ArrayList<>();
    List<Character> ops = new ArrayList<>();

    int solution(String exp) {
        nums = new ArrayList<>();
        ops = new ArrayList<>();

        char[] expArr = exp.toCharArray();
        for (int i = 0; i < exp.length(); i++) {
            if (i % 2 == 0) {
                nums.add(expArr[i] - '0');
            } else {
                ops.add(expArr[i]);
            }
        }

        solve(0, nums.get(0));

        return answer;
    }

    void solve(int opIdx, int result) {
        if (opIdx >= ops.size()) {
            answer = Math.max(answer, result);
            return;
        }

        int nextRes = calc(ops.get(opIdx), result, nums.get(opIdx + 1));
        solve(opIdx + 1, nextRes);

        if (opIdx + 1 < ops.size()) {
            int partRes = calc(ops.get(opIdx + 1), nums.get(opIdx + 1), nums.get(opIdx + 2));
            solve(opIdx + 2, calc(ops.get(opIdx), result, partRes));
        }
    }

    int calc(char op, int num1, int num2) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }

        return -1;
    }

}