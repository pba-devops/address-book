import {Stringifiable} from "../../model/contact/abstract/Stringifiable";

export abstract class RestService {

    protected constructor() {
    }

    protected getWithoutBody(endpoint: string, callback: Function) {

        const requestOption = {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        };
        fetch(endpoint, requestOption)
            .then(async res => await res.json())
            .then(data => callback(data))
            .catch(console.log);
    }

    protected getWithBody(endpoint: string, data: Stringifiable, callback: Function) {

        const requestOption = {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
            body: data.stringify()
        };
        fetch(endpoint, requestOption)
            .then(async res => await res.json())
            .then(data => callback(data))
            .catch(console.log);
    }
}