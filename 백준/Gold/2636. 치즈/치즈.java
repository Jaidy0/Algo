import java.util.*;
import java.io.*;

public class Main {
	
	static int R,C;
	static int[][] map;
	static boolean[][] V;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	public static void main (String[] args) throws IOException{
		// 정사각형 모양 판
		// 치즈에는 하나 이상의 구멍
		
		// 공기와 접촉된 칸 : 한 시간 후 녹음
		
		// 결과
		// 공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과
		// 녹기 한 시간 전에 치즈 조각이 놓인 칸의 개수 구하기
		
		// 치즈가 있는 칸 1, 없는 칸 0
		// 가로세로 길이 최대 100
		// 중요조건 : 판의 가장자리에는 항상 치즈가 없다. 
		
		/*
		 * 풀이 방법
		 * 1) 1로만 둘러쌓인 0 덩어리 좌표를 2로 만든다. -> 구멍표시 
		 * 2) BFS로 1덩어리를 찾고, 사방 중 0이 있는 좌표를 0으로 만든다.  
		 * 3) 시간 마다 치즈의 수를 저장한다.  
		 * 4) 치즈가 다 녹으면 저장해둔 직전의 치즈 수와 시간을 반환한다. 
		 */
		
		
		/*
		 * 2차 풀이 방법 (AI 한줄 힌트 받음)
		 * 1) (0,0)부터 0을 중심으로 BFS 하여 외부 공기를 방문 표시해 구분한다. 
		 * 2) BFS로 1덩어리를 찾고, 사방 중 방문 표시된 0이 있으면 1을 0으로 만든다.  
		 * 3) 시간 마다 치즈의 수를 저장한다.  
		 * 4) 치즈가 다 녹으면 저장해둔 직전의 치즈 수와 시간을 반환한다. 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		V = new boolean[R][C];
		
		int count = 1; // 치즈의 수
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) count++; // 최초 치즈 수 세기
			}
		}
		
		
		
//		System.out.println(Arrays.deepToString(map));
		
		int t = 0;
		int lastCount = 0;
		while( count > 0) {
			
		V = new boolean[R][C];
		// 1) 1로만 둘러쌓인 0 덩어리 좌표 방문처리하기 
		distinctOuter(0,0);
		
		
		lastCount = countCheese();
		
		// 2) 치즈 녹이기
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 1) {
					meltCheese(i,j);
				}
			}
		}
		
		// 3) 치즈 수 체크하기
		count = countCheese();
		
		
		// 4) 시간 증가
		t++;
		
		}
		
		System.out.println(t);
		System.out.println(lastCount);
		
		
		
	}
	
	private static int countCheese() {
		int num = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 1) {
					num++;
				}
			}
		}
		
		return num;
	}

	private static void meltCheese(int x, int y) {
		
		for(int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			// 사방 한칸이라도 외부와 접촉 시 녹음
			if(map[nx][ny] == 0 && V[nx][ny]) {
				map[x][y] = 0;
				break;
			}
		}
		
	}

	static void distinctOuter(int r, int c) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		V[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0];
			int y = tmp[1];
			
			for(int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(nx >= 0 && ny >= 0 && nx < R && ny < C) {
					if(map[nx][ny] == 0 && !V[nx][ny]) {
						V[nx][ny] = true;
						q.add(new int[] {nx,ny});
					}
				}
				
			}
		}
		
		
	}
}
