<template>
    <div class="card card-body">
        <h2>MemberInfo</h2>
        <p>요청 전체경로 : {{ $route.fullPath }}</p>
        <p>요청URI경로 : {{ $route.path }}</p>
        <p>요청전달데이터 : {{ $route.params }}</p>
        <p>요청쿼리스트링 : {{ $route.query }}</p>
    </div>
</template>

<script>
export default {
  name : "MemberInfo",  
}
</script>