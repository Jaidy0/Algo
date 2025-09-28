import java.io.*;
import java.util.*;
public class Main {

    static int N, M;
    static int[][] lab;
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] ansV;
    static boolean[][][] V;
    static int[] dr = {-1,1,0,0}; 
    static int[] dc = {0,0,-1,1};

    public static void main (String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = new boolean[N][M][4];
        ansV = new boolean[N][M];
        lab = new int[N][M];
        for(int r = 0 ; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                lab[r][c] = Integer.parseInt(st.nextToken());
                if(lab[r][c] == 9) {
                    q.add(new int[]{r,c});
                }
            }
        }

        move();

        int ans = 0;

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                for(int k = 0; k < 4; k++) {
                    if(V[r][c][k]) ansV[r][c] = true;
                }
            }
        }

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(ansV[r][c]){
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

    public static void move() {

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];
            int dir = 0;
            ansV[r][c] = true;

            int curR, curC;
            curR = r;
            curC = c;

            // 0 상 / 1 하 / 2 좌 / 3 우
            for(int k = 0; k < 4; k++) {
                r = curR;
                c = curC;
                dir = k;
                while(true) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
                    if(nr >= N || nc >= M || nr < 0 || nc < 0) {
                        break;
                    }
                    if(V[nr][nc][dir]) break;
                    V[nr][nc][dir]= true;
                    switch(lab[nr][nc]) {
                        case 1 :
                            if(dir == 2) { // 좌 → 우
                                dir = 3;
                            } else if(dir == 3) { // 우 → 좌
                                dir = 2;
                            }
                            r = nr;
                            c = nc;
                            break;
                        case 2 :
                            if(dir == 0) {
                                dir = 1;
                            } else if(dir == 1) {
                                dir = 0;
                            }
                            r = nr;
                            c = nc;
                            break;
                        case 3 : // 상하좌우
                            if(dir == 0) { // 하상우
                                dir = 3;
                            } else if(dir == 1) {// 상하좌
                                dir = 2;
                            } else if(dir == 2) { // 우좌하
                                dir = 1;
                            } else { // 좌우상
                                dir = 0;
                            }
                            r = nr;
                            c = nc;
                            break;
                        case 4 :
                            if(dir == 0) { //하상좌
                                dir = 2;
                            } else if(dir == 1) {// 상하우
                                dir = 3;
                            } else if(dir == 2) { // 우좌상
                                dir = 0;
                            } else { // 좌우하
                                dir = 1;
                            }
                            r = nr;
                            c = nc;
                            break;
                        default:                             
                            r = nr;
                            c = nc;
                    }

                }

                }
            }

    }
}


