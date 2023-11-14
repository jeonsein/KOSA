<template>
    <Layout>
        <template v-slot:header>
            <h2>헤더 영역</h2>
        </template>
        <template v-slot:sidebar>
            <h3>사이드1</h3>
            <h3>사이드2</h3>
            <h3>사이드3</h3>
        </template>
        <template v-slot:default>
            <h1>컨텐트 영역1</h1><h1>컨텐트 영역2</h1><h1>컨텐트 영역3</h1>
            <h1>컨텐트 영역4</h1><h1>컨텐트 영역5</h1><h1>컨텐트 영역6</h1>
        </template>
        <template v-slot:footer>
            <h2>푸터영역</h2>
        </template>
    </Layout>
</template>

<script>
import Layout from './components/LayoutSlot5.vue';
export default {
    name : "App5",
    components : { Layout }
}
</script>