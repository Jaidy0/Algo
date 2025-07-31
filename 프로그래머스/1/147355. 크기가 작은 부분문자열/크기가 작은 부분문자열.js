function solution(t, p) {
    let pLeng = p.length;
    
    let ans = 0;
    for(let i = 0; i < t.length - pLeng + 1; i++) {
        let tmp = t.slice(i,i+pLeng); 
        if(tmp - '0' <= p - '0') {
            ans++;
        }
    }
    
    return ans;
}