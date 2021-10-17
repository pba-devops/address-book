import {INamable} from "./INamable";
import {Stringifiable} from "./Stringifiable";

export interface IContact extends INamable, Stringifiable {

    getSurname(): string;
    setSurname(surname: string): void;

    getPhone(): string;
    setPhone(phone: string): void;

    getEmail(): string;
    setEmail(email: string): void;

    getAge(): number;
    setAge(age: number): void;

    getHair(): string;
    setHair(hair: string): void;

}