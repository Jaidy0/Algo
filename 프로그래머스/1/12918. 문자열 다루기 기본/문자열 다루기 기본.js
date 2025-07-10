function solution(s) {
    let isNum = true;
    let sArray = s.split("");
    for( let c of sArray ) {
        if(isNaN(c)) {
            isNum = false; 
        }
    }
    console.log(s.length);
    if(s.length !== 4 && s.length !== 6) {
        isNum = false;
    }
    
    return isNum;
}