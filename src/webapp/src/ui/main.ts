import './global.css';

import App from './App.svelte';
import getMeetups from "../api/meetup/getMeetups";

getMeetups().then(value => console.log(value))

const app = new App({
    target: document.body,
});

export default app;
