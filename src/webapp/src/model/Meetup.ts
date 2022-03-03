import type Attendee from "./Attendee";

export default interface Meetup {
    id: number
    active: Boolean
    closed: Boolean
    date: Date
    extendedRegistration: Boolean
    maxAttendees: number
    attendees: Array<Attendee>
}