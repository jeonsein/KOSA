<template>
    <div>
        🍓 생명주기용 메소드 실습
    </div>
</template>
<script>
import axios from 'axios';
export default {
    name: 'Lifecycle',
    methods: {
        // 여기에 Lify Cycle 메소드 작성 XX
    },
    beforeCreate() {
        console.log("하위 컴포넌트 beforeCreate() invoked.")
    },
    created() {
        console.log("하위 컴포넌트 created() invoked.")
        const url = "http://192.168.1.21:8888/back2/productlistjson?currentPage=1"
        axios
            .get(url)
            .then((response) => {
                console.log(response.data)
            })
    },
    beforeMount() {
        console.log("하위 컴포넌트 beforeMount() invoked.")
    },
    mounted() {
        console.log("하위 컴포넌트 mounted() invoked.")
    },
    beforeUnmount() {
        //  
        console.log("하위 컴포넌트 beforeUnmount() invoked.")
    },
    unmounted() {
        // 
        console.log("하위 컴포넌트 unmounted() invoked.")
    },
    beforeUpdate() {
        console.log("하위 컴포넌트 beforeUpdate() invoked.")
    },
    updated() {
        console.log("하위 컴포넌트 updated() invoked.")
    },
}
</script>