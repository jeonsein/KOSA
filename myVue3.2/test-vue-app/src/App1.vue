<template lang="">
    <div>
        <h1>💖 App1.vue 컴포넌트입니다 💖</h1>
        <!-- 
        <Checkbox1/>
        <Checkbox1/>
        <Checkbox1/>
        -->
        <ul>
            <!-- 
                부모 컴포넌트(App1.vue)를 자식 컴포넌트(Checkbox1.vue)에게 전달해주어야 함
                1. 자식 컴포넌트에서 props 설정 해주기
            -->
            <!-- <Checkbox1 v-for="p in products" v-bind:checked="p.checked"/> -->
            <!-- <Checkbox1 
                v-for = "p in products"
                v-bind:name="p.prodName"
                v-bind:chk="p.checked"
            /> -->
            <Checkbox1 v-for="p in products" v-bind:p="p"/>
        </ul>
    </div>
</template>
<script>
// Checkbox1.vue 임포트하기 (c는 임의로 정한 이름)
import Checkbox1 from "./components/Checkbox1.vue";
export default {
    name: "App1",
    components: { Checkbox1 },
    data() {
        return {
            products: [
                { prodNo: 'C0001', prodName: '아메리카노', checked: false },
                { prodNo: 'C0002', prodName: '쿨라임피지오', checked: true },
                { prodNo: 'C0003', prodName: '캐모마일릴렉서', checked: false }
            ]
        }
    }
}
</script>
<style lang="">
    
</style>