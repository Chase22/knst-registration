<script lang="ts">
    import {meetupsApi} from "../Context";
    import CircularProgress from '@smui/circular-progress';

    export let id: number = 0

    const meetupPromise = meetupsApi.getMeetupById(id)
</script>

<div>
    {#await meetupPromise}
        <div style="display: flex; justify-content: center">
            <CircularProgress style="height: 64px; width: 64px;" indeterminate/>
        </div>
    {:then response}
        {response.data.attendees}
    {/await}
</div>
