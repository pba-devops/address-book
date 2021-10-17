import {Contact} from "./abstract/Contact";
import {IFriend} from "./abstract/IFriend";

export class Friend extends Contact implements IFriend {

    private meeting: number;

    constructor(id: number, name: string, age: number, email: string, hair: string, phone: string, surname: string, meeting: number) {
        super(id, name, age, email, hair, phone, surname);
        this.meeting = meeting;
    }

    getFirstMeeting(): number {
        return this.meeting;
    }

    setFirstMeeting(meeting: number): void {
        this.meeting = meeting;
    }

}