<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
<style>
body {
    /* width: px; */
}
a {
    text-decoration: none;
    color:black;
    font-weight: 500;
}

header>img {
    width: 45px;
    height: 45px;
    display: inline-block;
    float: left;
    margin-top: 5px;
    margin-left: 13px;
}

nav {
    width: 590px;
    display:inline-block;
    background-color: rgb(246, 245, 239);
    margin-left: 37px;
    padding-bottom: 25px;
    padding-right: 25px;
}

nav>ul>li {
    float: left;
    list-style-type: none; /*ul요소의 점 제거*/
    text-align: left;
    margin-left: 10px;
    margin-right: 20px;

}

nav>ul>li>a:hover{
    color:rgb(102, 153, 0);
    font-weight: bold;
    text-decoration: underline;
}

section {
    /* width: 710px; */
    height: 300px;
    background-color: rgb(243, 243, 233);
}

footer {
    /* width: 710px; */
    height: 50px;
    text-align: center;
    font-size: 12px;
    color:rgb(153, 153, 153);
    padding-top: 20px;
    background-color: rgb(44, 42, 41);
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script>
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
            // △ ▽
            // $.ajax({
            //     url: u, // 매개변수의 값을 property의 값으로 사용함!
            //     method: method,
            //     success: (responseText) => {
            //         //  요청이 성공적으로 응답되었을 때 할 일을 기술!
            //         target.html(responseText)
            //     },
            //     error: () => {
            //         alert('응답실패')
            //     }
            // })

            if (method == 'GET') {
                target.load(u, function (response, status, xhr) { // jQuery용 메소드 load()
                    if (status == "error") {
                        alert(xhr.status + ShadowRoot.statusText)
                    } // inner-if
                })  // .load()
            } // outer-if

        } // ajaxHandler

        // js -> jQuery 사용해서 코드 수정!
        // window.addEventListener('load', () => {
        // $(document).ready()
        // △ ▽
        $(() => {
            // DOM Tree에서 section 객체 찾기
            const sectionObj = document.querySelector('section')
            // △ ▽
            const $sectionObj = $(`section`)
            console.log("----- 자바스크립트 객체 -----")
            console.log(sectionObj)

            console.log("----- 제이쿼리 객체 -----")
            console.log($sectionObj)
            console.log(sectionObj === $sectionObj)
            console.log(sectionObj === $sectionObj.get(0))

            // DOM Tree에서 nav>ul>li>a 객체들 찾기
            // const menus = document.querySelectorAll('nav>ul>li>a')
            // △ ▽
            const $menus = $(`nav>ul>li>a`)

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
            // △ ▽
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
    </script>
</head>
<header>
	<img src="./images/hamster.jpg" alt="hamster">
</header>
<nav>
	<ul>
		<li><a href="#" class="login">로그인</a></li>
        <li><a href="#" class="signup">가입</a></li>
        <li><a href="#" class="logout">로그아웃</a></li>
        <li><a href="#" class="productlist">상품목록</a></li>
        <li><a href="#" class="cartlist">장바구니목록</a></li>
        <li><a href="#" class="orderlist">주문목록</a></li>
	</ul>
</nav>
