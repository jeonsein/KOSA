<template lang="">
    <!-- 
        root component: App2.vue
        child-component: components/InputName2.vue 
    -->
    <div>
        <h1>🐹 App2.vue 컴포넌트입니당 🐹</h1>
        <!-- Event 감지를 위해 v-on, nameChanged Event 감지 -->
        <!-- method => nameChagedHandler 감지 -->
        <InputNameEmit2 @nameChanged="nameChangedHandler"/>
        <hr>
        👶🏻 자식컴포넌트가 전달해준 데이터 : {{parentName}}
    </div>
</template>
<script>
import InputNameEmit2 from "./components/InputNameEmit2.vue";
export default {
    name: "App",
    components: {InputNameEmit2},
    data() {
        return {
            parentName: ''
        }
    }, // data()
    methods: {
        // 이벤트와 관련된 매개변수 선언
        nameChangedHandler(e) {
            // 이벤트가 발생된 곳에서 전달된 데이터(name) 값을 가져와서 현재 컴포넌트의
            // 데이터에 저장해줌
            this.parentName = e.name
        }
    }
}
</script>