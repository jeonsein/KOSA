// window.addEventListener('load', ()=> {
$(() => {
    //DOM트리에서 가입하기버튼객체찾기
    // const btSignupObj = document.querySelector('form.signup>button[type=submit]')
    const $btSignupObj = $('form.signup>button[type=submit]')
    //DOM트리에서 id입력란객체찾기
    // document.getElementById('i')
    // document.querySelector('#i')
    // const idObj = document.querySelector('form.signup>input[name=id]')
    const $idObj = $('form.signup>input[name=id]')
    //----id입력란객체에서 focus이벤트 발생했을 때 할 일 START----
    // idObj.addEventListener('focus', ()=>{
    //     btSignupObj.style.display = 'none'
    // })
    $idObj.focus(() => {
        $btSignupObj.hide()
    })
    //----id입력란객체에서 focus이벤트 발생했을 때 할 일 END----


    //DOM트리에서 중복확인버튼객체찾기
    // const btDupchkObj = document.querySelector('form.signup>button[type=button]')
    const $btDupchkObj = $('form.signup>button[type=button]')

    //----중복확인버튼객체에서 클릭이벤트 발생했을때 할 일 START----
    // btDupchkObj.addEventListener('click', ()=>{
    $btDupchkObj.click(() => {
        // if('admin' == idObj.value){
        if ('admin' == $idObj.val()) {
            alert('이미 사용중인 아이디입니다')
        } else {
            alert('사용가능한 아이디입니다')
            // btSignupObj.style.display = 'inline-block'
            $btSignupObj.show()
        } // if-else
    }) // .click()
    //----중복확인버튼객체에서 클릭이벤트 발생했을때 할 일 END----


    //DOM트리에서 폼객체 찾기
    // const formObj = document.querySelector('form.signup')
    const $formObj = $('form.signup')

    //----폼객체에서 submit이벤트 발생했을때 할 일 START----
    // formObj.addEventListener('submit', (e)=>{
    $formObj.submit((e) => {
        const $pwdArr = $('form.signup>input[type=password]')   // 비밀번호 입력란 객체
        const $nameObj = $('form.signup>input[name=name]')  // 이름 입력란 객체
        
        if ($pwdArr.eq(0).val() != $pwdArr.eq(1).val()) {
            alert('비밀번호를 다시 입력하세요')
            $pwdArr.eq(0).focus()
            $pwdArr.eq(0).select()
        } else {
            
            // console.log($('form').serialize()) // id=1&pwd=1&name=1

            $.ajax({
                xhrFields: {
                    withCredentials: true
                },
                url:'http://192.168.1.21:8888/back/signup',
                methid:'post',
                data : 
                    // 방법1) 문자열 
                    // `id=${$idObj.val()}&pwd=${$pwdArr.eq(0).val()}&name=${$nameObj.val()}`,
                    // 방법2) 자스 객체로 이용
                    // { id: $idObj.val(), pwd: $pwdArr.eq(0).val(), name: $nameObj.val() },
                    // 방법3) 폼 객체
                    $('form').serialize(), // .serialize() = post 방식의 요청일 때에만 효과가 남!
                success: (responseJSONObj) => {
                    alert(responseJSONObj.msg);

                    if(responseJSONObj.status == 1) {
                        location.href = './main.html'
                    }
                },
                error: (jqxhr) => {
                    alert(jqxhr.status) // 정상처리가 되지 않으면 status = 0
                }
            })

        }// if-else

        // e.preventDefault()

        return false // 기본 이벤트 핸들러를 막는 것과 같은 효과 발생
        // e.preventDefault() + e.stopPropagation()

    }) // .submit()
    //----폼객체에서 submit이벤트 발생했을때 할 일 END----

})