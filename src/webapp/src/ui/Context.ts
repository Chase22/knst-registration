import { AttendeesApi, Configuration, MeetupsApi } from "../generated";

const configuration = new Configuration({ basePath: "http://localhost:8080" });

export const meetupsApi = new MeetupsApi(configuration);
export const attendeesApi = new AttendeesApi(configuration);
