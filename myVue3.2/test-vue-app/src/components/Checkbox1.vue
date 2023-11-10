<template lang="">
        <!-- 
            checked 프로퍼티가 input 박스에 바인딩되려면
            단방향 = v-bind:value
            양방향 = v-model
        -->
        <!-- <li><input type="checkbox" v-bind:checked="chk" v-bind:prodName="name"/>{{name}}</li> -->
        <li><input type="checkbox" v-bind:checked="p.checked"/> {{p.prodName}}</li>
        <input type="text" v-model="p.prodName">
</template>
<script>
// 옵션 객체 export
export default {
    name: 'Checkbox1',
    data() {
        // checked: false
    },
    // 부모 컴포넌트를 자식 컴포넌트에게 전달!
    // props는 무조건 문자열로! 여러개 전달될 수 있기 때문에 배열로 설정해줌
    // props: ["chk","name"]
    props: ["p"]
}
</script>
<style lang="">
    
</style>