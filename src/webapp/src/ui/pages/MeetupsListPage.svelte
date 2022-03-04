<script lang="ts">
    import {meetupsApi} from "../Context";

    const meetupListPromise = meetupsApi.getMeetups().then(value => value.data.content)
</script>

<div>
    {#await meetupListPromise}
        Loading...
    {:then meetups}
        {#each meetups as meetup}
            {meetup.id} {meetup.date}
        {/each}
    {/await}
</div>
