<template>
    <!-- 로고 -->
    <header>
        <!-- 아래 코드는Path에 해당하는 라우터 영역의 view를 보여주는 것임! 새로고침 XX -->
        <!-- <router-link to="/"><img src="/images/logo.png" alt="로고"></router-link> -->
        
        <img src="/images/logo.png" alt="로고" @click="logoClickHandler"/>
    </header>

    <!-- 메뉴바 -->
    <nav>
        <ul>
            <!-- <li v-show="loginedId === ''"> 
                로그인을 이미 만들어 놓고조건에 만족했을 때 display=none을 풀기 때문에 로그인이 보임 -->
            <!-- <li v-if="loginedId === ''">
                v-if는 조건에 만족했을 때 해당 객체를 돔 트리에 만들어냄 -->
            <li v-if="loginedId === ''"> <!-- 로그인 안된 경우 -->
                <router-link class="nav-link" to="/login">로그인</router-link>
            </li>
            <li v-if="loginedId === ''"> <!-- 로그인 안된 경우 -->
                <router-link class="nav-link" to="/signup">가입</router-link>
            </li>

            <!-- <li><a href="#" class="intro">자기소개서</a></li> -->
            <!-- <li><img class="profile"></li> -->

            <!-- 자료형까지 구분하기 위해 === 와 !== 사용 -->
            <li v-if="loginedId !== ''"> <!-- 로그인이 된 경우 -->
                <a href="#" class="logout" @click="logoutClickHandler()">
                    {{loginedId}}님 로그아웃
                </a>
            </li>

            <!-- <li><a href="#" class="productlist">상품목록</a></li> -->
            <!-- <li><a href="#" class="cartlist">장바구니목록</a></li> -->
            <!-- <li><a href="#" class="orderlist">주문목록</a></li> -->
        </ul>
    </nav>
</template>

<script>
import axios from 'axios'
export default {
    name: 'Header',
    // 모델의 프로퍼티가 될 객체들을 선언 
    data() {
        return {
            loginedId: '',
        }
    },
    created() {
        const loginedId = localStorage.getItem('loginedId')

        if(loginedId != null) {
            this.loginedId = loginedId
        }
    },
    methods: {
        // 〓〓 로고 img 객체에서 클릭이벤트가 발생했을 때 할 일 START 〓〓)
        logoClickHandler() {
            location.href = '/'
        },
        // 〓〓 로고 img 객체에서 클릭이벤트가 발생했을 때 할 일 END 〓〓)

        logoutClickHandler() {
            const url = `${this.backURL}/logout`
            const loginedId = localStorage.getItem('loginedId')

            axios
                .get(url, loginedId, {withCredentials: true})
                .then(response => {
                    console.log(response)
                    alert('로그아웃 성공')
                    localStorage.removeItem('loginedId')
                    location.href = "./"
                })
                .catch(error => {
                    console.error('에러', error);
                });
        }
    },
}
</script>

<style scoped>
a {
    text-decoration: none;
    color: black;
    font-weight: 500;
}

header>img {
    width: 45px;
    height: 45px;
    display: inline-block;
    float: left;
    margin-top: 5px;
    margin-left: 13px;
}

nav {
    width: 590px;
    display: inline-block;
    background-color: rgb(246, 245, 239);
    margin-left: 37px;
    padding-bottom: 25px;
    padding-right: 25px;
}

nav>ul>li {
    float: left;
    list-style-type: none;
    /*ul요소의 점 제거*/
    text-align: left;
    margin-left: 10px;
    margin-right: 20px;

}

nav>ul>li>a:hover {
    color: rgb(102, 153, 0);
    font-weight: bold;
    text-decoration: underline;
}

nav>ul>li>img.profile {
    width: 100px;
}
</style>