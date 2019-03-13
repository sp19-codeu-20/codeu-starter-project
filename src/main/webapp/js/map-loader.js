/** renders map **/
function createMap(){
  /** creates map **/
  const map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 42.40629165, lng: -71.1197505},
    zoom: 5
  });
     
  /** populates w landmarks **/
  addLandmark(map, 42.40629165, -71.1197505, 'Tufts University', 'where Emily goes to school')
  addLandmark(map, 42.3583961, -71.09567788, 'Massachusetts Institute of Technology', 'where Joshua goes to school')
  addLandmark(map, 33.776033, -84.39884086, 'Georgia Institute of Technology', 'where Lauren goes to school');
  addLandmark(map, 22.2832633, 114.1367447, 'University of Hong Kong', 'where Emily would like to go abroad');
}

/** adds a marker that shows an InfoWindow when clicked. */
function addLandmark(map, lat, lng, title, description) {
  const marker = new google.maps.Marker({
    position: {lat: lat, lng: lng},
    map: map,
    title: title
  });
  
  /** adds info window to marker **/
  var infoWindow = new google.maps.InfoWindow({
    content: description
  });
  
  /** adds click listener for info window **/
  marker.addListener('click', function() {
    infoWindow.open(map, marker);
  });
}