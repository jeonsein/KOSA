<template lang="">
    <div>
<ul class="pagination">
    <li class="page-item">
        <router-link 
            v-if= "start > 1"
            class="page-link" 
            :to="path + (start - 1)">
            PREV
        </router-link>
    </li>

    <template  v-for="i in (end-start+1)">
        <li v-if="(i+start-1) == currentPage"
            class="page-item active" 
            aria-current="page">
            <span class="page-link">{{i+start-1}}</span>
        </li>
        <li v-else
            class="page-item">
            <router-link 
                class="page-link"            
                :to="path+(i+start-1)">
                {{i+start-1}}
            </router-link>
        </li>
    </template>
    <li class="page-item"> 
        <router-link 
            v-if= "total > end"
            class="page-link"  
            :to="path+(end + 1)">
            NEXT
        </router-link>
    </li>
</ul>    
<!-- <template v-for="i in (endPage-startPage)"> 
    [<span :class="'pg'+(i+start)"         @click="axiosHandler(i+start)">{{(i+start)}}</span>]&nbsp;&nbsp;&nbsp
</template> -->
    </div>
</template>
<script>
export default {
    name: 'PageGroup',
    props: ['path', "currentPage", "start", "end", "total"],
    data() {
        return {
        }
    },
    methods: {
    },
}
</script>
<style lang="">
</style>