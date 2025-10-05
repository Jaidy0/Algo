import java.util.*;
import java.io.*;

public class Main {
    static int N,M,R,AR,AC,GR,GC;
    static String ADir;
    static int[][] map;
    static boolean[][] V;

    // 동서남북
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int AScore = 0;
        V = new boolean[N][M];
        int t = 0;
        while(t++ < R) {
            st = new StringTokenizer(br.readLine());
            AR = Integer.parseInt(st.nextToken()) - 1;
            AC = Integer.parseInt(st.nextToken()) - 1;
            ADir = st.nextToken();
            st = new StringTokenizer(br.readLine());
            GR = Integer.parseInt(st.nextToken()) - 1;
            GC = Integer.parseInt(st.nextToken()) - 1;

            // 공격자 행동
            if(!V[AR][AC]){ // 도미노가 넘어지지 않은 상태
                int curH = map[AR][AC];
                int cnt = curH - 1;
                V[AR][AC] = true;
                int dir = checkDir(ADir);
                AScore++;
                for(int i = 0; i < cnt; i++){
                    int nr = AR + dr[dir];
                    int nc = AC + dc[dir];

                    if(nr >= N || nc >= M || nr < 0 || nc < 0) break;
                    // 1. 먼저 이동
                    AR = nr;
                    AC = nc;
                    if(!V[AR][AC]) {
                        AScore++;
                        V[AR][AC] = true;  // 2. 넘어뜨림

                        if(map[AR][AC]-1 > cnt-i-1){
                            i = -1;
                            cnt = map[AR][AC] - 1;
                        }
                    }

                }
            }

            // 수비자 행동
            if(V[GR][GC]) { // 넘어진 상태 true;
                V[GR][GC] = false; // 세움
            }
        }


        System.out.println(AScore);
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(V[r][c]){
                    System.out.print("F");
                } else {
                    System.out.print("S");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static int checkDir(String ADir) {
        switch(ADir) {
            case "E":
                return 0;
            case "W" :
                return 1;
            case "S":
                return 2;
            case "N":
                return 3;
        }
        return -1;
    }

}
