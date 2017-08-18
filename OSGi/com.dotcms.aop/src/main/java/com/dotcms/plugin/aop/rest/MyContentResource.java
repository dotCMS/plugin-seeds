package com.dotcms.plugin.aop.rest;

import com.dotcms.repackage.javax.ws.rs.*;
import com.dotcms.repackage.javax.ws.rs.core.Context;
import com.dotcms.repackage.javax.ws.rs.core.MediaType;
import com.dotcms.repackage.javax.ws.rs.core.Response;
import com.dotcms.repackage.org.glassfish.jersey.server.JSONP;
import com.dotcms.rest.InitDataObject;
import com.dotcms.rest.ResponseEntityView;
import com.dotcms.rest.WebResource;
import com.dotcms.rest.annotation.NoCache;
import com.dotcms.rest.exception.mapper.ExceptionMapperUtil;
import com.dotcms.util.LogTime;
import com.dotcms.vanityurl.business.VanityUrlAPI;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.portlets.contentlet.business.ContentletAPI;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;

@Path("/v1/custom/content")
public class MyContentResource {

    private final WebResource webResource = new WebResource();
    private final MyContentService contentService = new MyContentService();
    private final ContentletAPI contentletAPI = APILocator.getContentletAPI();
    private final VanityUrlAPI  vanityUrlAPI  = APILocator.getVanityUrlAPI();

    @GET
    @Path("/inode/{inode}")
    @Produces(MediaType.APPLICATION_JSON)
    @LogTime
    public Response getContentByInode(@Context final HttpServletRequest request,
                               @PathParam("inode") final String inode) {

        final InitDataObject initData = this.webResource.init
                (null, true, request, false, null);
        final User user = initData.getUser();
        Contentlet contentlet = null;
        Response   response = null;

        try {

            contentlet = this.contentService.hydrateContentLet
                    (this.contentletAPI.find(inode, user, true));

            response = Response.ok(new ResponseEntityView (contentlet)).build();
        } catch (Exception e) {

            response = ExceptionMapperUtil.createResponse
                    (e, Response.Status.INTERNAL_SERVER_ERROR);
        }

       return response;
    }

    @Path("/lastMonth")
    @DELETE
    @JSONP
    @NoCache
    @Produces({MediaType.APPLICATION_JSON, "application/javascript"})
    public Response deleteLastMonthContent(@Context final HttpServletRequest request) {

        final InitDataObject initData = this.webResource.init
                (null, true, request, true, null);
        Response   response = null;

        try {

            response = Response.ok(new ResponseEntityView
                    (this.contentService.deleteLastMonthContent())).build();
        } catch (DotDataException e) {
            response = ExceptionMapperUtil.createResponse
                    (e, Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}