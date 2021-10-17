import React from "react";

function TextField({ placeholder }: { placeholder: string }) {

    return(
        <div className="field">
            <input type="input" placeholder={placeholder} />
        </div>
    );
}
export default TextField