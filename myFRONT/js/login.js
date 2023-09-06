window.addEventListener('load', () => {
    const savedId = localStorage.getItem('savedId')
    // console.log(savedId) // null
    if(savedId != null) { // id 저장이 되어있는 상태
        document.querySelector('input[name=id]').value = savedId
    }

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

        e.preventDefault()
    }) // document.querySelector('form').addEventListener()
    // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 END 〓〓〓

}); // window.addEventListener()

/*
<form method="post" action="http://localhost:8888/back/login">
    <div class="form_wrapper">

        <!-- 아이디 -->
        <div class="id_wrapper">
            <p>아이디: </p>
            <input type="text" placeholder="아이디를 입력하세요." id="id" name="id" required>
        </div>

        <!-- 비밀번호 -->
        <div class="pwd_wrapper">
            <p>비밀번호: </p>
            <input type="password" placeholder="비밀번호를 입력하세요." id="pwd" name="pwd" required>
        </div>

        <!-- 아이디 저장 - 체크박스 -->
        <div class="id_stored_wrapper">
            <input type="checkbox" checked> 
            <p>아이디저장</p>
        </div>
    
        <!-- 로그인 버튼 -->
        <div class="loginBtn_wrapper">
            <button class="loginBtn" type="submit">로그인</button>
        </div>

    </div>
</form>
*/