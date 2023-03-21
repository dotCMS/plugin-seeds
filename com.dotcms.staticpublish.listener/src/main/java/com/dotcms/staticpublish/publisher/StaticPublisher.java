package com.dotcms.staticpublish.publisher;

import com.dotcms.publisher.business.DotPublisherException;
import com.dotcms.publisher.business.EndpointDetail;
import com.dotcms.publisher.business.PublishAuditAPI;
import com.dotcms.publisher.business.PublishAuditHistory;
import com.dotcms.publisher.business.PublishAuditStatus.Status;
import com.dotcms.publisher.business.PublisherAPI;
import com.dotcms.publisher.business.PublisherQueueJob;
import com.dotcms.publisher.endpoint.bean.PublishingEndPoint;
import com.dotcms.publishing.PublisherConfig;
import com.dotcms.staticpublish.PluginProperties;
import com.dotmarketing.util.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.Response;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.sftp.SFTPException;
import net.schmizz.sshj.userauth.keyprovider.PKCS8KeyFile;
import net.schmizz.sshj.userauth.method.AuthPublickey;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

public class StaticPublisher {
    private List<String> hosts;
    private AuthPublickey authPublickey;
    private String username;
    private String remotePath;
    private PublisherConfig config;
    private PublishingEndPoint endPoint;
    private final PublishAuditAPI publishAuditAPI;
    private final PublisherAPI publisherAPI;
    private PublishAuditHistory currentStatusHistory;

    public StaticPublisher(PublisherConfig config, PublishingEndPoint endPoint){
        this.config          = config;
        this.endPoint        = endPoint;
        this.publishAuditAPI = PublishAuditAPI.getInstance();
        this.publisherAPI    = PublisherAPI.getInstance();
    }

    public void publishSCP(Path localPath) {
        publish(localPath, new SCPTransferMethod());
    }

    public void publishSFTP(final Path localPath) {
        publish(localPath, new SFTPTransferMethod());
    }

    private void publish(final Path localPath, TransferMethod transferMethod) {
        final List<String> hosts = getHosts();
        final SSHClient ssh      = new SSHClient();
        List<String> failedHosts = new ArrayList<>();
        EndpointDetail detail    = new EndpointDetail();

        try {
            ssh.loadKnownHosts();
            ssh.addHostKeyVerifier(new PromiscuousVerifier());
            this.currentStatusHistory = publishAuditAPI.getPublishAuditStatus(config.getId()).getStatusPojo();

            for (String host : hosts) {
                //publisherEndPointAPI.
                try {
                    Logger.info(this, "Connecting to ssh..");
                    ssh.connect(host,Integer.parseInt(PluginProperties.getProperty("host.port","22")));
                    Logger.info(this, "Connected to ssh..");
                    Logger.info(this, "Auth to ssh..");
                    ssh.auth(getUsername(), getAuthPublickey());

                    Logger.info(this, "Remote Bundle Path: " + getRemotePath());

                    transferMethod.transfer(ssh, localPath, Paths.get(getRemotePath()));
                    Logger.info(this, "Finished Transfering Files");

                } catch (IOException e) {
                    Logger.error(this, "IOException error: ", e);
                    failedHosts.add(host);
                } catch (Exception e) {
                    Logger.error(this, "Exception Error: ", e);
                    failedHosts.add(host);
                } finally {
                    try {
                        ssh.disconnect();
                    } catch (IOException e) {
                        Logger.error(this, "Unable to disconnect from SSH.", e);
                    }
                }
            }
        } catch (IOException e) {
            Logger.error(this, "Error loading ssh known hosts", e);
            detail.setStatus(Status.FAILED_TO_PUBLISH.getCode());
            detail.setInfo("Error sending bundle to " + String.join(",", hosts));
        } catch (DotPublisherException e) {
            Logger.error(this, "Error loading publish audit status", e);
            detail.setStatus(Status.FAILED_TO_PUBLISH.getCode());
            detail.setInfo("Error sending bundle to " + String.join(",", hosts));
        } catch (Exception e) {
            Logger.error(this, "Error publishing bundle", e);
            detail.setStatus(Status.FAILED_TO_PUBLISH.getCode());
            detail.setInfo("Error sending bundle to " + String.join(",", hosts));
        } finally {
            try {
                updatePublishAudit(hosts, failedHosts, detail);
            } catch (DotPublisherException e) {
                Logger.error(this, "Error updating publish audit status", e);
            }
        }
    }

    public void removeSFTP(final String absolutePath) {
        final List<String> hosts = getHosts();
        List<String> failedHosts = new ArrayList<>();
        final SSHClient ssh      = new SSHClient();
        EndpointDetail detail    = new EndpointDetail();
        SFTPClient sftp          = null;

        try {
            ssh.loadKnownHosts();
            this.currentStatusHistory = publishAuditAPI.getPublishAuditStatus(config.getId()).getStatusPojo();
        } catch (IOException e) {
            Logger.error(this, "Error loading ssh known hosts", e);
            detail.setStatus(Status.FAILED_TO_PUBLISH.getCode());
            detail.setInfo("Error sending bundle to " + String.join(",", hosts));
        } catch (DotPublisherException e) {
            Logger.error(this, "Error loading publish audit status", e);
            detail.setStatus(Status.FAILED_TO_PUBLISH.getCode());
            detail.setInfo("Error sending bundle to " + String.join(",", hosts));
        } catch (Exception e) {
            Logger.error(this, "Error publishing bundle", e);
            detail.setStatus(Status.FAILED_TO_PUBLISH.getCode());
            detail.setInfo("Error sending bundle to " + String.join(",", hosts));
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
                failedHosts.add(host);
            } catch (IOException e) {
                Logger.error(this, "IOException error: ", e);
                failedHosts.add(host);
            } catch (Exception e) {
                Logger.error(this, "Exception Error: ", e);
                failedHosts.add(host);
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

        try {
            updatePublishAudit(hosts, failedHosts, detail);
        } catch (DotPublisherException e) {
            Logger.error(this, "Error updating publish audit status", e);
        }
    }

    private void updatePublishAudit(List<String> hosts, List<String> failedHosts,
            EndpointDetail detail) throws DotPublisherException {
        if ((detail.getInfo() == null || detail.getInfo().isEmpty()) && failedHosts.isEmpty()){
            detail.setStatus(Status.SUCCESS.getCode());
            detail.setInfo("Bundle successfully sent to " + String.join(",", hosts));

            currentStatusHistory
                    .addOrUpdateEndpoint(endPoint.getGroupId(), endPoint.getId(), detail);
            publishAuditAPI.updatePublishAuditStatus(config.getId(), Status.SUCCESS,
                    currentStatusHistory);

            publisherAPI.deleteElementsFromPublishQueueTable(config.getId());
        }else{

            if (!failedHosts.isEmpty()) {
                detail.setStatus(Status.FAILED_TO_PUBLISH.getCode());
                detail.setInfo("Error sending bundle to " + String.join(",", failedHosts));
            }

            currentStatusHistory
                    .addOrUpdateEndpoint(endPoint.getGroupId(), endPoint.getId(), detail);

            if (currentStatusHistory.getNumTries() >= PublisherQueueJob.MAX_NUM_TRIES) {
                publisherAPI.deleteElementsFromPublishQueueTable(config.getId());
                publishAuditAPI
                        .updatePublishAuditStatus(config.getId(), Status.FAILED_TO_PUBLISH,
                                currentStatusHistory);
            } else {
                if (failedHosts.size() < hosts.size()) {
                    publishAuditAPI.updatePublishAuditStatus(config.getId(),
                            Status.FAILED_TO_SEND_TO_SOME_GROUPS, currentStatusHistory);
                } else {
                    publishAuditAPI.updatePublishAuditStatus(config.getId(),
                            Status.FAILED_TO_SEND_TO_ALL_GROUPS, currentStatusHistory);
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
