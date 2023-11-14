<template lang="">
    <div>
        <h1>두번째 탭</h1>
        <div>두번째탭 실행된 시간:{{new Date().toTimeString()}}</div>
        <div class="result">
    <input type="text" v-model="message" placeholder="메시지를 입력하세요"><br>
    메시지:{{message}}
</div>
    </div>
</template>
<script>
export default {
    name: "Tab2",
    data() {
        return {
            message: ""
        }
    },

    created() {
        console.log("tab2 created")
    },
    mounted() {
        console.log("tab2 mounted")
    },
    unmounted() {
        console.log("tab2 unmounted")
    },
    
}
</script>
<style lang="">
    
</style>