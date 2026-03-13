import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int  T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            for (int r = 0; r < N; r++) {
                int cnt = 0;
                boolean ok = true;
                boolean desc = false;
                for (int c = 0; c < N - 1; c++) {
                    cnt++;

                    if (map[r][c] < map[r][c + 1]) {
                        if (map[r][c + 1] - map[r][c] == 1) {
                            if (desc) {
                                if (cnt >= X * 2) {
                                    desc = false;
                                    cnt = 0;
                                } else {
                                    ok = false;
                                    break;
                                }
                            } else {
                                if (cnt < X) {
                                    ok = false;
                                    break;
                                }

                                cnt = 0;
                            }
                        } else {
                            ok = false;
                            break;
                        }
                    } else if (map[r][c] > map[r][c + 1]) {
                        if (map[r][c] - map[r][c + 1] == 1) {
                            if (desc && cnt < X) {
                                ok = false;
                                break;
                            }

                            desc = true;
                            cnt = 0;
                        } else {
                            ok = false;
                            break;
                        }
                    }
                }
                cnt++;
                if (desc && cnt < X) {
                    ok = false;
                }

                if (ok) {
                    answer++;
                }
            }

            for (int c = 0; c < N; c++) {
                int cnt = 0;
                boolean ok = true;
                boolean desc = false;
                for (int r = 0; r < N - 1; r++) {
                    cnt++;

                    if (map[r][c] < map[r + 1][c]) {
                        if (map[r + 1][c] - map[r][c] == 1) {
                            if (desc) {
                                if (cnt >= X * 2) {
                                    desc = false;
                                    cnt = 0;
                                } else {
                                    ok = false;
                                    break;
                                }
                            } else {
                                if (cnt < X) {
                                    ok = false;
                                    break;
                                }

                                cnt = 0;
                            }
                        } else {
                            ok = false;
                            break;
                        }
                    } else if (map[r][c] > map[r + 1][c]) {
                        if (map[r][c] - map[r + 1][c] == 1) {
                            if (desc && cnt < X) {
                                ok = false;
                                break;
                            }

                            desc = true;
                            cnt = 0;
                        } else {
                            ok = false;
                            break;
                        }
                    }
                }
                cnt++;
                if (desc && cnt < X) {
                    ok = false;
                }

                if (ok) {
                    answer++;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
