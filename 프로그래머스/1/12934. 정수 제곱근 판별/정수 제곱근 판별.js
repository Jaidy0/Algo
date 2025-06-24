function solution(n) {
    
    let X = 1;
    let ans = -1;
    for(let i = 0; i <= X; i++) {
        if(i * i === n){
            ans = (i + 1) * (i + 1);
        }
        X = n/i;
    }

    return ans;
}