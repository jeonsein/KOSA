// window.addEventListener('load', function(){
$(() => {

    /*
        중복확인 버튼 클릭되었을 때, 입력된 아이디 = 'admin' -> 이미 사용중인 아이디입니다.
        중복확인 버튼 클릭되었을 때, 입력된 아이디 != 'admin' -> 사용 가능한 아이디입니다. 
    */

    // DOM Tree에서 id 입력란 객체 찾기
    // const inputIdObj = document.querySelector('#id'); // ('input[name="id"]'), ('#id'), ('form.signup>input[name=id]')
    const inputIdObj = $('#id')

	/*
        아이디 입력란에 포커스되었을 때, 가입하기 버튼 사라짐
    */
	// ------------ id 입력란 객체에서 focus 이벤트가 발생했을 때 할 일 START ------------
	inputIdObj.addEventListener('focus', function(){
		signupBtnObj.style.display = 'none'
	}) // inputIdObj.addEventListener()
	// ------------ id 입력란 객체에서 focus 이벤트가 발생했을 때 할 일 END ------------
	
	// DOM Tree에서 중복확인 버튼 객체 찾기
    // const duplicateBtnObj = document.querySelector('button.idDuplicateCheckBtn') // (form.signup>button[type=button])
    const duplicateBtnObj = document.querySelector('button.idDuplicateCheckBtn') // (form.signup>button[type=button])

    // DOT Tree에서 가입하기 버튼 객체 찾기
    // const signupBtnObj = document.querySelector('button.signupBtn')
    const signupBtnObj = document.querySelector('button.signupBtn')

   // ------------ button 객체에서 클릭 이벤트가 발생했을 때 할 일 START ------------
    duplicateBtnObj.addEventListener('click', function(){
        if(inputIdObj.value == 'admin') {
            alert('이미 사용중인 아이디입니다.')
        } else {
            alert('사용 가능한 아이디입니다.')
            signupBtnObj.style.display = 'inline-block'
        } // if-else
    }); // btnObj.addEventListener()    
    // ------------ button 객체에서 클릭 이벤트가 발생했을 때 할 일 END ------------

// --------------------------------------------------------------------------------------------    

    /*
        가입하기 버튼 클릭되었을 때, 비밀번호 값과 비밀번호1 값이 같을 경우 전송,
        다를 경우 '비밀번호를 다시 입력하세요' 경고창 보여주기
    */

    // DOM Tree에서 폼객체 찾기
    const formObj = document.querySelector('form.signup')

	// DOM Tree에서 비밀번호 객체 찾기
	const pwd1 = document.querySelector('#p');
	const pwd2 = document.querySelector('#p1');

    // ------------ 폼객체에서 submit 이벤트가 발생했을 때 할 일 START ------------
    formObj.addEventListener('submit', function(e){

		// 비밀번호 객체 따로 받아와서 비교하기
		if(pwd1.value !== pwd2.value) {
			alert('비밀번호를 다시 입력하세요.');
			pwd1.focus()	// focus 이동시키기
			pwd1.select()	// 이후 입력된 항목 선택되게 하기!
			e.preventDefault(); // 기본 이벤트 막기!
		}; // if

    }); // formObj.addEventListener()
    // ------------ 폼객체에서 submit 이벤트가 발생했을 때 할 일 END ------------

});
