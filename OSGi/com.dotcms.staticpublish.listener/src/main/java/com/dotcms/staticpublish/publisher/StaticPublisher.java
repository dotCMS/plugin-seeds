package com.dotcms.staticpublish.publisher;

import com.dotcms.staticpublish.PluginProperties;
import com.dotmarketing.util.Logger;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.userauth.keyprovider.PKCS8KeyFile;
import net.schmizz.sshj.userauth.method.AuthPublickey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class StaticPublisher {
    private List<String> hosts;
    private AuthPublickey authPublickey;
    private String username;

    public void publish(Path localPath, Path remotePath) {
        List<String> hosts = getHosts();
        SSHClient ssh = new SSHClient();
        try {
            ssh.loadKnownHosts();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String host : hosts) {
            try {
                ssh.connect(host);
                ssh.auth(getUsername(), getAuthPublickey());

                // Present here to demo algorithm renegotiation - could have just put this before connect()
                // Make sure JZlib is in classpath for this to work
                ssh.useCompression();

                final TransferMethod transferMethod = new SCPTransferMethod();
                transferMethod.transfer(ssh, localPath, remotePath);
            } catch (IOException e) {
                Logger.error(this, "IOException error: ", e);

            } catch (Exception e){
                Logger.error(this, "Exception Error: ", e);
            } finally {
                try {
                    ssh.disconnect();
                } catch (IOException e) {
                    Logger.error(this, "Unable to disconnect from SSH.", e);
                }
            }
        }
    }

    private List<String> getHosts() {
        if (hosts == null) {
            String[] hostsArray = PluginProperties.getPropertyArray("hosts");
            hosts = Arrays.asList(hostsArray);
        }

        return hosts;
    }

    private AuthPublickey getAuthPublickey() {
        if (authPublickey == null) {
            PKCS8KeyFile keyFile = new PKCS8KeyFile();
            keyFile.init(new File(PluginProperties.getProperty("key.file.path")));
            authPublickey = new AuthPublickey(keyFile);
        }

        return authPublickey;
    }

    private String getUsername() {
        if (username == null) {
            username = PluginProperties.getProperty("ssh.user");
        }

        return username;
    }

}
