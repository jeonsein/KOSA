// import 구문
import { createRouter, createWebHistory } from 'vue-router'
// 경로 별칭 : @은 'src/' 경로를 의미함
import Home from '@/pages/Home.vue'
import Login from '@/pages/Login.vue'
import Signup from '@/pages/Signup.vue'

const router = createRouter({
    history: createWebHistory(), // 라우팅 모드 설정
    // 라우터에 등록할 컴포넌트들
    routes: [
        { path: '/', component: Home },
        { path: '/login', component: Login },
        { path: '/signup', component: Signup }
    ]
})
export default router;