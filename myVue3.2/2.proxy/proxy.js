// Dom 트리가 완성이 되면, div나 h2, input 영역들이 객체가 됨
window.addEventListener('load', function() {

    const model = {message: 'HELLO'}
    const demo1 = document.querySelector("#demo1")
    const demo2 = document.querySelector("#demo2")
    const inputObj = document.querySelector('input')

    //
    const data = new Proxy(model, {
        get: function(target, key){
            alert('get')
            if(!target[key]) throw new Error(`존재하지 않는 속성${key}입니다`)
            return target[key]
        },
        set : function(target, key, value){
            alert('set')
            target[key] = value  // target.key X / target[key] O
            demo1.innerHTML = value
            demo2.innerHTML += '<br>' +value
        }
    }) // Proxy({})

    demo1.innerHTML = data.message //Proxy의 get함수가 자동호출됨 // .message -> key에 message가 들어감
    demo2.innerHTML = data.message 

    // 이벤트 발생된 객체 = input 영역 -> input 영역에 입력된 값을 data.message에 대입함
    inputObj.addEventListener('keyup', function(e){                
        data.message =  e.target.value      //Proxy의 set함수가 자동호출됨  
    })

})