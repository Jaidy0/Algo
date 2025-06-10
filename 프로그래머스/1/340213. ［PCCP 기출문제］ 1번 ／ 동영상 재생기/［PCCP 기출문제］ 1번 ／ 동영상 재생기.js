function solution(video_len, pos, op_start, op_end, commands) {
    // prev -> 10초 전으로 이동. 10초 미만일 경우 처음 위치로
    // next -> 10초 후로 이동. 10초 미만일 경우 마지막 위치로
    // 오프닝 건너뛰기 : 현재 재생 위치가 오프닝 구간인 경우 자동으로 오프닝이 끝나는 위치로 이동
    
    
    /* 아이디어
        1) 현재 시간 pos를 : 기점으로 split posH, posM 하고 분단위로 계산 
        2) op_start, op_end 사이인지 체크 (각각의 시간을 split 해서 비교)
         - 사이일 경우 op_end 로 pos 설정 
         - 시간에 대한 연산 고민 (분 단위로)
        3) 사용자의 명령 값에 따라 pos 시간 반영 (commnand 동작이 한번 이루어질 때마다 2) 체크 )
    */ 
    
    let pos_mm = toMin(pos);
    let op_s_mm = toMin(op_start);
    let op_e_mm = toMin(op_end);
    let video_len_mm = toMin(video_len);
    
    let i = 0;
    
    console.log("1 pos_mm",pos_mm);
    while(i < commands.length){
        // 오프닝인지 확인
        if(pos_mm >= op_s_mm && pos_mm < op_e_mm){
            pos_mm = op_e_mm;
        }
        
        // 사용자 명령 반영 
        if(commands[i] === 'next'){
            if (pos_mm + 10 > video_len_mm ) {pos_mm = video_len_mm
            } else {
                pos_mm = pos_mm + 10;
            };
        } else {
            if(pos_mm - 10 < 0) {pos_mm = 0;
            } else {
                pos_mm = pos_mm - 10;
            };
        }
        
        console.log("pos_mm : ", pos_mm);
        
        // 오프닝인지 확인
        if(pos_mm >= op_s_mm && pos_mm < op_e_mm){
            pos_mm = op_e_mm;
        }
        
        i++;
    }
    
    return toHourMin(pos_mm);
}

function toMin(time){
    const splitedTime = time.split(":");

    let mm = parseInt(splitedTime[0], 10)*60 +  parseInt(splitedTime[1],10);

    return mm;
}

function toHourMin(time){
    let hh = Math.floor(time / 60).toString().padStart(2,'0');
    let mm = (time % 60).toString().padStart(2,'0');
    console.log("hh : mm", hh, ":", mm);
    let curTime = hh + ":" + mm;
    
    return curTime;
}