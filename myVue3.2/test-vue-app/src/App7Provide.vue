<template>
    <!--
    provide, inject를 이용한 공용데이터 사용
    부모 컴포넌트에서 자식 컴포넌트로 데이터를 전달하기 위해 props(속성)을 이용할 수 있지만 컴포넌트의 계층 구조가 복잡해지면 계층 구조를 따라 연속적으로 속성을 전달해야하는 문제가 있습니다. 
    provide, inject를 이용하면 공용데이터를 부모 컴포넌트에 제공(provide)하고 하위컴포넌트 트리상의 어느 컴포넌트에서나 필요한 데이터를 주입(inject)하여 사용하도록 하는 방법입니다.
    provide로 제공되는 객체는 반응성(Reactivity)을 제공하지 않기 때문에 제공 데이터를 변경하더라도 화면이 갱신되지 않습니다. 만일 반응성을 가지도록 하려면 Vue3의 Composition API가 지원하는 reactive, ref, computed와 같은 API를 이용해야 합니다. provide/inject 기능에서 반응성을 제공하는 것은 일반적으로 추천하지 않습니다. 
    가능하다면 애플리케이션 수준의 공용데이터를 읽기 전용으로 이용하는 것을 권장합니다.
    -->
    <div>
        <h2>상품목록</h2>
        <ProdideList :products="products" />
    </div>
</template>

<script>
import { computed } from 'vue';
import ProdideList from './components/ProvideList7.vue'

export default {
    name: "App",
    components: { ProdideList },
    data() {
        return {
            products: [
                { "prodNo": "C0001", "prodName": "아메84", "prodPrice": 1100, "checked": true },
                { "prodNo": "C0002", "prodName": "아이스아메리카노", "prodPrice": 1000, "checked": false },
                { "prodNo": "C0005", "prodName": "카푸치노", "prodPrice": 1500, "checked": true }
            ]
        }
    },
    provide() {
        return {
            // css에서 사용될 객체입니다
            icons: {
                checked: "far fa-check-circle",
                unchecked: "far fa-circle",
            },
            
            // 함수 형태로 provide! -> 전제조건 (computed 옵션으로 작성, 해당 옵션은 반드시 return값이 필요) 
            // 가격이 1500원 이상인 상품들의 개수를 반환하는 함수로써 computed옵션으로 작성합니다
            priceCount: computed(() => {
                return this.products.filter((p) => p.prodPrice >= 1500).length
            })
        }
    },
}
</script>

<style>
@import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css");
</style>