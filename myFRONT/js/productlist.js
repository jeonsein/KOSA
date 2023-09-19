$(() => {
    function ajaxHandler(cp) {
        $.ajax({
            url: 'http://192.168.1.21:8888/back/productlistjson',
            method: 'get',
            data: `currentPage=${cp}`,
            success: (responseJSONObj) => {
                // 응답 성공 시 호출되는 success 콜백 함수
                const totalCnt = responseJSONObj.totalCnt
                const list = responseJSONObj.list

                // 원본 product 객체 찾기
                const $originProduct = $('div.productlist>div.product').first() //원본 가져오기 위해 .first()
                // $originProduct.parent().children().not($originProduct) // 부모 입장에서 originProduct를 제외한 나머지 객체를 찾음
                $originProduct.siblings().remove() // originProduct의 형제들을 찾아서 clear = productlist영역 초기화
                $originProduct.show()

                $(list).each((index, p) => {  // 매개변수 = (인덱스, 요소)

                    // 복제본 product 객체 생성
                    const $copyProduct = $originProduct.clone()
                    const prodNo = p.prodNo // 상품 번호
                    const prodName = p.prodName // 상품 이름

                    $copyProduct.find("ul>li>img").attr('src', '../images/' + prodNo + '.jpg')
                    $copyProduct.find("ul>li>span").html(prodName)

                    // 복제본 객체를 .productlis 객체의 자식으로 추가하기
                    $('div.productlist').append($copyProduct)

                }) // forEach와 비슷한 jQuery용 문법

                // 원본 객체 숨기기
                $originProduct.hide()

                // 페이지 그룹 찾기
                const $divPageGroup = $('div.pagegroup')

                // 페이지 그룹 영역 초기화
                $divPageGroup.empty() // 부모 입장에서 후손들을 모두 제거함

                const startPage = responseJSONObj.startPage // 시작 페이지
                const endPage = responseJSONObj.endPage     // 끝 페이지

                // 페이징
                if (startPage > 1) { // startPage > 1이면 PREV를 보여주어야 함
                    // let page = '[<span class="pg' + (startPage-1) + '">' + 'PREV' + '</span>]' + '&nbsp;&nbsp;&nbsp;'
                    let page = `[<span class = "pg${startPage - 1}">'PREV'</span>]&nbsp;&nbsp;&nbsp;`
                } // if

                // 시작 페이지부터 끝 페이지까지 반복하면서 div.pagegroup에 내용을 채워주기
                for (let i = startPage; i <= endPage; i++) {
                    // let page = '[<span>'+ i + '</span>]' + '&nbsp;&nbsp;&nbsp;'
                    let page = `[<span class = "pg${i}">${i}</span>]&nbsp;&nbsp;&nbsp;`
                    $divPageGroup.html($divPageGroup.html() + page)
                } // for

                if (endPage != responseJSONObj.totalPage) {
                    // let page = '[<span>NEXT</span>]'
                    let page = `[<span class = "pg${endPage + 1}">NEXT</span>]`
                    $divPageGroup.html($divPageGroup.html() + page)
                } // if

            },
            error: () => {
                // 응답 실패 시 호출되는 success 콜백 함수
            }
        }) // $.ajax({})
    }

    ajaxHandler(1) // 함수 호출

    /* 'div.pagegroup' = 현재 돔 트리에 존재하는 객체 */
    /* span 객체가 현재는 존재하지 않지만, 미리 span 객체가 생성되었을 때 할 일을 등록하는 것 */
    $('div.pagegroup').on('click', 'span', (e) => {
        // alert($(e.target).html() + ": " + $(e.target).attr('class') + "페이지가 클릭되었습니다")
        const pg = $(e.target).attr('class')
        const currentPage = pg.substr(2)

        ajaxHandler(currentPage)
    }) // $('div.pagegroup').on()

    $('div.productlist').on('click', 'div.product', (e) => {
        // alert('클릭되었습니다.')

        /* 이미지 객체를 찾고 해당 이미지의 src 속성을 가지고 와서 상품 번호만 분리해내기 */
        // alert($(e.target).attr("src").lastIndexOf(".")) // .의 index 위치 알아내기 = 15
        const src = $(e.target).attr("src") // src 속성 가지고 오기 (../images/C0001.jpg)
        const prodNo = src.substring(src.lastIndexOf('/') + 1, src.lastIndexOf('.')) // /랑 .의 index위치로 상품 번호만 분리
        // alert(prodNo)
        location.href = `./product.html?prodno=${prodNo}`
    }) // $('div.productlist').on()

});