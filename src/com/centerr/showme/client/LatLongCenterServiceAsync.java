package com.centerr.showme.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LatLongCenterServiceAsync {

	void sendLatLngServer(Double lat, Double lng, AsyncCallback<String> callback);


}
