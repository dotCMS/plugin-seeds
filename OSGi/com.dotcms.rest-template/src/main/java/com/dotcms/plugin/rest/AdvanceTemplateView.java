package com.dotcms.plugin.rest;

import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.portlets.templates.model.Template;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = AdvanceTemplateView.Builder.class)
public class AdvanceTemplateView {

    private final String id;
    private final String siteId;
    private final String title;
    private final String description;
    private final String image;
    private final String body;

    public AdvanceTemplateView(final Template template) throws DotDataException {

        this.id     = template.getIdentifier();
        this.siteId = template.getParentPermissionable().getPermissionId();
        this.title  = template.getTitle();
        this.body   = template.getBody();
        this.image  = template.getImage();
        this.description = template.getFriendlyName();
    }

    private AdvanceTemplateView(final Builder templateBuilder)  {

        this.id     = templateBuilder.id;
        this.siteId = templateBuilder.siteId;
        this.title  = templateBuilder.title;
        this.body   = templateBuilder.body;
        this.image  = templateBuilder.image;
        this.description = templateBuilder.description;
    }

    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getBody() {
        return body;
    }

    public static final class Builder {
        @JsonProperty private  String id;
        @JsonProperty private  String siteId;
        @JsonProperty private  String title;
        @JsonProperty private  String description;
        @JsonProperty private  String image;
        @JsonProperty private  String body;

        public AdvanceTemplateView.Builder id(String id) {
            this.id = id;
            return this;
        }

        public AdvanceTemplateView.Builder siteId(String siteId) {
            this.siteId = siteId;
            return this;
        }


        public AdvanceTemplateView.Builder title(String title) {
            this.title = title;
            return this;
        }

        public AdvanceTemplateView.Builder description(String description) {
            this.description = description;
            return this;
        }

        public AdvanceTemplateView.Builder image(String image) {
            this.image = image;
            return this;
        }

        public AdvanceTemplateView.Builder body(String body) {
            this.body = body;
            return this;
        }

        public AdvanceTemplateView build() {
            return new AdvanceTemplateView(this);
        }
    }
}
