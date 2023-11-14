<template>
    <div class="row">
        <div class="col">
            <hr>
            <ul class="list-group">
                <!-- <TodoListItem v-for="todoItem in todoList" :key="todoItem.id" 
                    :todoItem="todoItem" /> 
                -->
                <TodoListItem v-for="todoItem in todoList" :key="todoItem.id" :todoItem="todoItem" @deleted="deletedTodo"
                    @toggleCompleted="toggleCompleted" />
            </ul>            
        </div>
    </div>
</template>

<script>
import TodoListItem from './TodoListItem.vue';
export default {
    name: 'todoList',
    components: { TodoListItem },
    data() {
        return {
            todoList: //목록과 바인드될 프로퍼티 
                [{ id: 1, todo: '내용1', completed: false },
                { id: 2, todo: '내용2', completed: true },
                { id: 3, todo: '내용3', completed: false },
                ]
                ,
            
        }
    },    
    // props: ["todo"],
    // beforeUpdate(){}, // 라이프 사이클 메소드 (없어도 무방)

    methods: {
        addTodo(newTodo) {
            const ids = this.todoList.map(newTodo => { return newTodo.id })
            const maxId = ids.length == 0 ? 0 : Math.max(...ids)
            this.todoList.push({ id: maxId + 1, todo: newTodo, completed: false })           
        },
        deletedTodo(id) {
            let index = this.todoList.findIndex((item) => id === item.id);
            this.todoList.splice(index, 1);
        },
        toggleCompleted(id) {
            let index = this.todoList.findIndex((item) => id === item.id);
            this.todoList[index].completed = !this.todoList[index].completed;
        }
    },
    watch : {
		// todo(newTodo, oldTodo) {
        // 	console.log('변경 감지');
        //     alert("watch this.todo=" + this.todo )
        //     if (this.todo != 'undefined' && this.todo !== '') {
        //         this.addTodo(newTodo)

        //     }
        // }
        
    }
}
</script>