<template>
    HOME 파일 경로 - @/pages/Home.vue
    <p>전체 경로: {{$route.fullPath}}</p>
    <p>URI 경로: {{$route.path}}</p>
    <p>요청 전달 데이터: {{$route.params}}</p>
    <p>요청 쿼리 스트링: {{$route.query}}</p>
</template>

<script>
export default {
    name: "Home"
}
</script>
