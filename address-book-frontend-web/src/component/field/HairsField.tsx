import React from "react";

function HairsField({ placeholder }: { placeholder: string }) {

    return(
        <div className="field">
            <input type="input" placeholder={placeholder} />
        </div>
    );
}
export default HairsField