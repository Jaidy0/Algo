function solution(s) {
    let sLeng = s.length;
    let leng = Math.ceil(s.length / 2);
    return sLeng % 2 === 0 ? s.substring(leng -1, leng + 1) : s[leng - 1];
}