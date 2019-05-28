// The following example creates complex markers to indicate beaches near
		// Sydney, NSW, Australia. Note that the anchor is set to (0,32) to correspond
		// to the base of the flagpole.
		function initMap() {
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 10,
				center : {
					lat : -6.1683295,
					lng : 106.75884940000003
				}
			});
			setMarkers(map);
		}
		// Data for the markers consisting of a name, a LatLng and a zIndex for the
		// order in which these markers should display on top of each other.
		var beaches = [
				[ 'West Jakarta', -6.1683295, 106.75884940000003, 4 ],
				[ 'East Jakarta', -6.2250138, 106.90044720000003, 5 ],
				[ 'Central Jakarta', -6.1864864, 106.83409110000002, 3 ],
				[ 'Slipi', -6.2206617, 106.61681529999998, 2 ],
				[ 'Wisma 77 Tower 2', -6.190042699999999, 106.79880279999998, 6 ]
				 ];

		function setMarkers(map) {
			// Adds markers to the map.

			// Marker sizes are expressed as a Size of X,Y where the origin of the image
			// (0,0) is located in the top left of the image.

			// Origins, anchor positions and coordinates of the marker increase in the X
			// direction to the right and in the Y direction down.
			var image = {
				url : 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
				// This marker is 20 pixels wide by 32 pixels high.

			//	src: '/img/app_icon.png',
				
				size : new google.maps.Size(20, 32),
				// The origin for this image is (0, 0).
				origin : new google.maps.Point(0, 0),
				// The anchor for this image is the base of the flagpole at (0, 32).
				anchor : new google.maps.Point(0, 32)
			};
			// Shapes define the clickable region of the icon. The type defines an HTML
			// <area> element 'poly' which traces out a polygon as a series of X,Y points.
			// The final coordinate closes the poly by connecting to the first coordinate.
			var shape = {
				coords : [ 1, 1, 1, 20, 18, 20, 18, 1 ],
				type : 'poly'
			};
	
			for (var i = 0; i < beaches.length; i++) {
				var beach = beaches[i];
				var marker = new google.maps.Marker({
					position : {
						lat : beach[1],
						lng : beach[2]
					},
					map : map,
					icon : image,
					shape : shape,
					title : beach[0],
					zIndex : beach[3]
				});

				google.maps.event.addListener(marker,'click', function() {
					map.setCenter(this.getPosition());
					$(".form-box").removeClass("collapse");	
				});
				
	/*	google.maps.event.addListener(marker,'doubleclick', function() {
			          map.setCenter(this.getPosition());
			          $(".form-box").addClass("collapse");
			        });
			*/
			}
		}