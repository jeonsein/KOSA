<template lang="">
    <div>
        <h1>첫번째탭</h1>
        <span>keep-alive 캐싱테스트해보세요. include인 경우 시간과 cnt값이 바뀌지 않습니다</span>
        <div>첫번째탭 실행된 시간:{{ (new Date().toTimeString())}}</div>
        <div class="result">
    cnt:{{ cnt }}<br>
    <button @click="increase">cnt증가</button>
</div>
    </div>
</template>
<script>
export default {
    name: "Tab1",
    data() {
        return { 
            cnt: 0,
        }
    }
    ,methods : {
        increase(){
            this.cnt++;
        }
    },
    created(){
        console.log("tab1 created")
    },
    mounted(){
        console.log("tab1 mounted")
    },
    unmounted(){
        console.log("tab1 unmounted")
    },
}
</script>
<style lang="">
</style>