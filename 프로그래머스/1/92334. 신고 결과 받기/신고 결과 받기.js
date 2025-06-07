function solution(id_list, report, k) {
    

    // 1) 
    const reportSet = [...new Set(report)];
    
    // 2) 
    let newReportSet = reportSet.map(str => str.split(" "));
    console.log(newReportSet);
    
    // 3) 
    let idNum = id_list.length;
    let count = new Array(idNum).fill(0);

    for(let i = 0; i < newReportSet.length; i++){
        for(let j = 0; j < idNum; j++){
            if(newReportSet[i][1] === id_list[j]){
                count[j]++;
            }
        }
    }
    
    
    // 4) 
    let result = new Array(idNum).fill(0);
    
    for(let i = 0; i < idNum; i++){
        for(let j = 0; j < newReportSet.length; j++){
            if(count[i] >= k && newReportSet[j][1] === id_list[i]){
                result[id_list.indexOf(newReportSet[j][0])]++;
            }
        }
    }

    return result;
}