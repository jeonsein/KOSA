<template>
    
    <div class="row">
        <div class="col">
            <ul class="list-group">          
                <TodoListItem  
                    v-for="item in todolist"  
                    :todoItem="item" 
                    @delete-todo="$emit('delete-todo',  item.id )"                   
                    />                   
            </ul>
        </div>
    </div>
</template>
<script>
import TodoListItem from "./TodoListItem.vue"
export default {
    name: "TodoList",
    components:{TodoListItem},
    data() {
        return {
            // todolist: //목록과 바인드될 프로퍼티 
                    // []
        }
    },
    props: ["todolist"],
    methods:{
        // deletedHandler(){
        //     this.$emit()
        // }
    }
}

</script>