import {Contact} from "./abstract/Contact";
import {IFamily} from "./abstract/IFamily";
import {Relationship} from "./abstract/Relationship";
import {Type} from "class-transformer";

export class Family extends Contact implements IFamily {

    // @ts-ignore
    @Type(() => Relationship)
    private relationship: Relationship;


    constructor(id: number, name: string, age: number, email: string, hair: string, phone: string, surname: string, relationship: Relationship) {
        super(id, name, age, email, hair, phone, surname);
        this.relationship = relationship;
    }

    getRelationship(): Relationship {
        return this.relationship;
    }

    setRelationship(relationship: Relationship): void {
        this.relationship = relationship;
    }

}