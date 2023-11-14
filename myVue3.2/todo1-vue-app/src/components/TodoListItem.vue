<template>
    <li class="list-group-item" 
        :class="{ 'list-group-item-success': todoItem.completed } "
        @click="$emit('toggleCompleted', todoItem.id)" >
        <span class="pointer" :class="{ 'todo-done': todoItem.completed }">
            {{todoItem.todo}} {{ todoItem.completed ? "(완료)" : "" }}
        </span>
        <span class="float-end badge bg-secondary pointer"
            @click.stop="$emit('deleted', todoItem.id)">삭제</span>            
    </li>
    
</template>

<script>
    export default {
        name : "TodoListItem",
        props : {
            todoItem : { type : Object, required: true }
        },
        emits : ["deleted", "toggleCompleted"], 
        
    }
</script>
<style scoped>
.todo-done { text-decoration: line-through; }
.pointer { cursor:pointer; }

</style>