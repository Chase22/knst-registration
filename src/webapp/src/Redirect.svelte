<script lang="ts">
    import {navigate} from "svelte-navigator";

    export let to: String

    navigate(to, {replace: true})
</script>