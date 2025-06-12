function solution(s){
    
    let pNum = 0;
    let yNum = 0; 
    
    let sArray = s.split("");
    
    for(let i = 0; i < sArray.length; i++){
        if(sArray[i] === 'p' || sArray[i] === 'P') {
            pNum++;
        }
        if(sArray[i] === 'y' || sArray[i] === 'Y') {
            yNum++;
        }        
    }
    
    if(pNum === yNum) {
        return true;
    } else {
        return false;
    }
    
}