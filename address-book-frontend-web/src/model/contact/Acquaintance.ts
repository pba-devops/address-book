import {Contact} from "./abstract/Contact";

export class Acquaintance extends Contact {

    constructor(id: number, name: string, age: number, email: string, hair: string, phone: string, surname: string) {
        super(id, name, age, email, hair, phone, surname);
    }
}