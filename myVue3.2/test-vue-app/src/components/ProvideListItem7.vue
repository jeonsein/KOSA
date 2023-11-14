<template>
    <li>
        <i :class="p.checked ? icons.checked : icons.unchecked"></i> {{p.prodName}}
    </li>
</template>
<script>
    export default {
        name: "ProvideListItem",
        inject: ["icons"],
        props: ["p"],
    }
</script>