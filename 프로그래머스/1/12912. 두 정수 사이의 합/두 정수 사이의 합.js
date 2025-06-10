function solution(a, b) {
    let sum = 0;
    if( a < b){
        while(a <= b){
            sum += a;
            a++;
        }
    } else {
        while(b <= a){
            sum += b;
            b++;
        }        
    }
    
    return sum;
}