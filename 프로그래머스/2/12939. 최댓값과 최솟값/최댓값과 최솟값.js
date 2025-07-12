function solution(s) {
    let sArrays = s.split(" ");
    sArrays = sArrays.map( arr => parseInt(arr));
    sArrays.sort((a,b) => a-b);
    
    let last = sArrays.pop();
    return `${sArrays[0]} ${last}`;
}