function solution(n) {
    let to3 = n.toString(3);
    let reverse3 = to3.split("").reverse().join("");
    let to10 = parseInt(reverse3,3); 
    
    return to10;
}