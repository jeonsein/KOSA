<template>
    <div>
        🗨 검색어를 입력하세요:
        <input type="text" 
                v-bind:value="word"
                @input="$emit('update:word',$event.target.value)"
                />
                <!-- input 이벤트 발생 : 키보드에서 입력이 발생했을 때 -->
                <!-- $emit(이벤트 이름, 데이터)-->
                <!-- 자식쪽에서 부모쪽으로 데이터 전달할 때는 이벤트 이름을 update로 기술해야 함! -->
    </div>
</template>
<script>
export default {
    name: "InputNamePropsEmit3",
    props: ["word"]
}
</script>