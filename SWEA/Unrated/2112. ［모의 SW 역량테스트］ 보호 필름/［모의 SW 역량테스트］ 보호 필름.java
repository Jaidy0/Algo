
import java.io.*;
import java.util.*;

public class Solution {
    static int[][] film;
    static int D,W,K,minNum;
    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 0;

        while (t++ < T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); // 두께
            W = Integer.parseInt(st.nextToken()); // 가로
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            minNum = 0;

            for(int r = 0; r < D; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < W; c++) {
                    film[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            injection(0,0);

            System.out.println("#" + t + " " + minNum);
        }

    }

    static public void injection(int r, int c) {
        int injectNum = 0;

        if(checkFilm(film)) { // 이미 합격 필름이면 return
            return;
        }

        while(injectNum <= D) { // 인젝션 수를 늘려가면서, 조합 만들기
            injectNum++;
            if(injectSimulate(0, injectNum, 0)) {
                minNum = injectNum;
                return;
            };
        }

    }

    static public boolean injectSimulate(int row, int injectNum, int useNum) {

        if(useNum == injectNum) {
            return checkFilm(film);
        }

        if(row >= D) return false; // 더 이상 선택할 막이 없음

        // 현재 막을 선택하지 않는 경우
        if(injectSimulate(row + 1, injectNum, useNum)) {
            return true;
        }

        // 현재 막 선택, A로 바꿀 경우
        int[] original = film[row].clone();
        Arrays.fill(film[row],0);
        if(injectSimulate(row + 1, injectNum, useNum + 1)) {
            return true; // 성공 시 종료
        };

        // 현재 막 선택, B로 바꿀 경우

        Arrays.fill(film[row],1);
        if(injectSimulate(row + 1, injectNum, useNum + 1)) {
            return true; // 성공 시 종료
        };

        film[row] = original;
        return false;
    }



    static public boolean checkFilm(int[][] film) {
        for(int c = 0; c < W; c++) {
            boolean columnPass = false;

            // 현재 열에서 연속된 구간 찾기
            int count = 1;
            for(int r = 1; r < D; r++) {
                if(film[r][c] == film[r-1][c]) {
                    count++;
                } else {
                    count = 1; // 연속이 끊어지면 1부터 다시 시작
                }

                if(count >= K) {
                    columnPass = true;
                    break;
                }
            }

            if(!columnPass) return false;
        }
        return true;
    }


}
