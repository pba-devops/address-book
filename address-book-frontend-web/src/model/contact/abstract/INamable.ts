import {IIdentifiable} from "./IIdentifiable";

export interface INamable extends IIdentifiable {

    getName(): string;
    setName(name: string): void;

}