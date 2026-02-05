import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 14891 톱니바퀴
  https://www.acmicpc.net/problem/14891
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 코드를 작성하세요.

        int[][] gears = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String[] g = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                gears[i][j] = Integer.parseInt(g[j]);
            }
        }

        int K = Integer.parseInt(br.readLine());
        int[][] cmds = new int[K][2];
        for (int i = 0; i < K; i++) {
            String[] c = br.readLine().split(" ");
            cmds[i][0] = Integer.parseInt(c[0]);
            cmds[i][1] = Integer.parseInt(c[1]);
        }

        bw.write(new Solution().solution(gears, cmds) + "");
        bw.flush();
        bw.close();
    }
}

class Solution {

    int solution(int[][] g, int[][] cmds) {
        List<List<Integer>> gears = new ArrayList<>();
        gears.add(new ArrayList<>(List.of(0, 0, 0, 0, 0, 0, 0, 0))); // padding
        for (int i = 0; i < 4; i++) {
            List<Integer> gear = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                gear.add(g[i][j]);
            }
            gears.add(gear);
        }

        for (int[] cmd : cmds) {
            int target = cmd[0];
            int direction = cmd[1];

            System.out.println("--- " + target + " " + direction + " ---" );
            System.out.println("Before");
            for (int i = 1; i < 5; i++) {
                System.out.println(gears.get(i));
            }

            int[] rotate = new int[5]; // padding
            rotate[target] = direction;

            List<Integer> prevGear = gears.get(target);
            for (int i = target - 1; i >= 0; i--) {
                List<Integer> curGear = gears.get(i);
                int curP = curGear.get(2);
                int prevP = prevGear.get(5);

                if (curP != prevP) {
                    rotate[i] = -rotate[i + 1];
                    prevGear = curGear;
                } else {
                    break;
                }
            }

            prevGear = gears.get(target);
            for (int i = target + 1; i < 5; i++) {
                List<Integer> curGear = gears.get(i);
                int curP = curGear.get(5);
                int prevP = prevGear.get(2);

                if (curP != prevP) {
                    rotate[i] = -rotate[i - 1];
                    prevGear = curGear;
                } else {
                    break;
                }
            }

            for (int i = 1; i < 5; i++) {
                Collections.rotate(gears.get(i), rotate[i]);
            }

            System.out.println("After");
            for (int i = 1; i < 5; i++) {
                System.out.println(gears.get(i));
            }
        }

        int answer = 0;
        for (int i = 1; i < 5; i++) {
            answer += gears.get(i).get(0) * Math.pow(2, i - 1);
        }

        return answer;
    }
}