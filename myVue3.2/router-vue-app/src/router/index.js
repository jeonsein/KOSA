// import 구문
import { createRouter, createWebHistory } from 'vue-router'
// 경로 별칭 : @은 'src/' 경로를 의미함
import Home from '@/pages/Home.vue'
import About from '@/pages/About.vue'
import Members from '@/pages/Members.vue'
import MemberInfo from '@/pages/MemberInfo.vue'

const router = createRouter({
    history: createWebHistory(),
    // 라우터에 등록할 컴포넌트들
    routes: [
        { path: '/', component: Home },
        { path: '/about', component: About },
        { path: '/members', component: Members },

        // 동적 라우팅 방법
        // { path: '/members', component: Members }, 이렇게 XX
        {path: '/members/:id', component: MemberInfo}
    ]
})
export default router;