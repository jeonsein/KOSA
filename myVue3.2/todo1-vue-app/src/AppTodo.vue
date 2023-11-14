<template>
    <div id="app" class="container">
        <div class="card card-body bg-light">
            <div class="title">TodoList </div>
        </div>
        <div class="card card-default card-borderless">
            <div class="card-body">
                <InputTodo @add="addTodo" />

                <h4>자식컴포넌트에게 props데이터 전달하기</h4>
                <TodoListProps :todo="todo" />
                <hr>

                <h4>자식컴포넌트 메서드 직접호출하기</h4>
                <!-- <button @click="addTodo">버튼</button> -->
                <TodoListRef ref="childRef" />
            </div>
        </div>
    </div>
</template>

<script>
import TodoListProps from './components/TodoListProps.vue'
import TodoListRef from './components/TodoListRef.vue'
import InputTodo from './components/TodoInput.vue'
export default {
    name: "App",
    components: { InputTodo, TodoListProps, TodoListRef },
    data() {
        return {
            todo: ''
        }
    },
    methods: {
        addTodo(e) {
            this.todo = e.todo
            this.$refs.childRef.addTodo(this.todo)
        }
    }
}
</script>
<style scoped>
@import "https://unpkg.com/bootstrap@5.2.3/dist/css/bootstrap.min.css";

body {
    margin: 0;
    padding: 0;
    font-family: sans-serif;
}

.title {
    text-align: center;
    font-weight: bold;
    font-size: 20pt;
}

.container {
    padding: 10px 10px 10px 10px;
}


.card-borderless {
    border: 0;
    box-shadow: none;
}</style>