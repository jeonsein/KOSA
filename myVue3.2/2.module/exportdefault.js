const base = 100
const calculator = {
    add : (x) => base+ x,
    multiply :  (x) => base * x,
    test : ()=> console.log('module내부에서 this는 undefined입니다', this)
}
export default calculator
