import {Stringifiable} from "../../model/contact/abstract/Stringifiable";

export abstract class RestService {

    protected constructor() {
    }

    protected get(endpoint: string, callback: Function) {

        const requestOption = {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        };
        fetch(endpoint, requestOption)
            .then(async res => await res.json())
            .then(data => callback(data))
            .catch(console.log);
    }

    protected put(endpoint: string, data: Stringifiable, callback: Function) {

        const requestOption = {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: data.stringify()
        };
        fetch(endpoint, requestOption)
            .then(async res => await res.json())
            .then(data => callback(data))
            .catch(console.log);
    }

    protected POST(endpoint: string, data: Stringifiable, callback: Function) {

        const requestOption = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: data.stringify()
        };
        fetch(endpoint, requestOption)
            .then(async res => await res.json())
            .then(data => callback(data))
            .catch(console.log);
    }
}
