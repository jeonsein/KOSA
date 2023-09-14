// function ajaxHandler(method, url, target) {
function ajaxHandler(method, u, target) {
    console.log(u)

    if (method == 'GET') {
        target.load(u, function (response, status, xhr) { // jQuery용 메소드 load()
            if (status == "error") {
                alert(xhr.status + ShadowRoot.statusText)
            } // inner-if
        })  // .load()
    } // outer-if

} // ajaxHandler

// js -> jQuery 사용해서 코드 수정!
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
    const $menus = $(`nav>ul>li>a`)

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
                // 방법#1
                ajaxHandler('GET', './productlist.html', $sectionObj)
                // 방법#2
                // ajaxHandler('GET', 'http://localhost:8888/back/productlistjson', $sectionObj)
                break;

            case 'cartlist': break;
            case 'orderlist': break;
        } // switch(e.target.class)()
        e.preventDefault()

    }) // menu.addEventListener()
    // 〓〓 메뉴 객체에서 클릭이벤트가 발생했을 때 할 일 END 〓〓)

    // 〓〓 로고 img 객체에서 클릭이벤트가 발생했을 때 할 일 START 〓〓)
    const $logo = $(`header>img`)

    $logo.click((e) => {
        location.href = `./main.html`
    })
    // 〓〓 로고 img 객체에서 클릭이벤트가 발생했을 때 할 일 END 〓〓)

});