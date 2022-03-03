import type { AxiosResponse } from "axios";
import { Configuration, MeetupPage, MeetupsApi } from "../../generated";

export default function getMeetups(): Promise<AxiosResponse<MeetupPage, any>> {
  return new MeetupsApi(
    new Configuration({ basePath: "http://localhost:8080" })
  ).getMeetups(null, {});
}
