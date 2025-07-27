function solution(s) {
    let strings = s.split(" ")
    
    // 처음엔 for ... of 를 적용했었다. 하지만, of는 복사본을 만들어 사용하는 것이므로, 직접 접근을 통한 변경이 불가해 
    // index를 사용하거나 map을 사용해야 했다. 
    
    for(let i = 0; i < strings.length; i++) {
        let start = strings[i].charCodeAt(0);
        if( start >= 97 && start <= 122){
            strings[i] = strings[i][0].toUpperCase() + strings[i].slice(1).toLowerCase(); 
        } else if(start >= 48 && start <= 57) {
            strings[i] = strings[i][0] + strings[i].slice(1).toLowerCase();
        } else if(start >= 65 && start <= 90){
            strings[i] = strings[i][0] + strings[i].slice(1).toLowerCase();
        }
    }
    
    return strings.join(" ");
}