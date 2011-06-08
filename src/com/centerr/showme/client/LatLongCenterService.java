package com.centerr.showme.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("latlng")
public interface LatLongCenterService extends RemoteService {
	String sendLatLngServer(Double lat, Double lng) throws IllegalArgumentException;
}
