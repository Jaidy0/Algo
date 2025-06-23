function solution(n) {
    
    let numsArr = n.toString().split("").map(char => char.charCodeAt(0) - 48);
    
    numsArr.sort((a,b) => b - a); 
    // return parseInt(numsArr.join(""));
    return +numsArr.join("");
}