let base = 100
const add = (x) => base+x // 계산 결과가 return
const test = () => {    
    console.log('module내부에서 this는 undefined입니다', this)
}

// export {add, test}
