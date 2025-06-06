function solution(x, n) {
    
    let ans = [];
    
    let i = x;
    while(ans.length !== n){
        ans.push(i);
        i = i + x; 
    }

    return ans;
}