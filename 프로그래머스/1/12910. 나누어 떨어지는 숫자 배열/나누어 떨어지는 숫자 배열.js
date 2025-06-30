function solution(arr, divisor) {
    let ans = arr.filter(x => x % divisor === 0).sort((a,b) => a-b);
    return ans.length !== 0? ans : [-1];
}