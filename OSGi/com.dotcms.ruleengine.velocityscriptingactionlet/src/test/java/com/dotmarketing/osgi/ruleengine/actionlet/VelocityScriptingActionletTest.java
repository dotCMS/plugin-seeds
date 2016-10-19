package com.dotmarketing.osgi.ruleengine.actionlet;

import com.dotcms.repackage.com.google.common.collect.ImmutableMap;
import com.dotmarketing.portlets.rules.model.ParameterModel;
import com.dotmarketing.portlets.rules.model.RuleAction;
import com.dotmarketing.util.Logger;
import com.dotmarketing.util.UtilMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.dotmarketing.osgi.ruleengine.actionlet.VelocityScriptingActionlet.REQUEST_KEY;
import static com.dotmarketing.osgi.ruleengine.actionlet.VelocityScriptingActionlet.INPUT_VELOCITY_SCRIPT_KEY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

public class VelocityScriptingActionletTest {
   
    
    @Test
    public void testGeneralConfiguration() throws Exception {
    	VelocityScriptingActionlet actionlet = new VelocityScriptingActionlet();
        assertThat(actionlet.getI18nKey(), is("api.system.ruleengine.actionlet.VelocityScripting"));
        assertThat("There is only two parameters.", actionlet.getParameterDefinitions().size(), is(2));
        assertThat(actionlet.getId(), is("VelocityScriptingActionlet"));
    }

    @Test(dataProvider = "velocityCases")
    public void testValidateParameters(SimpleCase theCase) throws Exception {
    	VelocityScriptingActionlet actionlet = new VelocityScriptingActionlet();
        List<ParameterModel> list = new ArrayList<>();
        ParameterModel param = new ParameterModel(REQUEST_KEY, theCase.key);
        list.add(param);
        param = new ParameterModel(INPUT_VELOCITY_SCRIPT_KEY, theCase.value);
        list.add(param);
        
        RuleAction actionInstance = new RuleAction();
        actionInstance.setParameters(list);
        Exception exception = null;
        try {
            actionlet.instanceFrom(actionInstance.getParameters());
        } catch (Exception e) {
            exception = e;
        }
        if(theCase.valid && exception != null){
            exception.printStackTrace();
        }
        assertThat(theCase.msg, exception, theCase.valid ? nullValue() : notNullValue());
    }

    /**
     * Define some test cases for validating the URL. TestNG will run each of these cases as a separate test.
     * This is a great way to test a large number of allowed inputs... and also helps makes your test count look amazing.
     */
    @DataProvider(name = "velocityCases")
    public Object[][] noConditionCases() {

        return new SimpleCase[][]{
            {new SimpleCase("Valid entry","test", 
            		"#foreach($con in $dotcontent.pull(${quote}+contentType:News +(conhost:48190c8c-42c4-46af-8d1a-0cd5db894797 conhost:SYSTEM_HOST)${quote}"
            +",1,'title asc desc')) $con.title #end",  true)},
            {new SimpleCase("Velocity code can't be empty","test2","", false)},
        };
    }

    public static class SimpleCase {

    	String msg;
        String key;
        String value;        
        boolean valid;

        public SimpleCase(String msg, String key, String value, boolean valid) {
        	this.msg = msg;
        	this.key = key;
            this.value = value;
            this.valid = valid;
        }
    }
}
