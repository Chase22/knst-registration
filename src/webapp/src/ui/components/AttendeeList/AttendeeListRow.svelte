<script lang="ts">
    import {Row, Cell} from '@smui/data-table';
    import type {Attendee} from "../../../generated";

    export let attendee: Attendee
</script>

<Row>
    <Cell numeric>{attendee.id}</Cell>
    <Cell>{attendee.username}</Cell>
    <Cell>{attendee.firstName} {attendee.lastName}</Cell>
    <Cell>{attendee.dateOfBirth}</Cell>
    <Cell>{attendee.registryDate}</Cell>
    <Cell numeric>{attendee.companies}</Cell>
</Row>
