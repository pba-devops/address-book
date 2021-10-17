package pba.devops.addressbook.dataresolver;

import pba.devops.addressbook.model.error.RemoteError;

public class RemoteException extends Exception {

    final private RemoteError remoteError;

    public RemoteException(RemoteError remoteError) {
        super();
        this.remoteError = remoteError;
    }

    public RemoteError remoteError() {
        return remoteError;
    }
}
