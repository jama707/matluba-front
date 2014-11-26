<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            <p>
                <strong>Location:</strong>
                <span id="address">Lot E-3A-3A, Star Parc Point, Jalan Taman Ibu Kota, Off Jalan Genting Kelang, 53300, Kuala Lumpur</span>
            </p>

            <div id="map_canvas" style="width: 100%; height: 400px; background-color:#ccc;">Initializing Google Maps
            </div>
        </div>
        <div class="col-md-4">
            <p><strong class="contact-label">Working Hours:</strong> Tuesday – Sunday: 10am-10pm</p>

            <p><strong class="contact-label">Phone:</strong> +60126046416</p>

            <p><strong class="contact-label">For wholesale inquiries:</strong> +60124803707</p>

            <p><strong class="contact-label">Email:</strong>jasurbek.umarov@gmail.com</p>
        </div>
    </div>
</div>
<script>
    function initializeMap() {
        var mapCanvas = document.getElementById('map_canvas');
        var mapOptions = {
            center: new google.maps.LatLng(44.5403, -78.5463),
            zoom: 14,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        } ;
        var map = new google.maps.Map(mapCanvas, mapOptions);
        var geocoder = geocoder = new google.maps.Geocoder();
        var address = document.getElementById('address').innerHTML;
        geocoder.geocode({ 'address': address }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                map.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location
                });
            } else {
                console.log('Geocode was not successful for the following reason: ' + status);
            }
        });
    }
    google.maps.event.addDomListener(window, 'load', initializeMap);
</script>