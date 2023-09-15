$(() => {
    $.ajax({
        xhrFields: {
            withCredentials: true // 인증서를 가지고 .ajax를 요청! (쿠키가 따라갈 수 있도록)
        },
        url: 'http://192.168.1.21:8888/back/cartlist',
        method: 'get',
        // data: 요청 전달 데이터가 없기 때문에 생략
        success: (responseJSONObj) => {
            // 응답 내용(배열)의 첫 번째 요소
            // const p = responseJSONObj[0].product
            // const q = responseJSONObj[0].quantity
            // console.log(p, q)

            const $originTrObj = $('div.cartlist>table>thead>tr') // 원본
            const $tbodyObj = $('div.cartlist>table>tbody') // 원본
            let totalPrice = 0; // 총가격

            responseJSONObj.forEach(element => {  // 매개변수 = (인덱스, 요소)

                const $copyTrObj = $originTrObj.clone() // 복제본
                $copyTrObj.empty()
                
                const p = element.product // 상품
                const q = element.quantity // 수량

                const $prodNoTdObj = $('<td>') // td 객체 생성($'td')는 객체찾기 $('<td>')는 객체 생성
                $prodNoTdObj.append(p.prodNo) // td 객체의 하위 노드 추가
                $copyTrObj.append($prodNoTdObj) // 복제본에 td 객체를 추가

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

}) // $(() => {}