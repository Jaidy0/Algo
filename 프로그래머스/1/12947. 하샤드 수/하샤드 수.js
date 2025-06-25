function solution(x) {
    
    /*
        하샤드 수 = x의 자릿수의 합으로 x가 나누어 져야 함 
        x가 하샤드 수인지 아닌지 검사하는 함수 작성
    */
    
    let xArr = x.toString().split("");
        let sum = xArr.reduce((acc, digit) => {
        return acc + Number(digit); // 문자를 숫자로 변환 후 더하기
    }, 0);
    
    if (x % sum === 0) {
        return true;  // 하샤드 수
    } else {
        return false; // 하샤드 수가 아님
    }
    
}