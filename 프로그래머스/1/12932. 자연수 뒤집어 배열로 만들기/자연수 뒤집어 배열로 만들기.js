function solution(n) {
    
    let arr = (n+"").split("").map(str => parseInt(str));
    let ans = [...arr].reverse();
    
    return ans;
}