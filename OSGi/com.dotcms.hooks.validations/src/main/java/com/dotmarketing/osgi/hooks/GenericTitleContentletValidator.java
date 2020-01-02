package com.dotmarketing.osgi.hooks;

import com.dotcms.contenttype.model.type.ContentType;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotRuntimeException;
import com.dotmarketing.exception.DotSecurityException;
import com.dotmarketing.portlets.categories.model.Category;
import com.dotmarketing.portlets.contentlet.business.DotContentletValidationException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.structure.model.ContentletRelationships;
import com.dotmarketing.util.UtilMethods;

import java.util.List;

/**
 * This validation for the Generic Contentlet
 * for this example the title must have the word "generic" on it
 */
public class GenericTitleContentletValidator implements Validator {
    @Override
    public ContentType contentType() {
        try {
            return  APILocator.getContentTypeAPI(APILocator.systemUser()).find("webPageContent");
        } catch (DotSecurityException | DotDataException e) {
            throw new DotRuntimeException(e);
        }
    }

    @Override
    public boolean validate(final Contentlet contentlet,
                            final ContentletRelationships contentRelationships,
                            final List<Category> categories) throws DotContentletValidationException {

        final String title = contentlet.getTitle();
        if (!UtilMethods.isSet(title) || !title.contains("generic")) {

            throw new DotContentletValidationException("The content must contains generic word on the title");
        }

        return true;
    }
}
