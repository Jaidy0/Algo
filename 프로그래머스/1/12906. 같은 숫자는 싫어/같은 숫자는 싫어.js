function solution(arr)
{
    let ans = [arr[0]];
    // 새로운 배열을 생성한다. 
    for(let i = 1; i < arr.length; i++) {
        if(arr[i] !== arr[i-1])
        ans.push(arr[i]);
    }
    
    return ans;
    
    
    
    
    
//     // for 문을 돌리면서, 중복된 것이 있으면 해당 index를 삭제한다
//     let i = 0;
//     while(i < arr.length - 1 ) {
//         if(arr[i] == arr[i+1]) {
//             arr.splice(i+1,1);
//         } else {
//             i++;
//         }
//     }

//     return arr;
    
}