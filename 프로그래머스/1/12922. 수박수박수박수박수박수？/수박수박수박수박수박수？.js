function solution(n) {
    
    // let ans = "";
    // for(let i = 0; i < n; i++) {
    //     if(i % 2 === 0) {
    //         ans += "수";   
    //     } else { 
    //         ans += "박";
    //     }
    // }
    
    return "수박".repeat(Math.ceil(n/2)).substring(0,n);
    // return ans;
}