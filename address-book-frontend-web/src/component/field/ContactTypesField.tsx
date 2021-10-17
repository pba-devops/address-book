import React from "react";

function ContactTypesField({ placeholder }: { placeholder: string }) {

    return(
        <div className="field">
            <input type="input" placeholder={placeholder} />
        </div>
    );
}
export default ContactTypesField