<template>
    <section>
        <!-- SECTION START -->
        <router-view></router-view>
        <!-- SECTION END<hr/> -->
    </section>
</template>

<script>
export default {
    
}
</script>

<style>
section {
    /* width: 710px; */
    height: 500px;
    background-color: rgb(243, 243, 233);
}

section>div>img {
    width: 250px;
}

section>div>div>ul>li {
    list-style: none;
}
</style>