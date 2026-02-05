import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 15685 드래곤 커브
  https://www.acmicpc.net/problem/15685
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
      int N = Integer.parseInt(br.readLine());

      int[][] dragonCurves = new int[N][4];
      for (int[] c : dragonCurves) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          c[0] = Integer.parseInt(st.nextToken());
          c[1] = Integer.parseInt(st.nextToken());
          c[2] = Integer.parseInt(st.nextToken());
          c[3] = Integer.parseInt(st.nextToken());
      }

      bw.write(new Solution().solution(dragonCurves) + "");
      bw.flush();
      bw.close();
  }
}

class Solution {
    int solution(int[][] dragonCurves) {
        Set<Point> allPoints = new HashSet<>();

        for (int[] curve : dragonCurves) {
            int startX = curve[0];
            int startY = curve[1];
            int direction = curve[2];
            int gen = curve[3];

            allPoints.addAll(dragonCurve(startX, startY, direction, gen));
        }

        int answer = 0;
        for (Point p : allPoints) {
            if (allPoints.containsAll(List.of(
                new Point(p.x + 1, p.y),
                new Point(p.x, p.y + 1),
                new Point(p.x + 1, p.y + 1)
            ))) {
                answer++;
            }
        }

        return answer;
    }

    Set<Point> dragonCurve(int x, int y, int dir, int gen) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        List<Integer> directions = new ArrayList<>();
        directions.add(dir);

        for (int i = 0; i < gen; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        Set<Point> points = new HashSet<>();
        points.add(new Point(x, y));

        int nx = x;
        int ny = y;
        for (int d : directions) {
            nx += dx[d];
            ny += dy[d];
            points.add(new Point(nx, ny));
        }

        return points;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}