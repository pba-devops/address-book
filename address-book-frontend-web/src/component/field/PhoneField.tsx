import React from "react";

function PhoneField({ placeholder }: { placeholder: string }) {

    return(
        <div className="field">
            <input type="input" placeholder={placeholder} />
        </div>
    );
}
export default PhoneField