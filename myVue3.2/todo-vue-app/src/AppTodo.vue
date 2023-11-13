<template>
    <div class="card card-body bg-light">
        
			<div class="title">:: Todolist App</div>           
            
            <TodoInput v-model:todo="todo" v-on:add="add" />
            
            <!-- 자식 컴포넌트가 보내 준 데이터 : {{ todo }} -->
            <!-- <TodoList :todolist="todolist" v-on:del="del" v-on:update="updateItem" /> -->
            <!-- 자식 컴포넌트가 보내 준 데이터 : {{ todolist }} -->
            <!-- <span class="btn btn-primary input-group-addon" v-on:click="add">추가</span> -->

            <TodoList :todolist="todolist" />
        </div>
</template>
<script>
import TodoInput from "./components/TodoInput.vue"
import TodoList from "./components/TodoList.vue"

export default {
    name: "AppTodo",
    components: { TodoInput, TodoList },
    data() {
        return {
            todo: '', //텍스트박스에 사용자가 입력한 내용과 바인드될 프로퍼티
            todolist: [{id:1, todo:'aaa', completed:false},
                        {id:1, todo:'aaa', completed:false},
            ],
        } // return
    }, // data
    methods: {
        add() {
            const ids = this.todolist.map(todo => todo.id);
            const maxId = ids.length === 0 ? 0 : Math.max(...ids);
            this.todolist.push({ id: maxId + 1, todo: this.todo, completed: false });

            this.todo= '';
        },
        del(id) {
            const index = this.todolist.findIndex(todo => todo.id === id);
            this.todolist.splice(index, 1);
        },
        updateItem(id) {
            const index = this.todolist.findIndex(todo => todo.id === id);
            this.todolist[index].completed = !this.todolist[index].completed;
        },
    },
} // export

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

.todo-done {
    text-decoration: line-through;
}

.container {
    padding: 10px 10px 10px 10px;
}

.card-borderless {
    border: 0;
    box-shadow: none;
}

.pointer {
    cursor: pointer;
}

</style>