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

}) // (() => {})