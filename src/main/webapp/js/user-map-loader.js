let map;
let editMarker;

//creates map
function createMap(){
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 38.5949, lng: -94.8923},
    zoom: 4
  });
   
  //creates listener for clicks
  map.addListener('click', (event) => {
    //adds marker wherever user clicks
    createMarkerForEdit(event.latLng.lat(), event.latLng.lng());
  });

  //gets markers from datastore
  fetchMarkers();
}

//creates marker at given lat and lng coordinates with 
  //corresponding info window
function createMarkerForEdit(lat, lng){
  //closes previously opened marker if exists
  if(editMarker){
    editMarker.setMap(null)
  }

  //creates marker
  editMarker = new google.maps.Marker({
    position: {lat: lat, lng: lng},
    map: map
  });  
  
  //creates info window
  const infoWindow = new google.maps.InfoWindow({
    content: buildInfoWindowInput(lat, lng)
  });

  //removes marker once user closes the info window
  google.maps.event.addListener(infoWindow, 'closeclick', () => {
    editMarker.setMap(null);
  });
  
  //opens info window corresponding to marker
  infoWindow.open(map, editMarker);
}

//creates marker that stores info from user
function createMarkerForDisplay(lat, lng, content){
  //creates marker
  const marker = new google.maps.Marker({
    position: {lat: lat, lng: lng},
    map: map
  });
  
  //creates info window
  var infoWindow = new google.maps.InfoWindow({
    content: content
  });

  //displays info window upon click
  marker.addListener('click', () => {
    infoWindow.open(map, marker);
  });
}

//builds div for info window containing text and button
function buildInfoWindowInput(lat, lng){
  //creates fields for div
  const textBox = document.createElement('textarea');
  const button = document.createElement('button');
  button.appendChild(document.createTextNode('Submit'));

  //saves info submitted from user
  button.onclick = () => {
    postMarker(lat, lng, textBox.value);
    createMarkerForDisplay(lat, lng, textBox.value);
    editMarker.setMap(null);
  };
  
  //creates the div 
  const containerDiv = document.createElement('div');
  containerDiv.appendChild(textBox);
  containerDiv.appendChild(document.createElement('br'));
  containerDiv.appendChild(button);
     
  return containerDiv;
}

//gets user's markers from the datastore and displays them
function fetchMarkers(){
  fetch('/user-markers').then((response) => {
    return response.json();
  }).then((markers) => {
    markers.forEach((marker) => {
      createMarkerForDisplay(marker.lat, marker.lng, marker.content)
    });  
  });
}

//sends info for user's marker to server
function postMarker(lat, lng, content){
  const params = new URLSearchParams();
  params.append('lat', lat);
  params.append('lng', lng);
  params.append('content', content);

  fetch('/user-markers', {
    method: 'POST',
    body: params
  });
}