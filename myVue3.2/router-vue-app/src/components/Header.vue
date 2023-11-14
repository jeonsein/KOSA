<template>
    <!--navbar-expand-md : 브라우저의 가로폭이 중간 크기인 경우 펼쳐지도록 합니다
        mt-2 : 위, 바깥쪽여백을 2만큼 지정
    -->
    <nav class="navbar navbar-expand-md bg-dark navbar-dark mt-2"><!--navigation bar 스타일-->
        <span class="navbar-brand">햄버거메뉴</span>
        <!--버튼이 클릭되면 아래 div :class="navClass"영역이 show/hide토글됩니다-->
        <button class="navbar-toggler" type="button" @click="changeIsNavShow">
            <span class="navbar-toggler-icon"></span>
        </button>

        
        <div :class="navClass" @click="changeIsNavShow">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <!--클릭하여 이동할 수 있는 기능을 제공합니다-->
                    <router-link class="nav-link" to="/" >홈</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/about">소개</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/members">멤버</router-link>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
// import { reactive, computed } from 'vue';

export default {
    // setup() {
    //     const state = reactive({
    //         isNavShow: false  
    //     })
    //     const navClass = computed(() => state.isNavShow 
    //          ? "collapse navbar-collapse show" : "collapse navbar-collapse")
    //     const changeIsNavShow = () => {
    //         state.isNavShow = !state.isNavShow;
    //     } 
        
    //     return { state, changeIsNavShow, navClass };
    // }
    data(){
        return {
            isNavShow: false
        }
    },
    computed:{        
        navClass(){
            return this.isNavShow
            ? "collapse navbar-collapse show" : "collapse navbar-collapse"
        }        
    },
    methods:{
        changeIsNavShow () {
                // state.isNavShow = !state.isNavShow;
                this.isNavShow = !this.isNavShow
        } 
    }
}
</script>