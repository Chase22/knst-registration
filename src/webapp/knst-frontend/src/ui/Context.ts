import { AttendeesApi, Configuration, MeetupsApi } from "generated-client";

const configuration = new Configuration({ basePath: "http://localhost:30000" });

export const meetupsApi = new MeetupsApi(configuration);
export const attendeesApi = new AttendeesApi(configuration);
