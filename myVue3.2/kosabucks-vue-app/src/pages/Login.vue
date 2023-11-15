<template>
    <!-- 폼의 기본 submit 이벤트를 처리하려고 하기 때문에 막아주기 위해서 .prevent -->
    <form @submit.prevent="loginFormSubmitHandler">
        <div class="form_wrapper">

            <!-- 아이디 -->
            <div class="id_wrapper">
                <p>아이디: </p>
                <input type="text" placeholder="아이디를 입력하세요." id="id" name="id" required 
                v-model="id">
            </div>

            <!-- 비밀번호 -->
            <div class="pwd_wrapper">
                <p>비밀번호: </p>
                <input type="password" placeholder="비밀번호를 입력하세요." id="pwd" name="pwd" required 
                v-model="pwd">
            </div>

            <!-- 아이디 저장 - 체크박스 -->
            <div class="id_stored_wrapper">
                <input type="checkbox" checked 
                v-model="checked">
                <p>아이디저장</p>
            </div>

            <!-- 로그인 버튼 -->
            <div class="loginBtn_wrapper">
                <button class="loginBtn">로그인</button>
                <!-- <button class="loginBtn" @click.prevent="login">로그인</button> -->
                <!-- button type="submit"과 같음 -->
            </div>

        </div>
    </form>
</template>

<script>
import axios from 'axios';
export default {
    name: "Login",
    data() {
        return {
            id: '',
            pwd: '',
            checked: true
        }
    },
    methods: {
        loginFormSubmitHandler(e) {
            if (this.checked) {
                localStorage.setItem('savedId', this.id)
            } else {
                localStorage.removeItem('savedId')
            } // if-else

            // 전역 변수로 사용하게 만들어주기
            const url = `${this.backURL}/login`
            
            // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 START 〓〓〓
            const data = `id=${this.id}&pwd=${this.pwd}`

            // 3번째 인자로 옵션 설정 (객체 형태로 withCredentials 설정)
            axios.post(url, data, {withCredentials: true})
                .then(response => {
                    console.log('data: ' + data)
                    console.log('response: ' + response)

                    if(response.data.status == 0) {
                        alert(response.data.msg)
                        alert('로그인실패')
                
                    } else if (response.data.status == 1) {
                        alert(response.data.msg)
                        alert('로그인성공')

                        localStorage.setItem("loginedId", this.id)
                        location.href = "./"
                    } // if-else
                })
                .catch(error => { // 네트워크 요청 실패
                    console.error('에러', error);
                });
                e.preventDefault()
            // 〓〓〓 form 객체에서 submit이벤트가 발생했을 때 할 일 END 〓〓〓
        }, // loginFormSubmitHandler()
    },
    created() {
        const savedId = localStorage.getItem('savedId')

        if(savedId != null) {
            this.id = savedId
        }
    }
}
</script>

<style scoped>
* {
    box-sizing: border-box;
}

a {
    text-decoration: none;
}

.form_wrapper {
    width: 300px;
    height: 170px;
    border: 1px solid black;
    background-color: rgb(248, 244, 238);
}

/* 아이디, 비밀번호 입력창 영역 */
div.id_wrapper,
div.pwd_wrapper {
    margin-left: 20px;
    display: flex;
    align-items: center;
}

/* 아이디 입력창 */
.id_wrapper>input {
    height: 20px;
    margin-top: 4px;
    margin-left: 20px;
}

/* 비번 입력창 */
.pwd_wrapper>input {
    height: 20px;
    margin-left: 4px;
}

/* 아이디저장 체크박스 영역 */
div.id_stored_wrapper {
    margin-left: 20px;
    display: flex;
    align-items: center;
    float: left;
}

/* 로그인 버튼 영역 */
div.loginBtn_wrapper {
    display: flex;
    align-items: center;
    margin-top: 23px;
}

/* 로그인 버튼 */
button.loginBtn {
    position: fixed;
    display: flex;
    align-items: center;
    text-align: left;
    margin-left: 90px;
}

/* 로그인 버튼 호버 효과 */
button.loginBtn:hover {
    background-color: rgb(0, 102, 51);
    color: white;
}
</style>