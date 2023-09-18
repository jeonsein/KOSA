$(() => {
    // alert("▷▷▷▷▷▷▷login용 window load event handler")

    const savedId = localStorage.getItem('savedId')
    // console.log(savedId) // null

    if (savedId != null) { // id 저장이 되어있는 상태
        // document.querySelector('input[name=id]').value = savedId
        $(`input[name=id]`).val(savedId)
    } // if

    // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 START 〓〓〓
    // DOM Tree에서 form 객체 찾기
    // document.querySelector('form').addEventListener('submit', (e) => {
    $('form').submit((e) => {
        alert("ajax-0")
        // checkbox 객체의 checked 속성값을 확인
        // jQuery용 메소드: $('input[type=checkbox]').prop('checked')
        if ($(`input[type=checkbox]`).prop('checked')) { // checkd == true이면
            // setItem(savedId에저장, 사용자가입력한id입력값)
            // localStorage.setItem('savedId', document.querySelector('input[name=id]').value)
            localStorage.setItem('savedId', $(`input[name=id]`).val())
        } else {    // id 저장하지 않겠다!
            localStorage.removeItem('savedId') // 저장된 아이디 제거
        } // if-else

        // alert("ajax-1")
        const idValue = $('input[name=id]').val()
        // alert("ajax-2")
        const pwdValue = $('input[name=pwd]').val()
        // alert("ajax-3")
        const data = `id=${idValue}&pwd=${pwdValue}`
        // alert("ajax-4" + data)

        $.ajax({
            xhrFields: {
                withCredentials: true 
            },
            url: 'http://192.168.1.21:8888/back/login',
            method: 'post',
            data: data,
            success: (responseJSONObj) => { // success의 응답내용은 로그인 실패 혹은 로그인 성공!
                // alert(responseText)
                if(responseJSONObj.status == 0) {   // 로그인 실패 케이스 (status = 0)
                    alert(responseJSONObj.msg)  // 응답내용의 msg를 alert!
                } else if(responseJSONObj.status == 1) {   // 로그인 성공 케이스 (status = 1)
                    localStorage.setItem('loginedId', idValue)
                    location.href="./main.html" // 메인으로 이동시키기
                } // if-else
            },
            error: (jqXHR, textStatus) => { //( jqXHR jqXHR, String textStatus, String errorThrown )
                // alert(textStatus) //error
                // console.log(jqXHR)
                alert(jqXHR.readyState + ":" + jqXHR.status + ":" + jqXHR.statusText)
            }
        }) // ajax()
        alert("ajax-5")
        e.preventDefault()
        // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 END 〓〓〓

    }) // document.querySelector('form').addEventListener()

});