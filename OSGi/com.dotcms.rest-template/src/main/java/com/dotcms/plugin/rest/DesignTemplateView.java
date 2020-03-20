package com.dotcms.plugin.rest;

import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.portlets.templates.model.Template;

public class DesignTemplateView extends AdvanceTemplateView {

    private final String theme;
    private final String themeName;
    private final String header;
    private final String footer;
    private final String drawedBody;


    public DesignTemplateView(final Template template) throws DotDataException {

        super (template);
        this.theme      = template.getTheme();
        this.themeName  = template.getThemeName();
        this.header     = template.getHeader();
        this.footer     = template.getFooter();
        this.drawedBody = template.getDrawedBody();
    }

    public String getTheme() {
        return theme;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public String getDrawedBody() {
        return drawedBody;
    }
}
