package com.dotmarketing.osgi.actionlet.actionlets;

import com.dotcms.api.system.event.message.MessageSeverity;
import com.dotcms.api.system.event.message.SystemMessageEventUtil;
import com.dotcms.api.system.event.message.builder.SystemMessageBuilder;
import com.dotmarketing.portlets.workflows.actionlet.Actionlet;
import com.dotmarketing.portlets.workflows.actionlet.WorkFlowActionlet;
import com.dotmarketing.portlets.workflows.model.WorkflowActionClassParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowActionletParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowProcessor;

import java.util.Arrays;

/**
 * This sub action allows the user to send message as part of the pipe-line in action.
 * The message could be just a raw or html, also can support velocity interpolation on both of them.
 *
 * @author jsanca
 * @version 5.0
 * @since May 26, 2018
 */
@Actionlet
public class HelloActionlet extends WorkFlowActionlet {

    protected final SystemMessageEventUtil systemMessageEventUtil =
            SystemMessageEventUtil.getInstance();

    private static final long serialVersionUID = 1177885642438262884L;

    private static final String ACTIONLET_NAME  = "Osgi Hello Message";
    private static final String HOW_TO          =
            "This actionlet will say hello";

    @Override
    public java.util.List<WorkflowActionletParameter> getParameters() {
        return null;
    }

    @Override
    public String getName() {
        return ACTIONLET_NAME;
    }

    @Override
    public String getHowTo() {
        return HOW_TO;
    }


    @Override
    public void executeAction(final WorkflowProcessor processor,
            final java.util.Map<String, WorkflowActionClassParameter> params) {

        this.systemMessageEventUtil.pushMessage(
                new SystemMessageBuilder().setMessage("Hello there")
                         .setLife(8l).setSeverity(MessageSeverity.INFO).create(),
                Arrays.asList(processor.getUser().getUserId()));
    }

}
