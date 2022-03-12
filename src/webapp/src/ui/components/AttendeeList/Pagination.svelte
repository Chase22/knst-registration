<script>
    import {
        Pagination,
    } from '@smui/data-table';
    import Select, { Option } from '@smui/select';
    import IconButton from '@smui/icon-button';
    import { Label } from '@smui/common';

    export let rowsPerPage = 25
    export let currentPage = 0
    export let maxItems = 0

    $: start = currentPage * rowsPerPage;
    $: end = Math.min(start + rowsPerPage, maxItems);
    $: lastPage = Math.max(Math.ceil(maxItems / rowsPerPage) - 1, 0);
</script>

<Pagination slot="paginate">
    <svelte:fragment slot="rowsPerPage">
        <Label>Rows Per Page</Label>
        <Select variant="outlined" bind:value={rowsPerPage} noLabel>
            <Option value={10}>10</Option>
            <Option value={25}>25</Option>
            <Option value={100}>100</Option>
        </Select>
    </svelte:fragment>
    <svelte:fragment slot="total">
        {start + 1}-{end} of {maxItems}
    </svelte:fragment>

    <IconButton
            class="material-icons"
            action="first-page"
            title="First page"
            on:click={() => (currentPage = 0)}
            disabled={currentPage === 0}>first_page</IconButton
    >
    <IconButton
            class="material-icons"
            action="prev-page"
            title="Prev page"
            on:click={() => currentPage--}
            disabled={currentPage === 0}>chevron_left</IconButton
    >
    <IconButton
            class="material-icons"
            action="next-page"
            title="Next page"
            on:click={() => currentPage++}
            disabled={currentPage === lastPage}>chevron_right</IconButton
    >
    <IconButton
            class="material-icons"
            action="last-page"
            title="Last page"
            on:click={() => (currentPage = lastPage)}
            disabled={currentPage === lastPage}>last_page</IconButton
    >
</Pagination>