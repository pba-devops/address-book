import {IContact} from "./IContact";

export interface IFriend extends IContact {

    getFirstMeeting(): number;
    setFirstMeeting(firstMeeting: number): void;

}