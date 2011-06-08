package com.centerr.showme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HackathonZH implements EntryPoint {

	private final String MAPS_API_KEY = "ABQIAAAAv2yIn538Jtp4amT0BmlY5xQXi2nOnQHiN0Y40gIBe__Esg-qtRS9D4SyCfbFUY4gd_qjp0IWmhGajA";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final LatLongCenterServiceAsync service = GWT
			.create(LatLongCenterService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		/*
		 * Asynchronously loads the Maps API.
		 * 
		 * The first parameter should be a valid Maps API Key to deploy this
		 * application on a public server, but a blank key will work for an
		 * application served from localhost.
		 */
		Maps.loadMapsApi(MAPS_API_KEY, "2", true, new Runnable() {
			public void run() {
				buildUi();
			}
		});
	}

	private void buildUi() {
		LatLng cawkerCity = LatLng.newInstance(39.50911111, -98.434222222);

		final MapWidget map = new MapWidget(cawkerCity, 6);
		map.setSize("100%", "100%");
		// Add some controls for the zoom level
		map.addControl(new LargeMapControl());

		Overlay overlay = new Overlay() {

			@Override
			protected void remove() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void redraw(boolean force) {
				LatLng myCenter = map.getCenter();

				map.getInfoWindow()
						.open(map.getCenter(),
								new InfoWindowContent(
										"<img src=\"http://www.google.com/intl/en_ALL/images/srpr/logo1w.png\">"));

				Double myLat = myCenter.getLatitude();
				Double myLng = myCenter.getLongitude();

				service.sendLatLngServer(myLat, myLng,
						new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(String result) {
								// TODO Auto-generated method stub
								map.getInfoWindow().open(
										map.getCenter(),
										new InfoWindowContent("<img src='"
												+ result + "'>"));

							}
						});
			}

			@Override
			protected void initialize(MapWidget map) {
				// TODO Auto-generated method stub

			}

			@Override
			protected Overlay copy() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		;
		;

		// Add a marker
		map.addOverlay(overlay);

		final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
		dock.add(map);

		// Add the map to the HTML host page
		RootLayoutPanel.get().add(dock);
	}
}
