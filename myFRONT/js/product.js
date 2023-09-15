$(() => {
    console.log(location.search) // ?prodno=C0001

    const queryStr = location.search.substr(1) // prodno=C0001

    $.ajax({
        url: "http://localhost:8888/back/productjson",
        method: 'get',
        data: queryStr,

        success: (responseJSONObj) => {

            if (responseJSONObj.msg != undefined) {   // 상품 조회 실패의 경우
                alert(responseJSONObj.msg)
            } else {                                 // 상품 조회 성공의 경우
                $('div.product>img').attr('src', `../images/${responseJSONObj.prodNo}.jpg`)
                $('div.product>div>ul>li>span.prodname').html(responseJSONObj.prodName)
                $('div.product>div>ul>li>span.prodno').html(responseJSONObj.prodNo)
                $('div.product>div>ul>li>span.prodprice').html(responseJSONObj.prodPrice)
            } // if-else
        }, // success

        error: (jqxhr) => {
            console.log(jqxhr.state()) // rejected
            console.log(jqxhr.status)  // 500
        } // error

    }) // .ajax({})

    //----장바구니넣기 버튼객체에서 클릭이벤트가 발생했을 때 할 일 START----
    $('div.product>div>ul>li>button').click(()=>{
        const prodno = $('div.product span.prodno').html()  // 상품번호값
        const quantity = $('div.product input.quantity').val() // 수량
        $.ajax({
            xhrFields: {
                withCredentials: true // 인증서를 가지고 .ajax를 요청! (쿠키가 따라갈 수 있도록)
            }, // 이후 서버쪽에서 해당 인증을 받아주는 과정도 추가해야 함!
            url: 'http://192.168.1.21:8888/back/addcart',//'http://localhost:8888/back/addcart',
            method : 'get',
            data : `prodNo=${prodno}&quantity=${quantity}`,
            success: ()=>{

            },
            error : (jqxhr)=>{
                alert(jqxhr.status)
            }   

        }) // .ajax({})
    })
    //----장바구니넣기 버튼객체에서 클릭이벤트가 발생했을 때 할 일 END----

}) // (() => {})