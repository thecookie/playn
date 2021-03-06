/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package playn.flash.json.impl;

import playn.core.Asserts;
import playn.flash.json.JsonArray;
import playn.flash.json.JsonBoolean;
import playn.flash.json.JsonException;
import playn.flash.json.JsonFactory;
import playn.flash.json.JsonNull;
import playn.flash.json.JsonNumber;
import playn.flash.json.JsonObject;
import playn.flash.json.JsonString;
import playn.flash.json.JsonValue;


/**
 * Implementation of JsonFactory interface using org.json library.
 */
public class JreJsonFactory implements JsonFactory {

  public JsonString create(String string) {
    return new JreJsonString(Asserts.checkNotNull(string));
  }

  public JsonNumber create(double number) {
    return new JreJsonNumber(number);
  }

  public JsonBoolean create(boolean bool) {
    return new JreJsonBoolean(bool);
  }

  public JsonArray createArray() {
    return new JreJsonArray(this);
  }

  public JsonNull createNull() {
    return null;
  }

  public JsonObject createObject() {
    return new JreJsonObject(this);
  }

  public <T extends JsonValue> T parse(String jsonString) throws JsonException {
    if (jsonString.startsWith("(") && jsonString.endsWith(")")) {
       // some clients send in (json) expecting an eval is required
       jsonString = jsonString.substring(1, jsonString.length() - 1);
    }
    @SuppressWarnings("unchecked") T value = (T) new JsonTokenizer(this, jsonString).nextValue();
    return value;
  }
}
