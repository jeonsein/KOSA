const backURL = 'http://192.168.1.21:8888/backspringconfiguration'
const frontURL = 'http://192.168.1.21:5500/html'

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

$(() => {

    const loginedId = localStorage.getItem("loginedId")
    const $img = $('nav>ul>li>img.profile')
    $img.parent().hide() // img 가려놓고 시작

    if(loginedId == null) {  // 로그인이 안된 경우
        // 로그인, 가입 메뉴를 보여주기 (자소서, 프로필 이미지, 로그아웃 메뉴는 보여주지 않음!)
        $('nav>ul>li>a.login').parent().show()
        $('nav>ul>li>a.signup').parent().show()
        $('nav>ul>li>a.intro').parent().hide()
        $('nav>ul>li>a.logout').parent().hide()
    } else {                 // 로그인이 된 경우
        $.ajax({
            xhrFields: {
                responseType: "blob",
            },
            url: 'http://192.168.1.21:8888/backspringconfiguration/download',
            data: 'id='+loginedId + "&opt=profile",
            success: (responseData)=>{
                if(responseData.size > 0){
                    const url = URL.createObjectURL(responseData)
                    $img.attr('src', url)
                    $img.parent().show() // 프로필 보여줌!
                }
            },
            error: (jqxhr)=>{
                
            }
        })
        // 자소서, 로그아웃 메뉴 보여주기 (로그인, 가입 메뉴는 보여주지 않음!)
        $('nav>ul>li>a.login').parent().hide()
        $('nav>ul>li>a.signup').parent().hide()
        $('nav>ul>li>a.intro').parent().show()
        $('nav>ul>li>a.logout').parent().show()
    } // if-else

    // DOM Tree에서 section 객체 찾기
    const $sectionObj = $(`section`)
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

            case 'logout': 
                $.ajax({
                    xhrFields: {
                        withCredentials: true 
                    },
                    url: 'http://192.168.1.21:8888/backspringconfiguration/logout',
                    success: () => {
                        localStorage.removeItem('loginedId')
                        location.href='./main.html'
                    } // success
                })
            break;
            
            case 'productlist':
                // 방법#1
                ajaxHandler('GET', './productlist.html', $sectionObj)
                // 방법#2
                // ajaxHandler('GET', 'http://localhost:8888/back/productlistjson', $sectionObj)
                break;

            case 'cartlist':
                ajaxHandler('GET', './cartlist.html', $sectionObj)
                break;
                
            case 'orderlist':
                ajaxHandler('GET', './orderlist.html', $sectionObj)
                break;

            case 'intro':
                // location.href='http://192.168.1.21:8888/back/download?id=' + loginedId + '&opt=intro'
                location.href= backURL+ '/download?id=' + loginedId + '&opt=intro'
                break;
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