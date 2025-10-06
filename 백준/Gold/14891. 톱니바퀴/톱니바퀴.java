import java.io.*;
import java.util.*;
public class Main {
    static LinkedList<Integer>[] gears;
    public static void main(String[] args) throws IOException{
      

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gears = new LinkedList[4];

        for(int i = 0; i < 4; i++) {
            gears[i] = new LinkedList<>();
            String tmp = br.readLine();
            for(int j = 0; j < 8; j++) {
                gears[i].add(tmp.charAt(j) - '0');
            }
        }

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int gearNo, dir;
        int[] rotations ;
        // true 시계 / false 반시계
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            gearNo = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken());
            // 현재 기어 돌리기
            rotations = new int[4];
            rotations[gearNo] = dir;

            for(int i = gearNo; i < 3; i++) {
                if(gears[i].get(2) != gears[i+1].get(6)) {
                    rotations[i+1] = -rotations[i];
                } else {
                    break;
                }
            }

            for(int i = gearNo; i > 0 ; i--) {
                if(gears[i].get(6) != gears[i-1].get(2)) {
                    rotations[i-1] = -rotations[i];
                } else {
                    break;
                }
            }


            for(int i = 0; i < 4; i++) {
                if(rotations[i] == 1) { // 시계방향일경우
                    rotate(i);
                } else if(rotations[i] == -1) { // 반시계
                    rotateReverse(i);
                } // 아무것도 아닐 땐 아무일도
            }

        }

        int ans = 0;
        for(int k = 0; k < 4; k++) {
            switch(k) {
                case 0:
                    if(gears[k].get(0) == 1) {
                        ans += 1;
                    }
                    break;
                case 1:
                    if(gears[k].get(0) == 1) {
                        ans += 2;
                    }
                    break;

                case 2:
                    if(gears[k].get(0) == 1) {
                        ans += 4;
                    }
                    break;
                case 3:
                    if(gears[k].get(0) == 1) {
                        ans += 8;
                    }
                    break;
            }
        }
        System.out.println(ans);
    }

    public static void rotate(int gearNo) {
        LinkedList<Integer> gear = gears[gearNo];
        int last = gear.removeLast();
        gear.addFirst(last);
    }

    public static void rotateReverse(int gearNo) {
        LinkedList<Integer> gear = gears[gearNo];
        int first = gear.removeFirst();
        gear.addLast(first);
    }

}
