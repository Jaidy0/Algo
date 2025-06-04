function solution(n)
{   
    let temp = n;
    let ans = 0;
    while(temp !== 0){
        let num = temp % 10;
        ans += num;
        temp = Math.floor(temp / 10);
    }
    return ans;
}