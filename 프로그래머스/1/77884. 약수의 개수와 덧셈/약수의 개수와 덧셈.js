function solution(left, right) {
    let sum = 0;
    for(let i = left; i <= right; i++) {
         
        let factors = [];
         for(let j = 1; j <= i; j++) {
             if( i % j === 0 ){
                 factors.push(j);
             }
         }
        
        if(factors.length % 2 === 0) {
            sum += i;            
        } else {
            sum -= i;
        }
    }
    
    // i까지 있는 소수들로 나눠간다. 
    
    return sum;
}

