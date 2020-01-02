package com.dotmarketing.osgi.hooks;

import com.dotcms.contenttype.model.type.ContentType;
import com.dotmarketing.portlets.categories.model.Category;
import com.dotmarketing.portlets.contentlet.business.ContentletAPIPreHook;
import com.dotmarketing.portlets.contentlet.business.DotContentletValidationException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.structure.model.ContentletRelationships;
import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * This hook does the validation for diff content types
 */
public class ValidatorPreContentHook implements ContentletAPIPreHook {

    public final Map<ContentType, List<Validator>> validatorMap = new HashedMap();

    /**
     * Adds a validators
     * @param validators
     */
    public void addValidator (final Validator... validators) {

        for (final Validator validator: validators) {

            final ContentType type = validator.contentType();
            this.validatorMap.computeIfAbsent(type, k -> new ArrayList<>()).add(validator);
        }
    }


    public ValidatorPreContentHook() {
        super();
    }

    /*@Override
	public boolean checkin(Contentlet currentContentlet, ContentletRelationships relationshipsData, List<Category> cats, List<Permission> selectedPermissions, User user,	boolean respectFrontendRoles) {

        Logger.info(this, "+++++++++++++++++++++++++++++++++++++++++++++++" );
        Logger.info(this, "INSIDE SamplePreContentHook.checkin()" );
        Logger.info(this, "+++++++++++++++++++++++++++++++++++++++++++++++" );

        return true;
	}*/

	@Override
    public boolean validateContentlet(final Contentlet contentlet, final ContentletRelationships contentRelationships,
                                   final List<Category> categories) throws DotContentletValidationException {


        final ContentType type           = contentlet.getContentType();
        final List<Validator> validators = this.validatorMap.getOrDefault(type, Collections.emptyList());
        for (final Validator validator : validators) {

            validator.validate(contentlet, contentRelationships, categories);
        }

        return true;
    }

}