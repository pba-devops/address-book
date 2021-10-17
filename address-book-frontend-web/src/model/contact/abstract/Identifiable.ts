import {IIdentifiable} from "./IIdentifiable";

export abstract class Identifiable implements IIdentifiable {

    private readonly id: number;

    protected constructor(id: number) {
        this.id = id;
    }

    getId(): number {
        return this.id;
    }

}