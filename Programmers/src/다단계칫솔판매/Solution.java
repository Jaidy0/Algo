package 다단계칫솔판매;

import java.util.HashMap;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

    	// enroll : 각 판매원의 이름을 담은 배열
    	// referral : 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열
    	// seller : 판매량 집계 데이터의 판매원 이름을 나열한 배열
    	// amount : 판매량 집계 데이터의 판매수량을 나열한 배열 
    	
    	HashMap<String, String> parent = new HashMap<>();
    	for(int i = 0; i < enroll.length; i++) {
    		parent.put(enroll[i], referral[i]);
    	}
    	
    	HashMap<String, Integer> total = new HashMap(); // Total Hash맵 
    	
    	for(int i = 0; i < seller.length; i++) {
    		String curName = seller[i]; // 현 판매자 이름
    		
    		int money = amount[i] * 100; // 해당 판매자의 총 금액
    		
    		while(money > 0  && !curName.equals("-")) { // 판매액이 있고, 
    			total.put(curName, total.getOrDefault(curName, 0) + money - (money /10));
    			curName = parent.get(curName); // 현재 이름이 부모 노드에서 몇번째인지
    			money /= 10; // 10%만 부모로 올림
    		}
    		
    		
    	}
    	
    	// 결과를 배열로 반환
    	int[] ans = new int[enroll.length];
    	for(int i = 0; i < enroll.length; i++) {
    		ans[i] = total.getOrDefault(enroll[i], 0);
    	}
    	
        return ans;
    }
}