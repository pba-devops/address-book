import {Identifiable} from "./Identifiable";
import {INamable} from "./INamable";

export abstract class Namable extends Identifiable implements INamable {

    private name: string;

    protected constructor(id: number, name: string) {
        super(id);
        this.name = name;
    }

    getName(): string {
        return this.name;
    }

    setName(name: string): void {
        this.name = name;
    }


}