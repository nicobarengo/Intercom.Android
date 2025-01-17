package com.intercom.intercom.android;

import android.app.Application;
import android.util.Log;
import java.util.Map;
import io.intercom.android.sdk.Company;
import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.IntercomError;
import io.intercom.android.sdk.IntercomStatusCallback;
import io.intercom.android.sdk.UserAttributes;
import io.intercom.android.sdk.identity.Registration;
import io.intercom.android.sdk.push.IntercomPushClient;

public class IntercomSDK {
    private static IntercomPushClient pushClient;

    public static void initialize(Application application, String apiKey, String appId) {
        Intercom.initialize(application, apiKey, appId);
    }

    public static void setUserHash(String userHash) {
        Intercom.client().setUserHash(userHash);
    }

    public static void loginIdentifiedUser(String userId) {
        Intercom.client().loginIdentifiedUser(new Registration().withUserId(userId),
                new IntercomStatusCallback() {
                    @Override
                    public void onFailure(IntercomError intercomError) {
                        Log.e("Intercom", intercomError.getErrorMessage());
                    }

                    @Override
                    public void onSuccess() {
                    }
                });
    }

    public static boolean isIntercomPush(Map<String, String> message) {
        if (pushClient == null)
            pushClient = new IntercomPushClient();

        return pushClient.isIntercomPush(message);
    }

    public static void handlePush(Application application, Map<String, String> message) {
        if (pushClient == null)
            pushClient = new IntercomPushClient();

        pushClient.handlePush(application, message);
    }

    public static void sendTokenToIntercom(Application application, String token) {
        if (pushClient == null)
            pushClient = new IntercomPushClient();

        pushClient.sendTokenToIntercom(application, token);
    }

    public static void updateUserDetails(
        String companyId,
        String companyName,
        String userId,
        String fullName,
        String phone,
        String email) {

        UserAttributes userAttributes = new UserAttributes.Builder()
                .withName(fullName)
                .withCompany(new Company.Builder()
                        .withCompanyId(companyId)
                        .withName(companyName)
                        .build())
                .withUserId(userId)
                .withPhone(phone)
                .withEmail(email)
                .build();

        Intercom.client().updateUser(userAttributes, new IntercomStatusCallback() {
            @Override
            public void onFailure(IntercomError intercomError) {
                Log.e("Intercom", intercomError.getErrorMessage());
            }

            @Override
            public void onSuccess() {
            }
        });
    }

    public static void handlePushMessage() {
        Intercom.client().handlePushMessage();
    }

    public static void present() {
        Intercom.client().present();
    }

    public static void logout() {
        Intercom.client().logout();
    }
}
