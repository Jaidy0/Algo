function solution(num) {
    let count = 0;
    
    while(num !== 1 && count < 500 ) {
        if(num % 2 === 0){
            num = num / 2;
            count++;
        } else {
            num = num * 3 + 1;
            count++;
        }
    }
    
    return count === 500 ? -1 : count ;
}