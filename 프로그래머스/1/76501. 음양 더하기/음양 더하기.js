function solution(absolutes, signs) {
    
    let sum = 0;
    let n = absolutes.length; 
    for(let i = 0; i < n; i++){
        if(signs[i]){
            sum += absolutes[i];
        } else if(!signs[i]) {
            sum -= absolutes[i];
        }
    }
    
    return sum;
}