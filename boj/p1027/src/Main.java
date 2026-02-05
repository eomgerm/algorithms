import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1027 고층 건물
  https://www.acmicpc.net/problem/1027
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 코드를 작성하세요.
        int N = Integer.parseInt(br.readLine());

        int[] buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(new Solution().solution(buildings) + "");
        bw.flush();
        bw.close();
    }
}

class Solution {

    int solution(int[] buildings) {
        int answer = 0;
        int N = buildings.length;

        return answer;
    }
}