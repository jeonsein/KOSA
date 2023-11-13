<template lang="">
    <div>
        <h1>🎄 App3.vue 컴포넌트입니당 🎄</h1>
        <!-- <InputNamePropsEmit3 v-bind:word="parentWord"/> --> <!--단방향-->
        <InputNamePropsEmit3 v-model:word="parentWord"/> <!--양방향-->
        👶🏻 자식 컴포넌트가 보내준 데이터: {{parentWord}}
    </div>
</template>
<script>
import InputNamePropsEmit3 from "./components/InputNamePropsEmit3.vue";
export default {
    name: "App3",
    components: {InputNamePropsEmit3},
    data() {
        return {
            parentWord: ''
            // parentWord: 'hello' // 초기값 hello로 설정해주기
        }
    }, // data()
    methods: {

    }
}
</script>