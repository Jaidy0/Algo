function solution(numbers) {
    
    return 45 - numbers.reduce((acc, cur) => (acc + cur), 0)
    
    // let checkNums = [0,1,2,3,4,5,6,7,8,9];
    // let sum = 0;
    // checkNums.map(arr => { 
    //         if(!numbers.includes(arr)){
    //         sum += arr;
    //         }
    //     }
    // )
    // return sum;
}