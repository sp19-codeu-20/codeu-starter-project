/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.data;

import java.util.UUID;

/** A single message posted by a user. */
public class Message {

  private UUID id;
  private String user;
  private String text;
  private long timestamp;
  private String recipient;
  private String imageUrl;
  private String imageLabels;
  private double lat;
  private double lng;
  private Boolean hasLocation;


  public Message(String user, String text, String recipient, String imageUrl, 
        String imageLabels) {
    this(UUID.randomUUID(), user, text, System.currentTimeMillis(), 
        recipient, imageUrl, imageLabels, 0, 0, false);
  }

  /**
   * Constructs a new {@link Message} posted by {@code user} with {@code text} content. Generates a
   * random ID and uses the current system time for the creation time.
   */
  public Message(String user, String text, String recipient, double lat, double lng) {
    this(UUID.randomUUID(), user, text, System.currentTimeMillis(), 
        recipient, "", "", lat, lng, true);
  }
  
  /**
   * Message Parameters.
   */
  public Message(UUID id, String user, String text, 
      long timestamp, String recipient, String imageUrl, 
      String imageLabels, double lat, double lng, boolean hasLocation) {
    this.id = id;
    this.user = user;
    this.text = text;
    this.timestamp = timestamp;
    this.imageUrl = imageUrl;
    this.recipient = recipient;
    this.imageLabels = imageLabels;
    this.lat = lat;
    this.lng = lng;
    this.hasLocation = hasLocation;
  }

  public UUID getId() {
    return id;
  }

  public String getUser() {
    return user;
  }

  public String getText() {
    return text;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getRecipient() {
    return recipient;
  }
  
  public String getImageUrl() {
    return imageUrl;
  }

  public double getLat() {
    return lat;
  }

  public double getLng() {
    return lng;
  }

  public boolean getHasLocation() {
    return hasLocation;
  }
  
  public void setImageUrl(String url) {
    this.imageUrl = url;
  }
  
  public String getImageLabels() {
    return imageLabels;
  }
  
  public void setImageLabels(String label) {
    this.imageLabels = label;
  }
}
