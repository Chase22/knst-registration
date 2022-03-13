<script lang="ts">
    import {navigate} from "svelte-navigator";

    export let to: string = ""

    navigate(to, {replace: true})
</script>