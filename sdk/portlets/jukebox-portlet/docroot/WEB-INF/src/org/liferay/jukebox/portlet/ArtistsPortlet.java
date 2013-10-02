/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package org.liferay.jukebox.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.liferay.jukebox.ArtistNameException;
import org.liferay.jukebox.model.Artist;
import org.liferay.jukebox.service.ArtistServiceUtil;

/**
 * @author Julio Camarero
 */
public class ArtistsPortlet extends MVCPortlet {

	public void addArtist(ActionRequest request, ActionResponse response)
		throws Exception {

		String name = ParamUtil.getString(request, "name");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Artist.class.getName(), request);

		try {
			ArtistServiceUtil.addArtist(name, serviceContext);

			SessionMessages.add(request, "artistAdded");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			if (e instanceof ArtistNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(request, e.getClass().getName());

				response.setRenderParameter(
					"jspPage", "/html/artists/edit_artist.jsp");
			}
			else {
				response.setRenderParameter("jspPage", "/html/error.jsp");
			}
		}
	}

	public void deleteArtist(ActionRequest request, ActionResponse response)
		throws Exception {

		long artistId = ParamUtil.getLong(request, "artistId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Artist.class.getName(), request);

		try {
			ArtistServiceUtil.deleteArtist(artistId, serviceContext);

			SessionMessages.add(request, "artistDeleted");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			response.setRenderParameter("jspPage", "/html/error.jsp");
		}
	}

	public void updateArtist(ActionRequest request, ActionResponse response)
		throws Exception {

		long artistId = ParamUtil.getLong(request, "artistId");
		String name = ParamUtil.getString(request, "name");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Artist.class.getName(), request);

		try {
			ArtistServiceUtil.updateArtist(artistId, name, serviceContext);

			SessionMessages.add(request, "artistUpdated");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			if (e instanceof ArtistNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(request, e.getClass().getName());

				response.setRenderParameter(
					"jspPage", "/html/artists/edit_artist.jsp");
			}
			else {
				response.setRenderParameter("jspPage", "/html/error.jsp");
			}
		}
	}

	public final static String PORTLET_ID = "artists_WAR_jukeboxportlet";

}