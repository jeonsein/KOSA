// function ajaxHandler(method, url, target) {
function ajaxHandler(method, u, target) {
    console.log(u)

    /*
    const xhttp = new XMLHttpRequest()
    xhttp.addEventListener('readystatechange', function () {
        // alert(this.readyState + ":" + this.status)

        if (this.readyState == 4) {
            if (this.status == 200) {
                target.innerHTML = this.responseText
            } else if (this.status == 404) {
                alert('자원이 없습니다.')
            } else if (this.status == 500) {
                alert('서버 실행시 오류가 발생했습니다.')
            } else {
                alert(this.responseText)
            } // if-else
        } // if
    }) // xhttp.addEventListener()
    xhttp.open(method, url)
    xhttp.send()
    */
     // 🔽 🔼
    $.ajax({
        url: u, // 매개변수의 값을 property의 값으로 사용함!
        method: method,
        success: (responseText) => {
            //  요청이 성공적으로 응답되었을 때 할 일을 기술!
            target.html(responseText)
        },
        error: () => {
            alert('응답실패')
        }
    })

} // ajaxHandler

// js -> jQuery 사용해서 코드 수정!
// window.addEventListener('load', () => {
// $(document).ready()
// 🔽 🔼
$(() => {
    // DOM Tree에서 section 객체 찾기
    const sectionObj = document.querySelector('section')
    // 🔽 🔼
    const $sectionObj = $('section')
    console.log("----- 자바스크립트 객체 -----")
    console.log(sectionObj)

    console.log("----- 제이쿼리 객체 -----")
    console.log($sectionObj)
    console.log(sectionObj === $sectionObj)
    console.log(sectionObj === $sectionObj.get(0))

    // DOM Tree에서 nav>ul>li>a 객체들 찾기
    const menus = document.querySelectorAll('nav>ul>li>a')
    // 🔽 🔼
    const $menus = $('nav>ul>li>a')

    /*
    menus.forEach((menu, index) => {
        
        // 〓〓 메뉴 객체에서 클릭이벤트가 발생했을 때 할 일 START 〓〓
        menu.addEventListener('click', (e) => {
            console.log(e.target.className)
            // menu
            switch(e.target.className) { // 화살표 함수 내부에서의 this는 윈도우 객체이기 때문에 e.target 사용!
                case 'login':
                    ajaxHandler('GET', './login.html', sectionObj) // sectionObj = 결과를 보여줄 영역
                    break;

                case 'signup':
                    ajaxHandler('GET', './signup.html', sectionObj)
                    break;
                    
                case 'logout': break;
                case 'productlist':
                    ajaxHandler('GET', './productlist.html', sectionObj)
                    break;
                case 'cartlist': break;
                case 'orderlist': break;
            } // switch(e.target.class)()
            e.preventDefault()

        }) // menu.addEventListener()
        // 〓〓 메뉴 객체에서 클릭이벤트가 발생했을 때 할 일 END 〓〓

    }) // menus.forEach()
    */
    // 🔽 🔼
    // 〓〓 메뉴 객체에서 클릭이벤트가 발생했을 때 할 일 START 〓〓
    $menus.click((e) => {
        console.log(e.target.className)
        // menu
        switch (e.target.className) { // 화살표 함수 내부에서의 this는 윈도우 객체이기 때문에 e.target 사용!
            case 'login':
                ajaxHandler('GET', './login.html', $sectionObj) // sectionObj = 결과를 보여줄 영역
                break;

            case 'signup':
                ajaxHandler('GET', './signup.html', $sectionObj)
                break;

            case 'logout': break;
            case 'productlist':
                ajaxHandler('GET', './productlist.html', $sectionObj)
                break;
            case 'cartlist': break;
            case 'orderlist': break;
        } // switch(e.target.class)()
        e.preventDefault()

    }) // menu.addEventListener()
    // 〓〓 메뉴 객체에서 클릭이벤트가 발생했을 때 할 일 END 〓〓)

});