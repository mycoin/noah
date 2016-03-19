package org.ionnic.biz.subversion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNHandler {

    protected final Log logger = LogFactory.getLog(getClass());

    private String repURL;

    private String repPath;

    private String username;

    private String password;

    private String jsonPath;

    private String xmlPath;

    public void svnCheckin() {

        SVNRepository repository;

        SVNClientManager ourClientManager = SVNClientManager.newInstance();

        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(repURL));

            ISVNAuthenticationManager authManager =
                    SVNWCUtil.createDefaultAuthenticationManager(username, password);
            repository.setAuthenticationManager(authManager);

            logger.info("Repository Root: " + repository.getRepositoryRoot(true));
            logger.info("Repository UUID: " + repository.getRepositoryUUID(true));

            logger.info("Repository Latest Revision: " + repository.getLatestRevision());

            ourClientManager.setAuthenticationManager(authManager);

            SVNCommitClient commitClient = ourClientManager.getCommitClient();
            SVNDiffClient diffClient = ourClientManager.getDiffClient();

            File xmlfile = new File(xmlPath);
            File jsonfile = new File(jsonPath);
            File[] xmlfilearray = {xmlfile};
            File[] jsonfilearray = {jsonfile};

            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            diffClient.doDiff(xmlfile, SVNRevision.UNDEFINED, SVNRevision.BASE, SVNRevision.WORKING,
                    SVNDepth.INFINITY, true, outputStream, null);

            if (outputStream.size() > 0) {
                commitClient.doCommit(xmlfilearray, false, outputStream.toString(), false, true);
                logger.info("XML Checked in at " + new Date());
            }

            diffClient.doDiff(jsonfile, SVNRevision.UNDEFINED, SVNRevision.BASE, SVNRevision.WORKING,
                    SVNDepth.INFINITY, true, outputStream, null);

            if (outputStream.size() > 0) {
                commitClient.doCommit(jsonfilearray, false, outputStream.toString(), false, true);
                logger.info("Json SVN Checked in at " + new Date());
            }

        } catch (SVNException e) {
            logger.error(e.toString());
        } finally {
            ourClientManager.dispose();
        }
    }

    public void svnCheckout() {

        File svnTempDir = new File(repPath);
        if (svnTempDir.exists()) {
            return;
        }

        SVNRepository repository;

        try {
            //initiate the reporitory from the url
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(repURL));

            //create authentication data
            ISVNAuthenticationManager authManager =
                    SVNWCUtil.createDefaultAuthenticationManager(username, password);
            repository.setAuthenticationManager(authManager);

            //need to identify latest revision
            long latestRevision = repository.getLatestRevision();

            //create client manager and set authentication
            SVNClientManager ourClientManager = SVNClientManager.newInstance();
            ourClientManager.setAuthenticationManager(authManager);

            //use SVNUpdateClient to do the export
            SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
            updateClient.setIgnoreExternals(false);

            updateClient.doCheckout(repository.getLocation(), svnTempDir,
                    SVNRevision.create(latestRevision), SVNRevision.create(latestRevision), true);

        } catch (SVNException e) {
            logger.error(e.toString());
        } finally {
            logger.info("resources/svn_temp checked outs");
        }
    }

}