package com.dotmarketing.osgi.hooks;

import com.dotcms.contenttype.model.type.ContentType;
import com.dotmarketing.portlets.categories.model.Category;
import com.dotmarketing.portlets.contentlet.business.DotContentletValidationException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.structure.model.ContentletRelationships;

import java.util.List;

/**
 * Validator for a Content type
 */
public interface Validator {

    /**
     * Returns the content type associated to this validator
     * @return
     */
    ContentType contentType();

    /**
     * Does the validation, throws an DotContentletValidationException if has an error
     * @param contentlet
     * @param contentRelationships
     * @param categories
     * @return boolean true if everything ok
     * @throws DotContentletValidationException
     */
    boolean validate(final Contentlet contentlet, final ContentletRelationships contentRelationships,
                                      final List<Category> categories) throws DotContentletValidationException;
}
