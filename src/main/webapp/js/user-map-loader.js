let map;

function createMap(){
  
  //creates map
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 38.5949, lng: -94.8923},
    zoom: 4
  });
   
  //creates listener for clicks
  map.addListener('click', (event) => {
    //adds marker wherever user clicks
    new google.maps.Marker({
      position: event.latLng,
      map: map
    });  
  });
}