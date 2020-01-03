package com.dotmarketing.osgi.hooks;

import com.dotmarketing.portlets.categories.model.Category;
import com.dotmarketing.portlets.contentlet.business.DotContentletValidationException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.structure.model.ContentletRelationships;
import com.dotmarketing.util.UtilMethods;

import java.util.List;

/**
 * This validation for the Generic Contentlet
 * for this example the title must have the word "generic" on it
 * @author jsanca
 */
public class GenericTitleContentletValidatorStrategy implements ValidatorStrategy {

    @Override
    public boolean test(final Contentlet contentlet,
                       final ContentletRelationships contentRelationships,
                       final List<Category> categories) {

        final String variable = contentlet.getContentType().variable();
        return "webPageContent".equals(variable);
    }

    @Override
    public boolean applyValidation(final Contentlet contentlet,
                                   final ContentletRelationships contentRelationships,
                                   final List<Category> categories) throws DotContentletValidationException {

        final String title = contentlet.getTitle();
        if (!UtilMethods.isSet(title) || !title.contains("generic")) {

            throw new DotContentletValidationException("The content must contains generic word on the title");
        }

        return true;
    }
}
