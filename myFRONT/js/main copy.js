function ajaxHandler (method, url, target) {
    console.log(url)

    const xhttp = new XMLHttpRequest()
    xhttp.addEventListener('readystatechange', function() {
        // alert(this.readyState + ":" + this.status)

        if (this.readyState == 4 ) {
            if(this.status == 200) {
                target.innerHTML = this.responseText
            } else if(this.status == 404) {
                alert('자원이 없습니다.')
            } else if(this.status == 500) {
                alert('서버 실행시 오류가 발생했습니다.')
            } else {
                alert(this.responseText)
            } // if-else
        } // if
    }) // xhttp.addEventListener()
    xhttp.open(method, url)
    xhttp.send()
} // ajaxHandler

window.addEventListener('load', () => {

    // DOM Tree에서 section 객체 찾기
    const sectionObj = document.querySelector('section')
    // DOM Tree에서 nav>ul>li>a 객체들 찾기
    const menus = document.querySelectorAll('nav>ul>li>a')
    
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

}) // window.addEventListener()