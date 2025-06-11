function solution(schedules, timelogs, startday) {
    const sortedDays = startday === 1 ? timelogs : sortDays(timelogs, startday);

    return checkWorkStartTime(schedules, sortedDays);
    
}

function checkWorkStartTime(schedules, sortedDays) {
    let workerCount = 0;
    
    for(let i = 0; i < schedules.length; i++) {
        let tempCheck = true;
        for(let j = 0; j < 5; j++){
            if(sortedDays[i][j] > addTime(schedules[i], 10)) {
                tempCheck = false;
                break;
            }
        }
        if(tempCheck) {
            workerCount++;            
        } 

    }

    return workerCount;
}

function addTime(time, addMinutes) {
    let hh = Math.floor(time / 100);
    let mm = time % 100;
    
    mm = mm + addMinutes;
    if(mm >= 60){
        hh += Math.floor(mm / 60);
        mm = (mm) % 60;
    }
    return hh * 100 + mm;
    
}

function sortDays(timelogs, startday) {
    let sortedDays = new Array(timelogs.length).fill(null).map(() => new Array(7).fill(0));
    
    for(let i = 0; i < timelogs.length; i++){
        for(let j = 0; j < 7; j++){
            sortedDays[i][((startday - 1) + j) % 7] = timelogs[i][j];
        }
    }
    return sortedDays;
}