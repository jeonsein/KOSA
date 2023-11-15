import 'bootstrap/dist/css/bootstrap.css'

import { createApp } from 'vue'
import App from './App.vue'
// import router from './router'
// 기본 파일인 index.js를 사용하는 경우에는 디렉토리명만 임포트가 가능함!
import router from './router/index.js'

const app = createApp(App)

//router에 등록된 컴포넌트들은 전역컴포넌트로 등록되기 때문에
// 앱 내부의 모든 컴포넌트에서 사용할 수 있습니다
app.use(router)

// 전역변수 선언
app.config.globalProperties.backURL='http://192.168.1.21:8888/back2'

app.mount('#app')
