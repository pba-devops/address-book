import React from "react";
import {Contact} from "../model/contact/abstract/Contact";

function Contacts({ contacts }: { contacts: Contact[] }) {

    return(
        <div>{ contacts.map(contact => <div>{contact.constructor.name + ' -- ' + contact.stringify()}</div>) }</div>
    );
}
export default Contacts