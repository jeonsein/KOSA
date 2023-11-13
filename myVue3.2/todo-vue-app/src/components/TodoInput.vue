<template>
    <div class="row mb-3">
        <div class="col">
            <div class="input-group">
                <input id="msg" type="text" class="form-control" name="msg" placeholder="할일을 여기에 입력!" v-bind:value="todo"
                @input="$emit('update:todo', $event.target.value)"
                @keyup.enter="add" />
                <span class="btn btn-primary input-group-addon" v-on:click="add">추가</span>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        name:"TodoInput",
        data() {

        },
        methods: {
            add() {
                this.$emit('add', this.todo);
                // this.todo = ''; // 입력값을 초기화
                // const ids = this.todolist.map(todo => { return todo.id })
				// const maxId = ids.length == 0 ? 0 : Math.max(...ids)
				// this.todolist.push({ id: maxId + 1, todo: this.todo, completed: false })
			},
        },
        props: ["todo"]
    }
</script>