function solution(arr) {
    
    let sum = 0;
    
    for(let i = 0; i < arr.length; i++){
        sum += arr[i];
    }
    
    let ans = 0;
    ans = sum / arr.length;
    return ans;
}