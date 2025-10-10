import java.io.*;
import java.util.*;

public class Main {
    static class Horse {
        int no, r, c, dir;

        Horse(int no, int r, int c, int dir) {
            this.no = no;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static int N,K;
    static int[][] board;
    static ArrayList<Horse>[][] recordHorse;
    static ArrayList<Horse> horseList;
    static int[] dr = {0,0,-1,1}; // 우좌상하
    static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        recordHorse = new ArrayList[N][N];
        horseList = new ArrayList<>();

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                recordHorse[r][c] = new ArrayList<>();
            }
        }

        int hr,hc,hDir;
        for(int k = 0; k < K; k++ ) {
            st = new StringTokenizer(br.readLine());
            hr = Integer.parseInt(st.nextToken()) - 1;
            hc = Integer.parseInt(st.nextToken()) - 1;
            hDir = Integer.parseInt(st.nextToken()) - 1;

//            recordHorse[hr][hc].add(new Horse(k,hr,hc,hDir));
//            horseList.add(new Horse(k,hr,hc,hDir));

            Horse horse = new Horse(k, hr, hc, hDir);
            recordHorse[hr][hc].add(horse);
            horseList.add(horse);  // 같은 객체 참조
        }

        // 말 이동 시키기
        // - 턴 세기
        // - 칸 색상별로 이동 로직 달리하기
        int t = 0;
        boolean gameEnd = false;
        while(true) {
            t++; // 초기에 하나, 마지막에 하나 상관 없나?
            if(t > 1000) break;

            // 게임 종료 조건 체크
            if (moveHorses()) {
                gameEnd = true;
                break;
            }
        }

        if(t > 1000 || !gameEnd) {
            System.out.println(-1);
        } else {
            System.out.println(t);
        }

    }

    static boolean checkEnd() {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(recordHorse[r][c].size() >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean moveHorses() {

        for(Horse h : horseList) {
            int nr = h.r + dr[h.dir];
            int nc = h.c + dc[h.dir];

            // 피랑이거나 벽일 경우, 방향 변경 후 한칸 더 이동
            if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 2) {
                // 방향 반대로 바꾸고 한칸 더 이동 / 우좌상하 1234
                if (h.dir == 0) {
                    h.dir = 1;
                } else if (h.dir == 1) {
                    h.dir = 0;
                } else if (h.dir == 2) {
                    h.dir = 3;
                } else if (h.dir == 3) {
                    h.dir = 2;
                }
                // horseRecord Data 도 갱신
                nr = h.r + dr[h.dir];
                nc = h.c + dc[h.dir];

                // 또 범위 밖이거나 파란색이면 이동 안 함
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 2) {
                    continue;
                }
            }

            // 새로운 칸으로 말 이동
            move(h.no, nr, nc, board[nr][nc] == 1);

            // 이동 후 즉시 종료 조건 체크
            if (recordHorse[nr][nc].size() >= 4) {
                return true;
            }
        }
        return false;
    }

    static boolean checkIsBlue( int nr, int nc) {
        if(board[nr][nc] == 3) return true;
        return false;
    }

    // 말 번호, 새롭게 갈 위치와 해당 위치가 Red 인지여부만 알려주면 말을 옮기는 로직
    static void move(int horseNo, int nr, int nc, boolean isRed) {
        Horse h = horseList.get(horseNo);
        int r = h.r;
        int c = h.c;

        // 현재 해당 칸에서 idx 찾기
        // int idx = recordHorse[r][c].indexOf(horseNum);
        int findIndex = -1;
        for(int i = 0; i < recordHorse[r][c].size(); i++) {
            if(recordHorse[r][c].get(i).no == horseNo) {
                findIndex = i;
//                recordHorse[r][c].get(i).dir = horse.dir;
                break;
            }
        }

        // 혹시나..
        if(findIndex == -1) return;

        // 움직일 말들을 담을 임시 배열
        List<Horse> movingHorses = new ArrayList<>();

        // 자신보다 뒤쪽 idx의 배열을 담음
        for(int i = findIndex; i < recordHorse[r][c].size(); i++) {
            movingHorses.add(recordHorse[r][c].get(i));
        }

        // 기존 위치의 말들을 삭제
        for(int i = recordHorse[r][c].size() - 1; i >= findIndex; i--) {
            recordHorse[r][c].remove(i);
        }

        // 빨간 칸일 경우, 옮길 말들의 순서를 꺼꾸로
        if(isRed) {
            Collections.reverse(movingHorses);
        }

        // 옮긴 말들의 정보를 갱신
        for(Horse horse  : movingHorses) {
            recordHorse[nr][nc].add(horse);
            horse.r = nr;
            horse.c = nc;
        }

    }
}
