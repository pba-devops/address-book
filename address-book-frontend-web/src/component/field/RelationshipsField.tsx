import React from "react";

function RelationshipsField({ placeholder }: { placeholder: string }) {

    return(
        <div className="field">
            <input type="input" placeholder={placeholder} />
        </div>
    );
}
export default RelationshipsField