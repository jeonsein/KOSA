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
    props: {
        chk: {
            type : Boolean,
            default: false
        },
        name: {
            validator(v) {
                // 전달된 데이터 타입이 string인지 확인, 이후 문자열의 자릿수가 4자리인지 확인함
                // => 부모로부터 전달된 name property가 string이고 4자리여야 true를 return함!!
                return typeof(v) !== 'string'? false : v.trim().length >= 4 ? true : false
            }
        }
    }
    props: ["p"]
}
</script>
<style lang="">
    
</style>