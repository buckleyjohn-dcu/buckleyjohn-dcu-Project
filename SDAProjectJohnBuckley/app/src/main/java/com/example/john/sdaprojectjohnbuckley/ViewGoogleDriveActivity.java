/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.john.sdaprojectjohnbuckley;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;

/**
 * An activity to illustrate how to pick a file with the opener intent.
 * Class contains code adapted from
 * URL:https://https://github.com/googledrive/android-quickstart
 * Retrieved on 10th of April 2017
 * URL:https://github.com/googledrive/android-demos
 * Retrieved on 15th of April 2017
 * Created by John on 20/04/2017.
 */
public class ViewGoogleDriveActivity extends GoogleDriveAuthorisationActivity
{
    private static final String TAG = "ViewGoogleDriveActivity";

    private static final int REQUEST_CODE_OPENER = 1;

    @Override
    /**
     * Code adapted from method onConnected() described at at https://github.com/googledrive/android-demos
     */

    public void onConnected(Bundle connectionHint) {
        super.onConnected(connectionHint);
        IntentSender intentSender = Drive.DriveApi
                .newOpenFileActivityBuilder()
                .setMimeType(new String[] { "text/plain", "text/html" })
                .build(getGoogleApiClient());
        try {
            startIntentSenderForResult(
                    intentSender, REQUEST_CODE_OPENER, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            Log.w(TAG, "Unable to send intent", e);
        }
    }

    @Override
    /**
     * Code adapted from method onConnected() described at at https://github.com/googledrive/android-demos
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_OPENER:
                if (resultCode == RESULT_OK) {
                    DriveId driveId = (DriveId) data.getParcelableExtra(
                            OpenFileActivityBuilder.EXTRA_RESPONSE_DRIVE_ID);
                    showMessage("Selected file's ID: " + driveId);
                }
                finish();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
