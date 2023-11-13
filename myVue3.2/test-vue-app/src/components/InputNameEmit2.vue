<template>
    <div style="border:solid 1px gray; padding:5px;">
        이름 : <input type="text" v-model="name"/>
        <!-- 사용자 정의 이벤트를 만들어서 자식 -> 부모쪽으로 전달 -->
        <!-- <button @click="$event => $emit('nameChanged', {name})">이벤트 발신</button> -->
        <!-- 이벤트를 무조건 발생시키는 것이 아닌, 유효성 검사를 통과했을 때 발생하도록 변경! -->
        <button @click="valid">이벤트 발신</button>
    </div>
</template>
<script>
    export default{
        name: "InputName",
        data(){
            return {
                name : "" // name값 초기화
            }
        },
        methods: {
            valid() {
                if(this.name.trim().length >= 3) {
                    // {a} => 프로퍼티값과 변수값이 a임 = {a:a}와 동일한 의미
                    // {b:'two'} => 프로퍼티 이름은 b, 변수값은 two임
                    this.$emit('nameChanged', {name:this.name})
                } else {
                    alert('⚠ 3자 이상 입력하세요.')
                }
            } // valid()
        }
        
    }
</script>