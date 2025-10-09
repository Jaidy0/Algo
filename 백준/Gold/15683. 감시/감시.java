import java.util.*;
import java.io.*;



public class Main {
    static class CCTV {
        int r, c, type;
        CCTV(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static int N,M, minBlindSpot;
    static int[][] office;
    static boolean[][] V;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][][] dirInfo = {{{0},{1},{2},{3}}, {{0,1},{2,3}}, {{0,3},{1,3},{1,2},{0,2}}, {{0,1,3},{3,1,2},{0,2,1},{2,0,3}},{{0,1,2,3}}};
    static ArrayList<CCTV> cctvList = new ArrayList<>();

    // [카메라번호][경우의 수][방향들]
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N][M];
        V = new boolean[N][M];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                office[r][c] = Integer.parseInt(st.nextToken());

                if(office[r][c] >= 1 && office[r][c] <= 5) {
                    cctvList.add(new CCTV(r,c,office[r][c]));
//                    V[r][c] = true;
                }

                if(office[r][c] == 6) {
                    V[r][c] = true;
                }
            }
        } // 입력

        minBlindSpot = Integer.MAX_VALUE;
        combi(0);

        System.out.println(minBlindSpot);


    }

    static void combi(int idx) {

        if(idx == cctvList.size()) {
//            for(int i = 0; i < V.length; i++) {
//                System.out.println(Arrays.toString(V[i]));
//            }
            // 여기서 현재의 사각지대 체크
            int tmp = checkBlind();
            minBlindSpot = Math.min(minBlindSpot, tmp);
            return;
        }

        CCTV cctv = cctvList.get(idx);
        int camType = cctv.type - 1;
        for(int dirIdx = 0; dirIdx < dirInfo[camType].length; dirIdx++) {
            boolean[][] backup = new boolean[N][M];
            for(int i = 0; i < N; i++) {
                backup[i] = V[i].clone();
            }
            // 현재 설정된 카메라 방향의 하위 방향들 탐색
            checkCamDir(cctv, dirIdx, true);

            combi(idx+1);

            // 복구
            for(int i = 0; i < N; i++) {
                V[i] = backup[i].clone();
            }

            // 다시 되돌리기
//            checkCamDir(cctv, dirIdx, false);
        }

    }

    static int checkBlind() {
        int cntBlind = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(office[r][c] == 0 && !V[r][c] ) {
                    cntBlind++;
                }
            }
        }
        return cntBlind;
    }

    // 특정 카메라의 특정 방향으로 화면을 확인하는 메서드
    static void checkCamDir (CCTV cctv, int dirIdx, boolean isCheck) {
        int camNo = cctv.type - 1;
        int camR, camC;
        for(int k = 0; k < dirInfo[camNo][dirIdx].length; k++) {
            camR = cctv.r;
            camC = cctv.c;
            while(true) {
                int nr = camR + dr[dirInfo[camNo][dirIdx][k]];
                int nc = camC + dc[dirInfo[camNo][dirIdx][k]];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) break; // 범위를 벗어남
                if(office[nr][nc] == 6) break; // 벽을 만남

                V[nr][nc] = isCheck;
                camR = nr;
                camC = nc;
            }

        }
    }
}
