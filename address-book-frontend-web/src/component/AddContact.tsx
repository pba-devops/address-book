import React from 'react';
// @ts-ignore
import {Effect, Modal, ModalManager} from 'react-dynamic-modal';
import ContactTypesField from "./field/ContactTypesField";
import TextField from "./field/TextField";
import NumberField from "./field/NumberField";
import HairsField from "./field/HairsField";
import EmailField from "./field/EmailField";
import PhoneField from "./field/PhoneField";
import RelationshipsField from "./field/RelationshipsField";


const TYPE_TO_ADD: string = 'typeToAdd';
const NAME_TO_ADD: string = 'nameToAdd';
const SURNAME_TO_ADD: string = 'surnameToAdd';
const EMAIL_TO_ADD: string = 'emailToAdd';
const AGE_TO_ADD: string = 'ageToAdd';
const HAIR_TO_ADD: string = 'hairToAdd';
const PHONE_TO_ADD: string = 'phoneToAdd';
const MEETING_TO_ADD: string = 'meetingToAdd';
const RELATIONSHIP_TO_ADD: string = 'emailToAdd';

class AddContact {

    render() {
        return (
            <Modal
                effect={Effect.SlideFromRight}
                style={{
                    overlay: {
                        backgroundColor : 'rgba(0, 0, 0, 0.4)'
                    },
                    content: {
                        position                : 'relative',
                        margin                  : '15% auto',
                        width                   : '90%',
                        // border                  : '1px solid rgba(0, 0, 0, 1)',
                        border                  : '1px solid rgba(155,155, 155, 0)',
                        background              : 'rgba(0, 0, 0, 0)',
                        overflow                : 'auto',
                        borderRadius            : '4px',
                        outline                 : 'none',
                        boxShadow               : '0 5px 10px rgba(0, 0, 0, .3)',
                    }
                }}>
                <div>
                    <form>
                        <div className="addContact">Add Contact</div>
                        <div>
                            <ContactTypesField  key={TYPE_TO_ADD}           placeholder={TYPE_TO_ADD} />
                            <TextField          key={NAME_TO_ADD}           placeholder={NAME_TO_ADD} />
                            <TextField          key={SURNAME_TO_ADD}        placeholder={SURNAME_TO_ADD} />
                            <NumberField        key={AGE_TO_ADD}            placeholder={AGE_TO_ADD} />
                            <HairsField         key={HAIR_TO_ADD}           placeholder={HAIR_TO_ADD} />
                            <EmailField         key={EMAIL_TO_ADD}          placeholder={EMAIL_TO_ADD} />
                            <PhoneField         key={PHONE_TO_ADD}          placeholder={PHONE_TO_ADD} />
                            <NumberField        key={MEETING_TO_ADD}        placeholder={MEETING_TO_ADD} />
                            <RelationshipsField key={RELATIONSHIP_TO_ADD}   placeholder={RELATIONSHIP_TO_ADD} />
                        </div>
                    </form>
                </div>
            </Modal>
        )
    }
}
export default AddContact