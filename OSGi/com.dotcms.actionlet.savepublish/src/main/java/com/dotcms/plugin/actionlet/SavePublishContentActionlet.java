package com.dotcms.plugin.actionlet;

import com.dotcms.business.WrapInTransaction;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.factories.PublishFactory;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.htmlpageasset.model.HTMLPageAsset;
import com.dotmarketing.portlets.structure.model.Structure;
import com.dotmarketing.portlets.workflows.actionlet.Actionlet;
import com.dotmarketing.portlets.workflows.actionlet.PublishContentActionlet;
import com.dotmarketing.portlets.workflows.actionlet.WorkFlowActionlet;
import com.dotmarketing.portlets.workflows.model.WorkflowActionClassParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowActionFailureException;
import com.dotmarketing.portlets.workflows.model.WorkflowActionletParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowProcessor;
import com.dotmarketing.util.Logger;
import com.dotmarketing.util.UtilMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Save and Publish the content in just one action
 * @author jsanca
 */
@Actionlet(save = true, publish = true)
public class SavePublishContentActionlet extends WorkFlowActionlet {


    public SavePublishContentActionlet() {

    }

    @Override
    public String getName() {
        return "Save & Publish content ";
    }

    @Override
    public String getHowTo() {

        return "This actionlet will checkin and publish the content.";
    }

    @Override
    @WrapInTransaction
    public void executeAction(final WorkflowProcessor processor,
                              final Map<String, WorkflowActionClassParameter> params) throws WorkflowActionFailureException {

        this.executeSaveAction(processor, params);
        this.executePublishAction(processor, params);
    }

    private void executePublishAction(final WorkflowProcessor processor, final Map<String, WorkflowActionClassParameter> params) throws WorkflowActionFailureException {

        try {

            Contentlet contentlet = processor.getContentlet();
            int structureType = contentlet.getStructure().getStructureType();

            if (processor.getContentlet().isArchived()) {
                APILocator.getContentletAPI().unarchive(processor.getContentlet(), processor.getUser(), false);
            }

            //First verify if we are handling a HTML page
            if (structureType == Structure.STRUCTURE_TYPE_HTMLPAGE) {

                HTMLPageAsset htmlPageAsset = APILocator.getHTMLPageAssetAPI().fromContentlet(contentlet);

                //Get the un-publish content related to this HTMLPage
                List relatedNotPublished = new ArrayList();
                /*
                Returns the list of unpublished related content for this HTML page where
                the user have permissions to publish that related content.
                 */
                relatedNotPublished = PublishFactory.getUnpublishedRelatedAssetsForPage(htmlPageAsset, relatedNotPublished, true, processor.getUser(), false);
                //Publish the page and the related content
                PublishFactory.publishHTMLPage(htmlPageAsset, relatedNotPublished, processor.getUser(), false);

            } else {
                APILocator.getContentletAPI().publish(processor.getContentlet(), processor.getUser(), false);
            }

        } catch (Exception e) {
            Logger.error(PublishContentActionlet.class, e.getMessage(), e);
            throw new WorkflowActionFailureException(e.getMessage(),e);
        }

    }

    private void executeSaveAction(final WorkflowProcessor processor,
                                   final Map<String, WorkflowActionClassParameter> params) throws WorkflowActionFailureException {

        try {

            final Contentlet contentlet =
                    processor.getContentlet();

            Logger.debug(this,
                    "Saving the content of the contentlet: " + contentlet.getIdentifier());

            final boolean    isNew              = this.isNew (contentlet);
            final Contentlet checkoutContentlet = isNew? contentlet:
                    APILocator.getContentletAPI().checkout(contentlet.getInode(), processor.getUser(), false);

            if (!isNew) {

                final String inode = checkoutContentlet.getInode();
                APILocator.getContentletAPI().copyProperties(checkoutContentlet, contentlet.getMap());
                checkoutContentlet.setInode(inode);
            }

            checkoutContentlet.setProperty(Contentlet.WORKFLOW_IN_PROGRESS, Boolean.TRUE);

            final Contentlet contentletNew = (null != processor.getContentletDependencies())?
                    APILocator.getContentletAPI().checkin(checkoutContentlet, processor.getContentletDependencies()):
                    APILocator.getContentletAPI().checkin(checkoutContentlet, processor.getUser(), false);

            processor.setContentlet(contentletNew);

            Logger.debug(this,
                    "content version already saved for the contentlet: " + contentlet.getIdentifier());
        } catch (Exception e) {

            Logger.error(this.getClass(),e.getMessage(),e);
            throw new WorkflowActionFailureException(e.getMessage(),e);
        }
    }

    private boolean isNew(final Contentlet contentlet) {
        return !UtilMethods.isSet(contentlet.getIdentifier()) || contentlet.isNew();
    }

    @Override
    public  List<WorkflowActionletParameter> getParameters() {

        return null;
    }
}