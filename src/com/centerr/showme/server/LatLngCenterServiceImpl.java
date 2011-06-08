package com.centerr.showme.server;

import java.io.IOException;
import java.text.DecimalFormat;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.centerr.showme.client.LatLongCenterService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LatLngCenterServiceImpl extends RemoteServiceServlet implements
		LatLongCenterService {

	@Override
	public String sendLatLngServer(Double lat, Double lng)
			throws IllegalArgumentException {

		DecimalFormat df = new DecimalFormat("0.###");
		System.out.println("server: " + df.format(lat) + ", " + df.format(lng));

		String myphoto = "";
		
		Flickr flickr = new Flickr("");
		try {
			
			
			PhotoList photos = flickr.getPhotosInterface().getRecent(0, 1);

			for (Object p : photos) {
				 myphoto = ((Photo) p).getThumbnailUrl();
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		
		return myphoto;

	}
}
