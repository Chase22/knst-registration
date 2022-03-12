<script lang="ts">
    import DataTable, {Body, SortValue} from '@smui/data-table';
    import AttendeeListHeader from "./AttendeeListHeader.svelte";
    import {Attendee} from "../../../generated";
    import AttendeeListRow from "./AttendeeListRow.svelte";
    import {PageableArray} from "../../../PageableArray";
    import Pagination from "./Pagination.svelte";

    export let attendees: Array<Attendee> = []

    let pageableAttendees: PageableArray<Attendee>
    $: {
        pageableAttendees = new PageableArray<Attendee>(...attendees)
    }

    let currentPage
    let rowsPerPage
    let sort: keyof Attendee = 'id';
    let sortDirection: Lowercase<keyof typeof SortValue> = 'ascending';
</script>

<DataTable
        sortable
        bind:sort
        bind:sortDirection
>
    <AttendeeListHeader/>
    <Body>
    {#each pageableAttendees.getPage(rowsPerPage, currentPage, SortValue[sortDirection.toUpperCase()], sort) as attendee}
        <AttendeeListRow attendee={attendee}/>
    {/each}
    <Pagination bind:currentPage bind:rowsPerPage maxItems={pageableAttendees.length}/>
    </Body>
</DataTable>