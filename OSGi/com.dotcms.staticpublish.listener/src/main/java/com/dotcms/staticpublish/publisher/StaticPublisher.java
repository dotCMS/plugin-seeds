package com.dotcms.staticpublish.publisher;

import com.dotcms.staticpublish.PluginProperties;
import com.dotmarketing.util.Logger;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.Response;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.sftp.SFTPException;
import net.schmizz.sshj.userauth.keyprovider.PKCS8KeyFile;
import net.schmizz.sshj.userauth.method.AuthPublickey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StaticPublisher {
    private List<String> hosts;
    private AuthPublickey authPublickey;
    private String username;
    private String remotePath;

    public void publishSCP(Path localPath) {
        List<String> hosts = getHosts();
        SSHClient ssh = new SSHClient();
        try {
            ssh.loadKnownHosts();
        } catch (IOException e) {
            Logger.error(this, "Error loading ssh known hosts", e);
        }

        for (String host : hosts) {
            try {
                ssh.connect(host);
                ssh.auth(getUsername(), getAuthPublickey());

                // Present here to demo algorithm renegotiation - could have just put this before connect()
                // Make sure JZlib is in classpath for this to work
                ssh.useCompression();

                Logger.info(this, "Remote Bundle Path: ");

                final TransferMethod transferMethod = new SCPTransferMethod();
                transferMethod.transfer(ssh, localPath, Paths.get(getRemotePath()));
            } catch (IOException e) {
                Logger.error(this, "IOException error: ", e);

            } catch (Exception e) {
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

    public void publishSFTP(final Path localPath) {
        final List<String> hosts = getHosts();
        final SSHClient ssh = new SSHClient();
        SFTPClient sftp = null;

        try {
            ssh.loadKnownHosts();
        } catch (IOException e) {
            Logger.error(this, "Error loading ssh known hosts", e);
        }

        for (String host : hosts) {
            try {
                ssh.connect(host);
                ssh.auth(getUsername(), getAuthPublickey());

                Logger.info(this, "Remote Bundle Path: ");

                final TransferMethod transferMethod = new SFTPTransferMethod();
                transferMethod.transfer(ssh, localPath, Paths.get(getRemotePath()));

            } catch (IOException e) {
                Logger.error(this, "IOException error: ", e);
            } catch (Exception e) {
                Logger.error(this, "Exception Error: ", e);
            } finally {
                try {
                    if (sftp != null) {
                        sftp.close();
                    }
                    ssh.disconnect();
                } catch (IOException e) {
                    Logger.error(this, "Unable to disconnect from SSH.", e);
                }
            }
        }
    }

    public void removeSFTP(final String absolutePath) {
        final List<String> hosts = getHosts();
        final SSHClient ssh = new SSHClient();
        SFTPClient sftp = null;

        try {
            ssh.loadKnownHosts();
        } catch (IOException e) {
            Logger.error(this, "Error loading ssh known hosts", e);
        }

        for (String host : hosts) {
            try {
                ssh.connect(host);
                ssh.auth(getUsername(), getAuthPublickey());

                Logger.info(this, "Remote Bundle Path: ");

                final TransferMethod transferMethod = new SFTPTransferMethod();
                transferMethod.remove(ssh, Paths.get(getRemotePath(), absolutePath));

            } catch (SFTPException e) {
                if (Response.StatusCode.NO_SUCH_FILE.equals(e.getStatusCode())) {
                    Logger.warn(this, "File not found remotely, maybe previously deleted: " + absolutePath);
                } else {
                    Logger.error(this, "SFTPException error: ", e);
                }
            } catch (IOException e) {
                Logger.error(this, "IOException error: ", e);
            } catch (Exception e) {
                Logger.error(this, "Exception Error: ", e);
            } finally {
                try {
                    if (sftp != null) {
                        sftp.close();
                    }
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

    private String getRemotePath() {
        if (remotePath == null) {
            remotePath = PluginProperties.getProperty("remote.path");
        }

        return remotePath;
    }

}
