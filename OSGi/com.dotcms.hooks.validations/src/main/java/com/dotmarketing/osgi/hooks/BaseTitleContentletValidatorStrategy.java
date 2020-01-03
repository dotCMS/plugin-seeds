package com.dotmarketing.osgi.hooks;

import com.dotcms.contenttype.model.type.BaseContentType;
import com.dotmarketing.portlets.categories.model.Category;
import com.dotmarketing.portlets.contentlet.business.DotContentletValidationException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.structure.model.ContentletRelationships;
import com.dotmarketing.util.UtilMethods;

import java.util.List;

/**
 * This base applies a check over the title for all types as base type CONTENT
 * @author jsanca
 */
public class BaseTitleContentletValidatorStrategy implements ValidatorStrategy {

    @Override
    public boolean test(final Contentlet contentlet,
                        final ContentletRelationships contentRelationships,
                        final List<Category> categories) {

        final BaseContentType baseContentType = contentlet.getContentType().baseType();
        return BaseContentType.CONTENT.equals(baseContentType);
    }

    @Override
    public boolean applyValidation(final Contentlet contentlet,
                                   final ContentletRelationships contentRelationships,
                                   final List<Category> categories) throws DotContentletValidationException {

        final String title = contentlet.getTitle();
        if (!UtilMethods.isSet(title) || !title.contains("base")) {

            throw new DotContentletValidationException("The content must contains 'base' word on the title");
        }

        return true;
    }
}

