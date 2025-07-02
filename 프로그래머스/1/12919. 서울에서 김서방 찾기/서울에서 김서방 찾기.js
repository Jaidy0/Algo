function solution(seoul) {
    
    let whereKim = 0;
    for(let i = 0; i < seoul.length; i++){
        if(seoul[i] === "Kim"){
            whereKim = i;
        }
        
    }
    
    return `김서방은 ${whereKim}에 있다`;
}