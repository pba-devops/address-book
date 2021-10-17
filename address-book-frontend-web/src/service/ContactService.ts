import {RestService} from "./abstract/RestService";

export class ContactService extends RestService {

    private static readonly ENDPOINT_GET_ALL_CONTACTS = 'http://localhost:8080/contact';

    constructor() {
        super();
    }

    getAllContact(callback: Function): void {

        super.getWithoutBody(ContactService.ENDPOINT_GET_ALL_CONTACTS,
            async (promise: Promise<any[]>) => {
                const objects = await Promise.resolve(promise);
                callback(objects);
            });
    }
}