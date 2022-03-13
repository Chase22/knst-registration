/* tslint:disable */
/* eslint-disable */
/**
 * Knst Registration
 * Knst Registration
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import { Configuration } from './configuration';
import globalAxios, { AxiosPromise, AxiosInstance, AxiosRequestConfig } from 'axios';
// Some imports not used depending on template conditions
// @ts-ignore
import { DUMMY_BASE_URL, assertParamExists, setApiKeyToObject, setBasicAuthToObject, setBearerAuthToObject, setOAuthToObject, setSearchParams, serializeDataIfNeeded, toPathString, createRequestFunction } from './common';
// @ts-ignore
import { BASE_PATH, COLLECTION_FORMATS, RequestArgs, BaseAPI, RequiredError } from './base';

/**
 * 
 * @export
 * @interface Attendee
 */
export interface Attendee {
    /**
     * 
     * @type {number}
     * @memberof Attendee
     */
    'id'?: number;
    /**
     * 
     * @type {number}
     * @memberof Attendee
     */
    'meetupId': number;
    /**
     * 
     * @type {string}
     * @memberof Attendee
     */
    'username': string;
    /**
     * 
     * @type {string}
     * @memberof Attendee
     */
    'firstName'?: string;
    /**
     * 
     * @type {string}
     * @memberof Attendee
     */
    'lastName'?: string;
    /**
     * 
     * @type {string}
     * @memberof Attendee
     */
    'dateOfBirth'?: string;
    /**
     * 
     * @type {string}
     * @memberof Attendee
     */
    'registryDate': string;
    /**
     * 
     * @type {string}
     * @memberof Attendee
     */
    'attendeeStatus': AttendeeAttendeeStatusEnum;
    /**
     * 
     * @type {number}
     * @memberof Attendee
     */
    'companies': number;
}

export const AttendeeAttendeeStatusEnum = {
    Attending: 'ATTENDING',
    Unknown: 'UNKNOWN',
    NotAttending: 'NOT_ATTENDING'
} as const;

export type AttendeeAttendeeStatusEnum = typeof AttendeeAttendeeStatusEnum[keyof typeof AttendeeAttendeeStatusEnum];

/**
 * A page with attendees
 * @export
 * @interface AttendeePage
 */
export interface AttendeePage {
    /**
     * 
     * @type {Array<Attendee>}
     * @memberof AttendeePage
     */
    'content'?: Array<Attendee>;
    /**
     * 
     * @type {number}
     * @memberof AttendeePage
     */
    'number'?: number;
    /**
     * 
     * @type {number}
     * @memberof AttendeePage
     */
    'size'?: number;
    /**
     * 
     * @type {number}
     * @memberof AttendeePage
     */
    'totalElements'?: number;
    /**
     * 
     * @type {number}
     * @memberof AttendeePage
     */
    'totalPages'?: number;
}
/**
 * 
 * @export
 * @interface Meetup
 */
export interface Meetup {
    /**
     * 
     * @type {number}
     * @memberof Meetup
     */
    'id'?: number;
    /**
     * 
     * @type {boolean}
     * @memberof Meetup
     */
    'active': boolean;
    /**
     * 
     * @type {boolean}
     * @memberof Meetup
     */
    'closed': boolean;
    /**
     * 
     * @type {string}
     * @memberof Meetup
     */
    'date': string;
    /**
     * 
     * @type {boolean}
     * @memberof Meetup
     */
    'extendedRegistration': boolean;
    /**
     * 
     * @type {number}
     * @memberof Meetup
     */
    'maxAttendees'?: number | null;
    /**
     * 
     * @type {Array<Attendee>}
     * @memberof Meetup
     */
    'attendees'?: Array<Attendee>;
}
/**
 * A page with meetups
 * @export
 * @interface MeetupPage
 */
export interface MeetupPage {
    /**
     * 
     * @type {Array<Meetup>}
     * @memberof MeetupPage
     */
    'content'?: Array<Meetup>;
    /**
     * 
     * @type {number}
     * @memberof MeetupPage
     */
    'number'?: number;
    /**
     * 
     * @type {number}
     * @memberof MeetupPage
     */
    'size'?: number;
    /**
     * 
     * @type {number}
     * @memberof MeetupPage
     */
    'totalElements'?: number;
    /**
     * 
     * @type {number}
     * @memberof MeetupPage
     */
    'totalPages'?: number;
}
/**
 * A minimal page response without content
 * @export
 * @interface Page
 */
export interface Page {
    /**
     * 
     * @type {number}
     * @memberof Page
     */
    'number'?: number;
    /**
     * 
     * @type {number}
     * @memberof Page
     */
    'size'?: number;
    /**
     * 
     * @type {number}
     * @memberof Page
     */
    'totalElements'?: number;
    /**
     * 
     * @type {number}
     * @memberof Page
     */
    'totalPages'?: number;
}
/**
 * minimal Pageable query parameters
 * @export
 * @interface Pageable
 */
export interface Pageable {
    /**
     * 
     * @type {number}
     * @memberof Pageable
     */
    'page'?: number;
    /**
     * 
     * @type {number}
     * @memberof Pageable
     */
    'size'?: number;
}

/**
 * AttendeesApi - axios parameter creator
 * @export
 */
export const AttendeesApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * Adds an attendee to a given meetup
         * @param {number} meetupId The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createAttendee: async (meetupId: number, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'meetupId' is not null or undefined
            assertParamExists('createAttendee', 'meetupId', meetupId)
            const localVarPath = `/api/meetups/{meetupId}/attendee`
                .replace(`{${"meetupId"}}`, encodeURIComponent(String(meetupId)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * Deletes a given attendee
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        deleteAttendee: async (id: number, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'id' is not null or undefined
            assertParamExists('deleteAttendee', 'id', id)
            const localVarPath = `/api/attendee/{id}`
                .replace(`{${"id"}}`, encodeURIComponent(String(id)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'DELETE', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * Gets the attendee with the given Id
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getAttendeeById: async (id: number, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'id' is not null or undefined
            assertParamExists('getAttendeeById', 'id', id)
            const localVarPath = `/api/attendee/{id}`
                .replace(`{${"id"}}`, encodeURIComponent(String(id)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * Get attendes for a meetup
         * @param {number} meetupId The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getAttendeesByMeetup: async (meetupId: number, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'meetupId' is not null or undefined
            assertParamExists('getAttendeesByMeetup', 'meetupId', meetupId)
            const localVarPath = `/api/meetups/{meetupId}/attendee`
                .replace(`{${"meetupId"}}`, encodeURIComponent(String(meetupId)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * Updates a given attendee
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        updateAttendee: async (id: number, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'id' is not null or undefined
            assertParamExists('updateAttendee', 'id', id)
            const localVarPath = `/api/attendee/{id}`
                .replace(`{${"id"}}`, encodeURIComponent(String(id)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'PUT', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * AttendeesApi - functional programming interface
 * @export
 */
export const AttendeesApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = AttendeesApiAxiosParamCreator(configuration)
    return {
        /**
         * Adds an attendee to a given meetup
         * @param {number} meetupId The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async createAttendee(meetupId: number, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Attendee>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.createAttendee(meetupId, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * Deletes a given attendee
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async deleteAttendee(id: number, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.deleteAttendee(id, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * Gets the attendee with the given Id
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getAttendeeById(id: number, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Attendee>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getAttendeeById(id, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * Get attendes for a meetup
         * @param {number} meetupId The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getAttendeesByMeetup(meetupId: number, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<AttendeePage>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getAttendeesByMeetup(meetupId, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * Updates a given attendee
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async updateAttendee(id: number, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Attendee>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.updateAttendee(id, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
    }
};

/**
 * AttendeesApi - factory interface
 * @export
 */
export const AttendeesApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = AttendeesApiFp(configuration)
    return {
        /**
         * Adds an attendee to a given meetup
         * @param {number} meetupId The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createAttendee(meetupId: number, options?: any): AxiosPromise<Attendee> {
            return localVarFp.createAttendee(meetupId, options).then((request) => request(axios, basePath));
        },
        /**
         * Deletes a given attendee
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        deleteAttendee(id: number, options?: any): AxiosPromise<void> {
            return localVarFp.deleteAttendee(id, options).then((request) => request(axios, basePath));
        },
        /**
         * Gets the attendee with the given Id
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getAttendeeById(id: number, options?: any): AxiosPromise<Attendee> {
            return localVarFp.getAttendeeById(id, options).then((request) => request(axios, basePath));
        },
        /**
         * Get attendes for a meetup
         * @param {number} meetupId The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getAttendeesByMeetup(meetupId: number, options?: any): AxiosPromise<AttendeePage> {
            return localVarFp.getAttendeesByMeetup(meetupId, options).then((request) => request(axios, basePath));
        },
        /**
         * Updates a given attendee
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        updateAttendee(id: number, options?: any): AxiosPromise<Attendee> {
            return localVarFp.updateAttendee(id, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * AttendeesApi - object-oriented interface
 * @export
 * @class AttendeesApi
 * @extends {BaseAPI}
 */
export class AttendeesApi extends BaseAPI {
    /**
     * Adds an attendee to a given meetup
     * @param {number} meetupId The id of the Meetup
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AttendeesApi
     */
    public createAttendee(meetupId: number, options?: AxiosRequestConfig) {
        return AttendeesApiFp(this.configuration).createAttendee(meetupId, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Deletes a given attendee
     * @param {number} id The id of the Meetup
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AttendeesApi
     */
    public deleteAttendee(id: number, options?: AxiosRequestConfig) {
        return AttendeesApiFp(this.configuration).deleteAttendee(id, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Gets the attendee with the given Id
     * @param {number} id The id of the Meetup
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AttendeesApi
     */
    public getAttendeeById(id: number, options?: AxiosRequestConfig) {
        return AttendeesApiFp(this.configuration).getAttendeeById(id, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Get attendes for a meetup
     * @param {number} meetupId The id of the Meetup
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AttendeesApi
     */
    public getAttendeesByMeetup(meetupId: number, options?: AxiosRequestConfig) {
        return AttendeesApiFp(this.configuration).getAttendeesByMeetup(meetupId, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Updates a given attendee
     * @param {number} id The id of the Meetup
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AttendeesApi
     */
    public updateAttendee(id: number, options?: AxiosRequestConfig) {
        return AttendeesApiFp(this.configuration).updateAttendee(id, options).then((request) => request(this.axios, this.basePath));
    }
}


/**
 * MeetupsApi - axios parameter creator
 * @export
 */
export const MeetupsApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @param {Meetup} meetup The meetup to be created
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createMeetup: async (meetup: Meetup, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'meetup' is not null or undefined
            assertParamExists('createMeetup', 'meetup', meetup)
            const localVarPath = `/api/meetups`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(meetup, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * Delete a meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        deleteMeetup: async (id: number, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'id' is not null or undefined
            assertParamExists('deleteMeetup', 'id', id)
            const localVarPath = `/api/meetups/{id}`
                .replace(`{${"id"}}`, encodeURIComponent(String(id)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'DELETE', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * Getting a single meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getMeetupById: async (id: number, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'id' is not null or undefined
            assertParamExists('getMeetupById', 'id', id)
            const localVarPath = `/api/meetups/{id}`
                .replace(`{${"id"}}`, encodeURIComponent(String(id)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @param {Pageable} [pagable] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getMeetups: async (pagable?: Pageable, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            const localVarPath = `/api/meetups`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            if (pagable !== undefined) {
                localVarQueryParameter['pagable'] = pagable;
            }


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * Updating a single meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        updateMeetup: async (id: number, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'id' is not null or undefined
            assertParamExists('updateMeetup', 'id', id)
            const localVarPath = `/api/meetups/{id}`
                .replace(`{${"id"}}`, encodeURIComponent(String(id)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'PUT', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * MeetupsApi - functional programming interface
 * @export
 */
export const MeetupsApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = MeetupsApiAxiosParamCreator(configuration)
    return {
        /**
         * 
         * @param {Meetup} meetup The meetup to be created
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async createMeetup(meetup: Meetup, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Meetup>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.createMeetup(meetup, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * Delete a meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async deleteMeetup(id: number, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.deleteMeetup(id, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * Getting a single meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getMeetupById(id: number, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Meetup>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getMeetupById(id, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @param {Pageable} [pagable] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getMeetups(pagable?: Pageable, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<MeetupPage>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getMeetups(pagable, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * Updating a single meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async updateMeetup(id: number, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Meetup>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.updateMeetup(id, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
    }
};

/**
 * MeetupsApi - factory interface
 * @export
 */
export const MeetupsApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = MeetupsApiFp(configuration)
    return {
        /**
         * 
         * @param {Meetup} meetup The meetup to be created
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createMeetup(meetup: Meetup, options?: any): AxiosPromise<Meetup> {
            return localVarFp.createMeetup(meetup, options).then((request) => request(axios, basePath));
        },
        /**
         * Delete a meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        deleteMeetup(id: number, options?: any): AxiosPromise<void> {
            return localVarFp.deleteMeetup(id, options).then((request) => request(axios, basePath));
        },
        /**
         * Getting a single meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getMeetupById(id: number, options?: any): AxiosPromise<Meetup> {
            return localVarFp.getMeetupById(id, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @param {Pageable} [pagable] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getMeetups(pagable?: Pageable, options?: any): AxiosPromise<MeetupPage> {
            return localVarFp.getMeetups(pagable, options).then((request) => request(axios, basePath));
        },
        /**
         * Updating a single meetup
         * @param {number} id The id of the Meetup
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        updateMeetup(id: number, options?: any): AxiosPromise<Meetup> {
            return localVarFp.updateMeetup(id, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * MeetupsApi - object-oriented interface
 * @export
 * @class MeetupsApi
 * @extends {BaseAPI}
 */
export class MeetupsApi extends BaseAPI {
    /**
     * 
     * @param {Meetup} meetup The meetup to be created
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof MeetupsApi
     */
    public createMeetup(meetup: Meetup, options?: AxiosRequestConfig) {
        return MeetupsApiFp(this.configuration).createMeetup(meetup, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Delete a meetup
     * @param {number} id The id of the Meetup
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof MeetupsApi
     */
    public deleteMeetup(id: number, options?: AxiosRequestConfig) {
        return MeetupsApiFp(this.configuration).deleteMeetup(id, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Getting a single meetup
     * @param {number} id The id of the Meetup
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof MeetupsApi
     */
    public getMeetupById(id: number, options?: AxiosRequestConfig) {
        return MeetupsApiFp(this.configuration).getMeetupById(id, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @param {Pageable} [pagable] 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof MeetupsApi
     */
    public getMeetups(pagable?: Pageable, options?: AxiosRequestConfig) {
        return MeetupsApiFp(this.configuration).getMeetups(pagable, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Updating a single meetup
     * @param {number} id The id of the Meetup
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof MeetupsApi
     */
    public updateMeetup(id: number, options?: AxiosRequestConfig) {
        return MeetupsApiFp(this.configuration).updateMeetup(id, options).then((request) => request(this.axios, this.basePath));
    }
}

