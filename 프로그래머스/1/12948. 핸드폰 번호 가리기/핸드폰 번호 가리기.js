function solution(phone_number) {
    let numLength = phone_number.length - 4;
    let ans = "*".repeat(numLength) + phone_number.substring(numLength);
    return ans;
}