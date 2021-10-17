import React from "react";
// @ts-ignore
import {ModalManager} from 'react-dynamic-modal';
import AddContact from "./AddContact";

function Title({ title }: { title: string }) {

    return(
        <div>
            <div className="heading">{title}</div>
            <div className="heading">+</div>
        </div>
    );
}
export default Title