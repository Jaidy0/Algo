function solution(arr) {
    if(arr.length <= 1) return [-1];
    let minValue = Math.min(...arr);
    return arr.filter(value => value !== minValue); 
}