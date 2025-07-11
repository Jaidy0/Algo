function solution(arr1, arr2) {
    let ans = [];
    for(let i = 0; i < arr1.length; i++) {
        let sumArr = [];
        for(let j = 0; j < arr1[0].length; j++) {
            let sum = arr1[i][j] + arr2[i][j];
            sumArr.push(sum);
        }
        ans.push(sumArr);
    }
    
    return ans;
}