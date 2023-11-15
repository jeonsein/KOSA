<template>
    <form class="signup" @submit.prevent="signupFormSubmitHandler">    
        <label for="i">아이디 : </label>

        <!-- 아이디 입력창 -->
        <input type="text" name="id" id="i" required
            v-model="c.id"
            @focus="idFocusHandler()"/>

        <!-- 중복확인 버튼 -->
        <button type="button" @click="btDupchkClickHandler()">중복확인</button>
        <br>

        <!-- 비밀번호 입력창 -->
        <label for="p">비밀번호 :</label>
        <input type="password" name="pwd" id="p" required
            v-model="c.pwd"
            ref="p"> <!--해당 돔 객체를 직접 참조하기 위해 ref 사용-->
        <br>
        <label for="p1">비밀번호1 :</label>
        <input type="password" id="p1" required
            v-model="pwd1"
            ref="p1">
        <br>

        <!-- 이름 입력창 -->
        <label for="n">이름 :</label>
        <input type="text" name="name" id="n" required
            v-model="c.name">
        <br>

        <label for="f1">프로필: </label>
        <input type="file" name="f1" id="f1">
        <!-- 선택한 파일이 이미지 형태로 보여지는 영역 -->
        <div>
            <img class="profile">
        </div>
        <br>
        <label for="f2">자기소개: </label><input type="file" name="f2" id="f2">

        <!-- 조건에 따라 다른 클래스 속성을 위해 바인드 속성 사용 -->
        <button type="submit" :class="[isBtSignup?'btSignupShow':'btSignupHide']">가입하기</button>
    </form>

    <hr>

    <!-- 프로필 사진 -->
    <!-- <form method="post" enctype="multipart/form-data" action="http://192.168.1.21:8888/back/upload">
        <input type="file" multiple name="f1">
        <input type="text" name="t" value="tValue"><br>
        <button type="submit">첨부</button>
    </form>
    <hr> -->
    <!-- ajax로 바꿔보기 -->
    <!-- <form class="form1">
        <input type="file" multiple name="f1">
        <input type="text" name="t" value="tValue"><br>
        <button type="submit">첨부</button>
    </form> -->

    <div class="download">
        <img>
        <button>파일 다운로드</button>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "Signup",
    data() {
        return {
            // id: '',
            // pwd: '',
            pwd1: '',
            // name: '',

            c : {
                id: '',
                pwd: '',
                name: ''
            },

            // 가입 버튼 보여주기 여부
            isBtSignup: false,
        }
    },
    methods: {
        //----id입력란객체에서 focus이벤트 발생했을 때 할 일 START----
        idFocusHandler() {
            this.isBtSignup = false
        },
        //----id입력란객체에서 focus이벤트 발생했을 때 할 일 END----

        //----중복확인버튼객체에서 클릭이벤트 발생했을때 할 일 START----
        btDupchkClickHandler() {
            // get 방식이기 때문에 url에 쿼리 스트링 만들어서 보내면 됨
            const url = `${this.backURL}/iddupchk?id=${this.c.id}`

            axios
                .get(url, {withCredentials: true})
                .then(response => {
                    if(response.data.status == 1) { // 중복 X
                        this.isBtSignup = true
                        alert('사용하실 수 있는 아이디입니다.')
                    } else if(response.data.status == 0) { // 중복 O
                        alert('아이디가 중복되었습니다.')
                    }
                    
                })
                .catch(error => {
                    console.log('error: ' + error)
                })
        },
        //----중복확인버튼객체에서 클릭이벤트 발생했을때 할 일 END----

        signupFormSubmitHandler() {
            if(this.c.pwd != this.pwd1) {
                alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요.')
                
                // 참조되어 있는 객체들을 (p라는 이름으로 등록되어 있는 객체를) 찾아감
                const pwdObj = this.$refs.p // 비번

                pwdObj.focus()
                pwdObj.select()
            } else {
                const url = `${this.backURL}/signup`
                const data = this.c // 요청 전달 데이터

                axios.post(url, data)
                    .then(response => {
                        alert('결과: ' + response.data.msg)

                        if(response.data.status == 1) {
                            location.href = './'
                        }
                    })
                    .catch(error => {
                        console.log('error: ' + error)
                    })

                console.log(data)
            }
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

p {
    font-weight: bold;
}

.form_wrapper {
    width: 400px;
    height: 280px;
    border: 1px solid black;
    border-radius: 30px;
    background-color: rgb(255, 251, 251);
}

div.id_wrapper,
div.pwd_wrapper1,
div.pwd_wrapper2,
div.name_wrapper {
    margin-left: 20px;
    display: flex;
    align-items: center;
}

.id_wrapper>input {
    height: 20px;
    margin-top: 4px;
    margin-left: 25px;
}

.idDuplicateCheckBtn {
    margin-top: 4px;
    margin-left: 17px;
}

button.idDuplicateCheckBtn:hover {
    border: 1px solid rgb(0, 102, 51);
    border-radius: 5px;
    background-color: rgb(0, 102, 51);
    color: white;
    font-weight: bold;
}

.pwd_wrapper1>input {
    height: 20px;
    margin-left: 11px;
}

.pwd_wrapper2>input {
    height: 20px;
    margin-left: 5px;
}

.name_wrapper>input {
    height: 20px;
    margin-left: 45px;
}

.signupBtn_wrapper {
    margin-top: 15px;
    margin-left: 20px;
}

.btSignupShow {
    display: block;
}

.btSignupHide {
    display: none;
}

button.signupBtn:hover {
    border: 1px solid rgb(0, 102, 51);
    border-radius: 5px;
    background-color: rgb(0, 102, 51);
    color: white;
    font-weight: bold;
}

form.signup img.profile {
    width: 150px;
}
</style>