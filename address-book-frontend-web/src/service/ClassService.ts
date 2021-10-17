import {Contact} from "../model/contact/abstract/Contact";
import {plainToClass} from "class-transformer";
import {Acquaintance} from "../model/contact/Acquaintance";
import {Friend} from "../model/contact/Friend";
import {Family} from "../model/contact/Family";

export class ClassService {

    private static readonly TYPE_ACQUAINTANCE = 'acquaintance';
    private static readonly TYPE_FRIEND = 'friend';
    private static readonly TYPE_FAMILY = 'family';

    private constructor() {
    }

    static convert(objects: Object[]): Contact[] {
        // @ts-ignore
        const contacts: Contact[] =
            objects
                .map(object => ClassService.convertClass(object))
                .filter(object => object !== null);
        return contacts;
    }

    private static convertClass(object: Object): Contact | null {

        // const objectType = object.objectType;
        const objectType = JSON.parse(JSON.stringify(object)).objectType;

        if(objectType === ClassService.TYPE_ACQUAINTANCE) {
            return plainToClass(Acquaintance, object);
        }
        if(objectType === ClassService.TYPE_FRIEND) {
            return plainToClass(Friend, object);
        }
        if(objectType === ClassService.TYPE_FAMILY) {
            return plainToClass(Family, object);
        }
        return null;
    }
}