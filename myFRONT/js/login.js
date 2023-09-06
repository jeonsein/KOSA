window.addEventListener('load', () => {
    alert("▷▷▷▷▷▷▷login용 window load event handler")
    const savedId = localStorage.getItem('savedId')
    // console.log(savedId) // null

    if(savedId != null) { // id 저장이 되어있는 상태
        document.querySelector('input[name=id]').value = savedId
    } // if

    // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 START 〓〓〓
        // DOM Tree에서 form 객체 찾기
    document.querySelector('form').addEventListener('submit', (e) => {
        
        // checkbox 객체의 checked 속성값을 확인
        if(document.querySelector('input[type=checkbox]').checked) { // checkd == true이면
            // setItem(savedId에저장, 사용자가입력한id입력값)
            localStorage.setItem('savedId', document.querySelector('input[name=id]').value)
        } else {    // id 저장하지 않겠다!
            localStorage.removeItem('savedId') // 저장된 아이디 제거
        } // if-else

        const xhttp = new XMLHttpRequest()

        xhttp.addEventListener('readystatechange', function() {
            if(this.readyState == 4) {
                if(this.status == 200) {
                    alert(this.responseText + ":" + this.responseText)
                } else {
                    alert(this.status + ":" + this.responseText)
                } // if-else
            } // if
        }) // xhttp.addEventListener
        xhttp.open('post', 'http://localhost:8888/back/login')
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded") // 헤더 설정
        // xhttp.send('id=a&pwd=b')
        // 사용자 입력값으로 send하기 🔽
        const idValue = document.querySelector('input[name=id]').value
        const pwdValue = document.querySelector('input[name=pwd]').value
        // const data = 'id=' + idValue + '&pwd=' + pwdValue // data = 요청시 전달할 데이터
        // 표준화 -> 백틱 연산자 사용 🔽
        const data = `id=${idValue}&pwd=${pwdValue}`
        console.log(data)
        xhttp.send(data)

        e.preventDefault()
        // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 END 〓〓〓

    }) // document.querySelector('form').addEventListener()

}); // window.addEventListener()