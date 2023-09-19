$(() => {
    $.ajax({
        xhrFields: {
            withCredentials: true // 인증서를 가지고 .ajax를 요청! (쿠키가 따라갈 수 있도록)
        },
        url: 'http://192.168.1.21:8888/back/orderlist',
        method: 'get',
        // data: 요청 전달 데이터가 없기 때문에 생략
        success: (responseJSONObj) => {

            const $originTrObj = $('div.orderlist>table>thead>tr') // 원본
            const $tbodyObj = $('div.orderlist>table>tbody') // 원본

            responseJSONObj.forEach(element => {  // 매개변수 = (인덱스, 요소)

                const $copyTrObj = $originTrObj.clone() // 복제본
                $copyTrObj.empty()

                const p = element.product // 상품
                const q = element.quantity // 수량

                const $prodNoTdObj = $('<td>')
                $prodNoTdObj.append(p.prodNo)
                $copyTrObj.append($prodNoTdObj)

                const $prodNameTdObj = $('<td>')
                $prodNameTdObj.append(p.prodName)
                $copyTrObj.append($prodNameTdObj)

                const $prodPriceTdObj = $('<td>')
                $prodPriceTdObj.append(p.prodPrice)
                $copyTrObj.append($prodPriceTdObj)

                const $quantityTdObj = $('<td>')
                $quantityTdObj.append(q)
                $copyTrObj.append($quantityTdObj)

                $tbodyObj.append($copyTrObj) // tbody 객체에 복제본 개체 추가

            }) // responseJSONObj.forEach()

            const $copyTrObj = $originTrObj.clone() // 복제본

        } // success

    }) // .ajax

})