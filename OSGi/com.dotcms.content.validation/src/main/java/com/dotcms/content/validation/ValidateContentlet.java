package com.dotcms.content.validation;


import com.dotmarketing.portlets.contentlet.business.DotContentletValidationException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.workflows.actionlet.PublishContentActionlet;
import com.dotmarketing.portlets.workflows.actionlet.SaveContentActionlet;
import com.dotmarketing.portlets.workflows.actionlet.WorkFlowActionlet;
import com.dotmarketing.portlets.workflows.model.WorkflowActionClass;
import com.dotmarketing.portlets.workflows.model.WorkflowActionClassParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowActionFailureException;
import com.dotmarketing.portlets.workflows.model.WorkflowActionletParameter;
import com.dotmarketing.portlets.workflows.model.WorkflowProcessor;
import java.util.List;
import java.util.Map;

public class ValidateContentlet extends WorkFlowActionlet {

    private static final long serialVersionUID = 1L;

    @Override
    public List<WorkflowActionletParameter> getParameters() {
        return null;
    }

    @Override
    public String getName() {
        return "Content Validation Actionlet Example";
    }

    @Override
    public String getHowTo() {
        return "If the content has 'nosave' in the title, it can't be saved.  If the title has `nopublish` in it, the content can't be published.";
    }


    
    
    @Override
    public void executePreAction(WorkflowProcessor processor, Map<String, WorkflowActionClassParameter> params)
                    throws WorkflowActionFailureException, DotContentletValidationException {


        Contentlet content = processor.getContentlet();
        List<WorkflowActionClass> actions =processor.getActionClasses();
        
        
        if(content.validateMe() && isSaving(actions) && content.getTitle().toLowerCase().contains("nosave")) {
            throw new DotContentletValidationException("You cannot save a contentlet with `nosave` in its title");
        }
        
        
        if(content.validateMe() && isPublishing(actions) && content.getTitle().toLowerCase().contains("nopublish")) {
            throw new DotContentletValidationException("You cannot publish a contentlet with `nopublish` in its title");
        }
        
        
        
    }



    @Override
    public void executeAction(WorkflowProcessor processor, Map<String, WorkflowActionClassParameter> arg1)
                    throws WorkflowActionFailureException {
        

        
    }

    private boolean isSaving(final List<WorkflowActionClass> actions) {
        return actions.stream().anyMatch(wac -> wac.getActionlet() instanceof SaveContentActionlet);
    }
    
    
    private boolean isPublishing(final List<WorkflowActionClass> actions) {
        return actions.stream().anyMatch(wac -> wac.getActionlet() instanceof PublishContentActionlet);
    }


}
