import {Relationship} from "./Relationship";

export interface IFamily {

    getRelationship(): Relationship;
    setRelationship(relationship: Relationship): void;

}