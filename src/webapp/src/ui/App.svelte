<script lang="ts">
    import TopAppBar, {Title} from '@smui/top-app-bar'
    import {Route, Router} from "svelte-navigator";
    import MeetupsListPage from "./pages/MeetupsListPage.svelte";
    import MeetupPage from "./pages/MeetupPage.svelte";
    import Redirect from "./Redirect.svelte";
</script>

<main>
    <TopAppBar variant="static"><Title>KNST Anmeldung</Title></TopAppBar>
    <Router>
        <Route path="/">
            <Redirect to="/meetup"/>
        </Route>
        <Route path="meetup/*">
            <Route path="/" component={MeetupsListPage}/>
            <Route path=":id" component={MeetupPage}/>
        </Route>
    </Router>
</main>

<style>
    main {
        text-align: center;
        max-width: 240px;
        margin: 0 auto;
    }

    @media (min-width: 640px) {
        main {
            max-width: none;
        }
    }
</style>
