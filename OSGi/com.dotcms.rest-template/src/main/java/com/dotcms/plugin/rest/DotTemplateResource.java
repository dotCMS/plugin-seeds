package com.dotcms.plugin.rest;

import com.dotcms.rest.InitDataObject;
import com.dotcms.rest.ResponseEntityView;
import com.dotcms.rest.WebResource;
import com.dotcms.rest.annotation.NoCache;
import com.dotmarketing.beans.Host;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.web.HostWebAPI;
import com.dotmarketing.business.web.WebAPILocator;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotSecurityException;
import com.dotmarketing.portlets.contentlet.business.HostAPI;
import com.dotmarketing.portlets.templates.business.TemplateAPI;
import com.dotmarketing.portlets.templates.model.Template;
import com.dotmarketing.util.Logger;
import com.dotmarketing.util.PageMode;
import com.dotmarketing.util.UtilMethods;
import com.liferay.portal.model.User;
import org.glassfish.jersey.server.JSONP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/v1/templates")
public class DotTemplateResource {

    private final WebResource webResource = new WebResource();
    private final HostWebAPI  hostWebAPI  = WebAPILocator.getHostWebAPI();
    private final TemplateAPI templateAPI = APILocator.getTemplateAPI();
    private final HostAPI     hostAPI     = APILocator.getHostAPI();

	/**
	 * Returns a json representation of the a template (advance or drawed)
	 *
	 * Url example: v1/templates?id=xxx
	 */
	@GET
	@JSONP
	@NoCache
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, "application/javascript"})
	public final Response getTemplate(@Context final HttpServletRequest  httpRequest,
										@Context final HttpServletResponse httpResponse,
										@QueryParam("id") final String templateId) throws DotDataException, DotSecurityException {

		final InitDataObject initData 		 = new WebResource.InitBuilder(this.webResource)
				.requestAndResponse(httpRequest, httpResponse).rejectWhenNoUser(true).init();
		final User user						 = initData.getUser();
		final PageMode pageMode  			 = PageMode.get(httpRequest);

		Logger.info(this, "Getting the template: " + templateId + ", live: " + pageMode.showLive);

		final Template template 			 = pageMode.showLive?
				this.templateAPI.findLiveTemplate(templateId,    user, pageMode.respectAnonPerms):
				this.templateAPI.findWorkingTemplate(templateId, user, pageMode.respectAnonPerms);

		return Response.ok(new ResponseEntityView(
				template.isDrawed()?
						new DesignTemplateView(template):
						new AdvanceTemplateView(template)))
				.build();
	}

	/**
	 * Returns a plain representation of an advance template
	 *
	 * Url example: v1/templates?id=xxx
	 */
	@GET
	@JSONP
	@NoCache
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.TEXT_PLAIN, "text/plain"})
	@Path("/plain")
	public final Response getAdvanceTemplatePlain(@Context final HttpServletRequest  httpRequest,
										@Context final HttpServletResponse httpResponse,
										@QueryParam("id") final String templateId) throws DotDataException, DotSecurityException {

		final InitDataObject initData 		 = new WebResource.InitBuilder(this.webResource)
				.requestAndResponse(httpRequest, httpResponse).rejectWhenNoUser(true).init();
		final User user						 = initData.getUser();
		final PageMode pageMode  			 = PageMode.get(httpRequest);

		Logger.info(this, "Getting the plain body of an advance template: " + templateId + ", live: " + pageMode.showLive);

		final Template template 			 = pageMode.showLive?
				this.templateAPI.findLiveTemplate(templateId,    user, pageMode.respectAnonPerms):
				this.templateAPI.findWorkingTemplate(templateId, user, pageMode.respectAnonPerms);

		if (template.isDrawed()) {

			throw new BadRequestException("The template must be an advance template");
		}

		return Response.ok(template.getBody()).build();
	}

	/**
	 * Saves an advance template
	 */
	@POST
	@JSONP
	@NoCache
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, "application/javascript"})
	@Path("/advance")
	public final Response saveTemplate(@Context final HttpServletRequest  httpRequest,
									  @Context final HttpServletResponse httpResponse,
									  final AdvanceTemplateView advanceTemplateView) throws DotDataException, DotSecurityException {

		final InitDataObject initData 		 = new WebResource.InitBuilder(this.webResource)
				.requestAndResponse(httpRequest, httpResponse).rejectWhenNoUser(true).init();
		final User user						 = initData.getUser();
		final PageMode pageMode  			 = PageMode.get(httpRequest);
		// todo: check permissions to modify

		Logger.info(this, "Adding a new template: " + advanceTemplateView.getId() + ", live: " + pageMode.showLive);

		final Optional<Host> hostOpt = this.resolveHostIdentifier(httpRequest, advanceTemplateView.getSiteId(), user);
		final Host 		    host     = hostOpt.isPresent()?hostOpt.get():hostAPI.findDefaultHost(user, pageMode.respectAnonPerms);
		final Template      template = this.buildTemplate (advanceTemplateView);
		final Template savedTemplate = this.templateAPI.saveTemplate(template, host, user, pageMode.respectAnonPerms);

		return Response.ok(new ResponseEntityView(savedTemplate)).build();
	}

	private Template buildTemplate(final AdvanceTemplateView advanceTemplateView) {

		final Template template = new Template();

		template.setIdentifier(advanceTemplateView.getId());
		template.setBody(advanceTemplateView.getBody());
		template.setTitle(advanceTemplateView.getTitle());
		template.setFriendlyName(advanceTemplateView.getDescription());
		template.setImage(advanceTemplateView.getImage());

		return template;
	}

	private Optional<Host> resolveHostIdentifier(final HttpServletRequest request, final String hostId, final User user) {

		Host checkedHost = null;

		try {

			if (UtilMethods.isSet(hostId)) {

				checkedHost = APILocator.getHostAPI().find(hostId, user, false);
			} else {

				final Host currentHost = this.hostWebAPI.getCurrentHostNoThrow(request);
				checkedHost = null != currentHost? currentHost: null;
			}
		} catch (DotDataException | DotSecurityException e) {

			checkedHost = null;
		}

		return (null == checkedHost)?
				Optional.empty():
				Optional.of(checkedHost);
	}

}
