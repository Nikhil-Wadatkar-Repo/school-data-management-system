import React, { useContext, useState } from "react";
import { MyContext } from "./MyContext";

function AlertMessage() {
  const {
    text,
    setText,
    alert,
    setAlert,
    alertMessage,
    setAlertMessage,
    messageType,
    setMessageType,
    alertTitle,
    setAlertTitle,
  } = useContext(MyContext);
  return (
    <div>
      <>
        <div>
          <div className={"alert " + messageType} role="alert">
            <h4 className="alert-heading">{alertTitle}!</h4>
            <hr></hr>
            <p class="mb-0">{alertMessage}</p>
          </div>
        </div>
      </>
    </div>
  );
}

export default AlertMessage;
