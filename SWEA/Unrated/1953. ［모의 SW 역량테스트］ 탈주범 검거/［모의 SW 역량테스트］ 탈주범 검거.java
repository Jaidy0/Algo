import java.util.*;
import java.io.*;
public class Solution {
    static int N,M,R,C,L;
    static int[][] map;
    static boolean[][] V;

    static int[][][] move =
            {{{-1,0,0},{1,0,1},{0,-1,2},{0,1,3}} // 상하좌우 0123
            ,{{-1,0,0},{1,0,1}} // 상하
            ,{{0,-1,2},{0,1,3}} // 좌우
            ,{{-1,0,0},{0,1,3}} // 상우
            ,{{1,0,1},{0,1,3}} // 하우
            ,{{1,0,1},{0,-1,2}} // 하좌
            ,{{-1,0,0},{0,-1,2}} // 상좌
            };


    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 세로 크기 N, 가로 크기 M, 멘홀뚜껑 R,C / 탈출 후 소요 시간 L

        int T = Integer.parseInt(br.readLine());
        int t = 0;
        StringTokenizer st;

        while(t++ < T) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 세로 크기
            M = Integer.parseInt(st.nextToken()); // 가로 크기
            R = Integer.parseInt(st.nextToken()); // 맨홀 세로 위치
            C = Integer.parseInt(st.nextToken()); // 멘홀 가로 위치
            L = Integer.parseInt(st.nextToken()); // 탈출 후 소요시간

            map = new int[N][M];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            V = new boolean[N][M];

            bfs();

            int ans = 0;
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < M; c++) {
                    if(V[r][c]) ans++;
                }
            }

            System.out.println("#" + t + " " + ans);

        }

    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{R,C,1});
        V[R][C] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];
            int l = tmp[2];

            if(l >= L) {
                continue;
            }

            if(map[r][c] == 0) continue;

            int[][] movArr = switchDir(map[r][c]);
            int nr,nc;

            for(int i = 0; i < movArr.length; i++) {
                nr = r + movArr[i][0];
                nc = c + movArr[i][1];

                if( nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] != 0) {
                    boolean nextCheck = nextDirCheck(nr,nc,movArr,i);

                    if(!V[nr][nc] && nextCheck) {
                        V[nr][nc] = true;
                        q.add(new int[]{nr,nc,l + 1});
                    }
                }
            }
        }

    }

    static boolean nextDirCheck(int nr, int nc, int[][] movArr, int idx) {
        int curDir = movArr[idx][2];

        // 현재의 방향 : 상,하,좌,우
        switch(curDir) {
            case 0 : // 상 => 하
                if(map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6) {
                    return true;
                }
                break;
            case 1 : // 하 => 상
                if(map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7) {
                    return true;
                }
                break;
            case 2 : // 좌 => 우
                if(map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5) {
                    return true;
                }
                break;
            case 3 : // 우 => 좌
                if(map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7) {
                    return true;
                }
                break;
        }
        return false;
    };

    static int[][] switchDir(int dir) {
        switch(dir) {
            case 1 : // 상하좌우
                return move[0];
            case 2 : // 상,하
                return move[1];
            case 3 : // 좌,우
                return move[2];
            case 4 : // 상,우
                return move[3];
            case 5 : // 하,우
                return move[4];
            case 6 : // 하,좌
                return move[5];
            case 7 : // 상,좌
                return move[6];
        }

        return move[0];
    }

}
