import {IContact} from "./IContact";
import {Namable} from "./Namable";

export abstract class Contact extends Namable implements IContact {

    private age: number;
    private email: string;
    private hair: string;
    private phone: string;
    private surname: string;

    protected constructor(id: number, name: string, age: number, email: string, hair: string, phone: string, surname: string) {
        super(id, name);
        this.age = age;
        this.email = email;
        this.hair = hair;
        this.phone = phone;
        this.surname = surname;
    }

    getAge(): number {
        return this.age;
    }

    getEmail(): string {
        return this.email;
    }

    getHair(): string {
        return this.hair;
    }

    getPhone(): string {
        return this.phone;
    }

    getSurname(): string {
        return this.surname;
    }

    setAge(age: number): void {
        this.age = age;
    }

    setEmail(email: string): void {
        this.email = email;
    }

    setHair(hair: string): void {
        this.hair = hair;
    }

    setPhone(phone: string): void {
        this.phone = phone;
    }

    setSurname(surname: string): void {
        this.surname = surname;
    }

    stringify(): string {
        return JSON.stringify(this);
    }

}