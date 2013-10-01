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

package org.liferay.jukebox.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.service.SongLocalService;
import org.liferay.jukebox.service.persistence.AlbumPersistence;
import org.liferay.jukebox.service.persistence.ArtistPersistence;
import org.liferay.jukebox.service.persistence.SongPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the song local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.liferay.jukebox.service.impl.SongLocalServiceImpl}.
 * </p>
 *
 * @author Julio Camarero
 * @see org.liferay.jukebox.service.impl.SongLocalServiceImpl
 * @see org.liferay.jukebox.service.SongLocalServiceUtil
 * @generated
 */
public abstract class SongLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements SongLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.liferay.jukebox.service.SongLocalServiceUtil} to access the song local service.
	 */

	/**
	 * Adds the song to the database. Also notifies the appropriate model listeners.
	 *
	 * @param song the song
	 * @return the song that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Song addSong(Song song) throws SystemException {
		song.setNew(true);

		return songPersistence.update(song);
	}

	/**
	 * Creates a new song with the primary key. Does not add the song to the database.
	 *
	 * @param songId the primary key for the new song
	 * @return the new song
	 */
	@Override
	public Song createSong(long songId) {
		return songPersistence.create(songId);
	}

	/**
	 * Deletes the song with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param songId the primary key of the song
	 * @return the song that was removed
	 * @throws PortalException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Song deleteSong(long songId) throws PortalException, SystemException {
		return songPersistence.remove(songId);
	}

	/**
	 * Deletes the song from the database. Also notifies the appropriate model listeners.
	 *
	 * @param song the song
	 * @return the song that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Song deleteSong(Song song) throws SystemException {
		return songPersistence.remove(song);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Song.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return songPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return songPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return songPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return songPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return songPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Song fetchSong(long songId) throws SystemException {
		return songPersistence.fetchByPrimaryKey(songId);
	}

	/**
	 * Returns the song with the matching UUID and company.
	 *
	 * @param uuid the song's UUID
	 * @param  companyId the primary key of the company
	 * @return the matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchSongByUuidAndCompanyId(String uuid, long companyId)
		throws SystemException {
		return songPersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the song matching the UUID and group.
	 *
	 * @param uuid the song's UUID
	 * @param groupId the primary key of the group
	 * @return the matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchSongByUuidAndGroupId(String uuid, long groupId)
		throws SystemException {
		return songPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the song with the primary key.
	 *
	 * @param songId the primary key of the song
	 * @return the song
	 * @throws PortalException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song getSong(long songId) throws PortalException, SystemException {
		return songPersistence.findByPrimaryKey(songId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return songPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the song with the matching UUID and company.
	 *
	 * @param uuid the song's UUID
	 * @param  companyId the primary key of the company
	 * @return the matching song
	 * @throws PortalException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song getSongByUuidAndCompanyId(String uuid, long companyId)
		throws PortalException, SystemException {
		return songPersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the song matching the UUID and group.
	 *
	 * @param uuid the song's UUID
	 * @param groupId the primary key of the group
	 * @return the matching song
	 * @throws PortalException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song getSongByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {
		return songPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the songs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @return the range of songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> getSongs(int start, int end) throws SystemException {
		return songPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of songs.
	 *
	 * @return the number of songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getSongsCount() throws SystemException {
		return songPersistence.countAll();
	}

	/**
	 * Updates the song in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param song the song
	 * @return the song that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Song updateSong(Song song) throws SystemException {
		return songPersistence.update(song);
	}

	/**
	 * Returns the album local service.
	 *
	 * @return the album local service
	 */
	public org.liferay.jukebox.service.AlbumLocalService getAlbumLocalService() {
		return albumLocalService;
	}

	/**
	 * Sets the album local service.
	 *
	 * @param albumLocalService the album local service
	 */
	public void setAlbumLocalService(
		org.liferay.jukebox.service.AlbumLocalService albumLocalService) {
		this.albumLocalService = albumLocalService;
	}

	/**
	 * Returns the album remote service.
	 *
	 * @return the album remote service
	 */
	public org.liferay.jukebox.service.AlbumService getAlbumService() {
		return albumService;
	}

	/**
	 * Sets the album remote service.
	 *
	 * @param albumService the album remote service
	 */
	public void setAlbumService(
		org.liferay.jukebox.service.AlbumService albumService) {
		this.albumService = albumService;
	}

	/**
	 * Returns the album persistence.
	 *
	 * @return the album persistence
	 */
	public AlbumPersistence getAlbumPersistence() {
		return albumPersistence;
	}

	/**
	 * Sets the album persistence.
	 *
	 * @param albumPersistence the album persistence
	 */
	public void setAlbumPersistence(AlbumPersistence albumPersistence) {
		this.albumPersistence = albumPersistence;
	}

	/**
	 * Returns the artist local service.
	 *
	 * @return the artist local service
	 */
	public org.liferay.jukebox.service.ArtistLocalService getArtistLocalService() {
		return artistLocalService;
	}

	/**
	 * Sets the artist local service.
	 *
	 * @param artistLocalService the artist local service
	 */
	public void setArtistLocalService(
		org.liferay.jukebox.service.ArtistLocalService artistLocalService) {
		this.artistLocalService = artistLocalService;
	}

	/**
	 * Returns the artist remote service.
	 *
	 * @return the artist remote service
	 */
	public org.liferay.jukebox.service.ArtistService getArtistService() {
		return artistService;
	}

	/**
	 * Sets the artist remote service.
	 *
	 * @param artistService the artist remote service
	 */
	public void setArtistService(
		org.liferay.jukebox.service.ArtistService artistService) {
		this.artistService = artistService;
	}

	/**
	 * Returns the artist persistence.
	 *
	 * @return the artist persistence
	 */
	public ArtistPersistence getArtistPersistence() {
		return artistPersistence;
	}

	/**
	 * Sets the artist persistence.
	 *
	 * @param artistPersistence the artist persistence
	 */
	public void setArtistPersistence(ArtistPersistence artistPersistence) {
		this.artistPersistence = artistPersistence;
	}

	/**
	 * Returns the song local service.
	 *
	 * @return the song local service
	 */
	public org.liferay.jukebox.service.SongLocalService getSongLocalService() {
		return songLocalService;
	}

	/**
	 * Sets the song local service.
	 *
	 * @param songLocalService the song local service
	 */
	public void setSongLocalService(
		org.liferay.jukebox.service.SongLocalService songLocalService) {
		this.songLocalService = songLocalService;
	}

	/**
	 * Returns the song remote service.
	 *
	 * @return the song remote service
	 */
	public org.liferay.jukebox.service.SongService getSongService() {
		return songService;
	}

	/**
	 * Sets the song remote service.
	 *
	 * @param songService the song remote service
	 */
	public void setSongService(
		org.liferay.jukebox.service.SongService songService) {
		this.songService = songService;
	}

	/**
	 * Returns the song persistence.
	 *
	 * @return the song persistence
	 */
	public SongPersistence getSongPersistence() {
		return songPersistence;
	}

	/**
	 * Sets the song persistence.
	 *
	 * @param songPersistence the song persistence
	 */
	public void setSongPersistence(SongPersistence songPersistence) {
		this.songPersistence = songPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("org.liferay.jukebox.model.Song",
			songLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"org.liferay.jukebox.model.Song");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Song.class;
	}

	protected String getModelClassName() {
		return Song.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = songPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = org.liferay.jukebox.service.AlbumLocalService.class)
	protected org.liferay.jukebox.service.AlbumLocalService albumLocalService;
	@BeanReference(type = org.liferay.jukebox.service.AlbumService.class)
	protected org.liferay.jukebox.service.AlbumService albumService;
	@BeanReference(type = AlbumPersistence.class)
	protected AlbumPersistence albumPersistence;
	@BeanReference(type = org.liferay.jukebox.service.ArtistLocalService.class)
	protected org.liferay.jukebox.service.ArtistLocalService artistLocalService;
	@BeanReference(type = org.liferay.jukebox.service.ArtistService.class)
	protected org.liferay.jukebox.service.ArtistService artistService;
	@BeanReference(type = ArtistPersistence.class)
	protected ArtistPersistence artistPersistence;
	@BeanReference(type = org.liferay.jukebox.service.SongLocalService.class)
	protected org.liferay.jukebox.service.SongLocalService songLocalService;
	@BeanReference(type = org.liferay.jukebox.service.SongService.class)
	protected org.liferay.jukebox.service.SongService songService;
	@BeanReference(type = SongPersistence.class)
	protected SongPersistence songPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private SongLocalServiceClpInvoker _clpInvoker = new SongLocalServiceClpInvoker();
}