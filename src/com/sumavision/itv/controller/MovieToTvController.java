package com.sumavision.itv.controller;

import com.sumavision.itv.engine.ItvEngine;
import com.sumavision.itv.listener.OnMovieToTvListener;


public class MovieToTvController
{

	private static OnMovieToTvListener movieToTvListener;

	public static void setOnMovieToTvListener(OnMovieToTvListener listener)
	{

		movieToTvListener = listener;
	}

	public static void addFavorite(boolean bool)
	{

		ItvEngine.serviceAddFavorite(bool);
	}

	public static void getTopicComment(boolean bool)
	{

		ItvEngine.serviceGetTopicComment(bool);
	}

	public static void onMovieToTvEvent(int eventId, int errCode)
	{

		boolean isOk = errCode == ItvEngine.EVENT_ERROR ? false : true;
		if (movieToTvListener != null)
		{
			if (eventId == ItvEngine.EVENT_ADD_FAVORITE)
			{
				movieToTvListener.eventAddFavorite(isOk);
			}
			else if (eventId == ItvEngine.EVENT_GET_TOPIC_COMMENT)
			{
				movieToTvListener.eventGetTopicComment(isOk);
			}
		}
	}

}
