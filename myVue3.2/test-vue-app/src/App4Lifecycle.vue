<template lang="">
    <div>
        <h1>🍀 App4.vue 컴포넌트입니당 🎄</h1>
    </div>
    <Lifecycle/>
</template>

<script>
import Lifecycle from "./components/Lifecycle.vue";
export default {
    name: "App4Lifecycle",
    components: {Lifecycle},
    data() {
        return {

        }
    }, // data()
    methods: {

    },
    beforeCreate() {
        // DOM 트리 완성 전에 호출 = DOM에 접근 불가능
        console.log("beforeCreate() invoked.")
    },
    created() {
        // DOM 트리 완성 후에 호출
        console.log("created() invoked.")
    },
    beforeMount() {
        // template 태그 실행 후 호출 됨(렌더링 전)
        console.log("beforeMount() invoked.")
    },
    mounted() {
        // 초기화면에서 렌더링 후에 호출 (매번 호출 X)
        console.log("mounted() invoked.")
    },
    // 여기까지는 한 번만 호출,
    // 이후에 변경사항이 있는 경우에만
    // 아래 beforeUpdate()나, updated가 여러번 호출됨
    beforeUnmount() {
        //  
        console.log("beforeUnmount() invoked.")
    },
    unmounted() {
        // 
        console.log("unmounted() invoked.")
    },
    beforeUpdate() {
        // data(data 옵션, props 옵션) 변경되고 렌더링 전에 호출
        console.log("beforeUpdate() invoked.")
        // data 변경 가능!
    },
    updated() {
        // data(data 옵션, props 옵션) 변경되고 렌더링 후에 호출
        console.log("updated() invoked.")
        // 함수 내부에서 data를 변경할 경우,
        // beforeUpdate -> updated -> beforeUpdated로 무한 루프
        // 그렇기에 updated() 내부에서는 데이터 변경 작업 XXX
    },
}
</script>