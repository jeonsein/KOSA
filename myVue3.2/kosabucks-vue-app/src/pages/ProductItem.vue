<template lang="">
    <div class="product">
        <ul>
        <li><img :src="'../images/'+p.prodNo +'.jpg'" :alt="p.prodName"></li>
        <li><span>{{p.prodName}}</span></li>
        </ul>
    </div>
</template>
<script>
export default {
    name: 'ProductItem',
    props:["p"],
    data(){
        return {
            // p1:{
            //     prodNo:'C0001',
            //     prodName: 'Americano'
            // }
        }
    }
}
</script>
<style scoped>
    div.product {
    width: 200px;
    display: inline-block;
}

div.product>ul {
    list-style-type: none;
    padding-left: 0px;
}

div.product>ul>li>img {
    /* width:100px;                 */
    width: 80%;
}

div.product>ul>li>span {
    display: inline-block;
    width: 80%;
    text-align: center;
}

</style>