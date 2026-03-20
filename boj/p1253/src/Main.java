import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  BAEKJOON 1253 좋다
  https://www.acmicpc.net/problem/1253
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(nums);

    int answer = 0;

    // i번째 수를 타겟으로 설정하고 '좋은 수'인지 판별
    for (int i = 0; i < N; i++) {
      int target = nums[i];
      int left = 0;
      int right = N - 1;

      while (left < right) {
        int sum = nums[left] + nums[right];

        if (sum == target) {
          // 조건: 두 수는 서로 다른 위치에 있어야 하며, 자기 자신(i)이 아니어야 함
          if (left != i && right != i) {
            answer++;
            break;
          } else if (left == i) {
            // 왼쪽 포인터가 자기 자신일 경우
            left++;
          } else {
            // 오른쪽 포인터가 자기 자신일 경우
            right--;
          }
        } else if (sum < target) {
          left++; // 합이 작으면 왼쪽 포인터를 올려 값을 키움
        } else {
          right--; // 합이 크면 오른쪽 포인터를 내려 값을 줄임
        }
      }
    }

    System.out.println(answer);
  }
}