package com.dotcms.staticpublish.publisher;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.xfer.FileSystemFile;

import java.io.IOException;
import java.nio.file.Path;

public class SCPTransferMethod implements TransferMethod {

    @Override
    public void transfer(SSHClient client, Path localPath, Path remotePath) throws IOException {
        client.newSCPFileTransfer().upload(new FileSystemFile(localPath.toString()), remotePath.toString());
    }

    @Override
    public void remove(SSHClient client, Path remotePath) {
        throw new UnsupportedOperationException("SCP does not support Remove");
    }
}
