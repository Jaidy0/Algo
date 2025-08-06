import java.io.*;
import java.util.*;

class Atom {
    int x, y, dir, energy;

    Atom(int x, int y, int dir, int energy) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.energy = energy;
    }
}


public class Solution {
    static Queue<Atom> atoms;
    static int N;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static int crachScore;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 0;

        while (t++ < T) {

            N = Integer.parseInt(br.readLine());
            atoms = new LinkedList<>();
            crachScore = 0;
            map = new int[4001][4001];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                atoms.add(new Atom(x, y, dir, e));
                map[x][y] = e;
            }

            bfs();
            System.out.println("#" + t + " " + crachScore);
        }


    }

    public static void bfs() {
        // 모두 이동 시킴

        while(!atoms.isEmpty()) {
            Atom a = atoms.poll();
            if(map[a.x][a.y] != a.energy) { // 다른 원자와 충돌 확인
                crachScore += map[a.x][a.y];
                map[a.x][a.y] = 0;
                continue;
            }

            int nx = a.x + dx[a.dir];
            int ny = a.y + dy[a.dir];

            if(nx >= 0 && ny >= 0 && nx <= 4000 && ny <= 4000) {
                if (map[nx][ny] == 0) {
                    map[nx][ny] = a.energy;
                    atoms.add(new Atom(nx, ny, a.dir, a.energy));
                } else {
                    map[nx][ny] += a.energy;
                }
            }

            map[a.x][a.y] = 0;

        }

    }


}