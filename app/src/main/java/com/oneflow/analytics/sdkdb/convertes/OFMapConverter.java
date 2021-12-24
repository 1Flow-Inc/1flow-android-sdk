/*
 *  Copyright 2021 1Flow, Inc.
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.oneflow.analytics.sdkdb.convertes;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.HashMap;

//@ProvidedTypeConverter
public class OFMapConverter implements Serializable {
    @TypeConverter
    public HashMap<String, String> stringToMap(String value)  {
        return new Gson().fromJson(value,  new TypeToken<HashMap<String, String>>(){}.getType());
    }

    @TypeConverter
    public String mapToString(HashMap<String, String> map) {
        if(map == null)
            return "";
        else
            return new Gson().toJson(map);
    }
}