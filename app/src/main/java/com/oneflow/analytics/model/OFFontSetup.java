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

package com.oneflow.analytics.model;

import android.graphics.Typeface;

import com.google.gson.annotations.SerializedName;

public class OFFontSetup {

    @SerializedName("typeface")
    Typeface typeface;
    @SerializedName("fontSize")
    Float fontSize;

    public OFFontSetup(Typeface face, Float size){
        this.typeface = face;
        this.fontSize = size;

    }
    public Typeface getTypeface() {
        return typeface;
    }

   /* public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }*/

    public Float getFontSize() {
        return fontSize;
    }

    /*public void setFontSize(Float fontSize) {
        this.fontSize = fontSize;
    }*/
}
