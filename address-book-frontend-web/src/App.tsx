import React from 'react';
import './App.css';
import {ContactService} from "./service/ContactService";
import {Contact} from "./model/contact/abstract/Contact";

import 'reflect-metadata';
import {ClassService} from "./service/ClassService";
import Contacts from "./component/Contacts";
import Title from "./component/Title";

function App() {

    const contactService = new ContactService();
    const [contacts, setContacts] = React.useState<Contact[]>([]);

    // MOUNT PROCESS --
    React.useEffect(
        () => contactService.getAllContact((objects: Object[]) => loadContacts(objects)), [])

    const loadContacts = (objects: Object[]) => {
        // @ts-ignore
        const contacts: Contact[] = ClassService.convert(objects);
        setContacts(contacts);
    }

    return (
        <div className="App">
            <Title title="Address Book" />
            <Contacts contacts={contacts} />
        </div>
    );
}

export default App;
