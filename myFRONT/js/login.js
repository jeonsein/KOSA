// window.addEventListener('load', () => {
$(() => {
    alert("▷▷▷▷▷▷▷login용 window load event handler")

    const savedId = localStorage.getItem('savedId')
    // console.log(savedId) // null

    if (savedId != null) { // id 저장이 되어있는 상태
        // document.querySelector('input[name=id]').value = savedId
        savedId = $(`input[name=id]`).val()
    } // if

    // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 START 〓〓〓
    // DOM Tree에서 form 객체 찾기
    // document.querySelector('form').addEventListener('submit', (e) => {
    $(`form`).submit((e) => {
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

        alert("ajax-1")
        const idValue = $(`input[name=id]`).val()
        alert("ajax-2")
        const pwdValue = $(`input[name=pwd]`).val()
        alert("ajax-3")
        const data = `id=${idValue}&pwd=${pwdValue}`
        alert("ajax-4" + data)
        $.ajax({
            url: 'http://localhost:8888/back/login',
            method: 'post',
            success: (responseText) => {
                alert(responseText)
            },
            error: (jqXHR, textStatus) => {
                alert(jqXHR.readyState + ":" + jqXHR.status + ":" + jqXHR.statusText)
                alert('에러발생')
            }
        })
        alert("ajax-5")
        e.preventDefault()
        // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 END 〓〓〓

    }) // document.querySelector('form').addEventListener()

});