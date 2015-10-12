Lenddo SDK for Android
======================

## Table of Contents

1.  [Introduction](#introduction)
2.  [An Overview of the Lenddo Process](#an-overview-of-the-lenddo-process)
3.  [Getting Started](#getting-started)
    1.  [Requirements](#requirements)
    2.  [Installation update with screenshot](#installation-update-with-screenshot)
4.  [Setting up the sample loan app](#setting-up-the-sample-loan-app)
5.  [Adding the Lenddo library to your existing project](#adding-the-lenddo-library-to-your-existing-project)
    1.  [Permissions](#permissions)
    2.  [Notes on Backwards Compatibility and Issues](#notes-on-backwards-compatibility-and-issues)
6.  [Integration](#integration)
    1.  [Adding the Lenddo workflow to your app](#adding-the-lenddo-workflow-to-your-app)
    2.  [Add the Lenddo Button to your form](#add-the-lenddo-button-to-your-form)
    3.  [Customizing the Lenddo Button](#customizing-the-lenddo-button)
    4.  [Passing a Facebook Token (Optional)](#passing-a-facebook-token-optional)
        1. [Setting up your app](#setting-up-your-app)
    5.  [Directly Passing Data for Verification (Requires Facebook token)](#directly-passing-data-for-verification-requires-facebook-token) 
    6.  [Using the auto-collector (Optional)](#using-the-auto-collector-optional)

## Introduction

This is the Lenddo SDK for Android based devices, if you are developing for other platforms like IOS and web, please refer to the online documentation: [https://www.lenddo.com/documentation](https://www.lenddo.com/documentation)

The Lenddo SDK for Android allows you to integrate the Lenddo Verification and/or Scoring workflow seamlessly into your Android app.

## An Overview of the Lenddo Process

1.  User fills up a form on your app.
2.  User clicks on the **Lenddo Button** provided by the Lenddo SDK.
3.  A popup webview showing the Lenddo Authorize site is shown.
4.  User completes the Lenddo Authorize process.
5.  A callback to your app is initiated and your app will consume the results.
6.  User is sent to the next phase in your app.

## Getting Started

1.  First make sure you have all the requirements. See the [Requirements](#requirements) section below.
2.  Successfully run the SDK in a simple loan app. See the [Installation update with screenshot](#installation-update-with-screenshot) section below.
3.  Add the Lenddo SDK libraries to your own application.
4.  Test and deploy

### Requirements

Before you start on integrating the Lenddo SDK, please make sure you have the following

1.  Properly installed latest version of **Android Studio**. You may refer to the Google Developer Docs on how to set this up [https://developer.android.com/sdk/index.html](https://developer.android.com/sdk/index.html)
2.  A valid Lenddo **Partner Script Id**
3.  Basic knowledge on setting up Android Libraries. (This document will explain the specific steps for the Lenddo SDK only).
4.  Download the LenddoSDK onto your hard drive

### Installation update with screenshot

Download the Lenddo SDK and extract the archive into your local drive. After extracting the archive, the Lenddo SDK folder structure should look like this:

![](http://www.lenddocdn.com/images/sdk-android-package.png)

The **LenddoSDK** folder contains the actual Lenddo SDK library project that you can include in your app. The **simple_loan** folder contains the sample app called **Simple Loan** which illustrates how to integrate the **Lenddo Button** into your existing app.

## Setting up the Sample Loan App

1.  Using Android Studio, click on **Select File -> Open** and **choose** the folder LenddoSDK-android which was created when you extracted the Lenddo SDK.zip. Android Studio will automatically set up the project for you.
2.  The sample app is already configured to use the LenddoSDK, all you need to do is to fill in your **partner-script-id**. Edit the **simple_loan/src/main/res/values/config.xml** and replace the words “PLACE YOUR PARTNER SCRIPT ID HERE” with the **partner_script_id** key provided to you. (See below)

    ```
    <?xml version="1.0" encoding="utf-8"?>
    <resources>
      <!-- Lenddo Partner Script ID -->
      <string name="lenddo_app_id">PLACE_YOUR_PARTNER_SCRIPT_ID_HERE</string>
    </resources>
    ```

3.  Now, build and run the sample app (_make sure you have your emulator running or you have an Android device connected and configured for development. If you need more information on how to do this, please refer to the Android Studio documentation to learn more_).
4.  When the sample app successfully launches, you will see a sample form with a Lenddo Button at the bottom and q client ID field above it. This Client ID field corresponds to a user id or application id that is created by your app (for testing purposes you can enter a sample ID).
5.  Click on the Verify with Lenddo button to complete the authorize process.

If you would like more information on how this works you can view The file **SampleActivity.java** in the simple_loan/src/main/java/lenddo.com.lenddoconnect folder

## Adding the Lenddo library to your existing project

Inside the extracted directory, copy the **LenddoSDK** subfolder and place it inside the root of your Application’s Android Studio project folder. If you encounter an error copy the **LenddoSDK** subfolder in the Folder with the name of your Application in your computer.

Edit the **settings.gradle** file and add the following:

```
include ':LenddoSDK'
```

Open and edit the **build.gradle** file of your app (not the one in the project root but in the app folder), you should see a section for dependencies below is an example on how it looks:

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
}
```

Add "compile project(':LenddoSDK')" so that it looks like the following:

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile project(':LenddoSDK')
}
```

Android Studio should tell you to resync, the SDK classes should now be available after that.

### Permissions

The required permissions are already defined in the Lenddo SDK and should automatically be incorporated to your app, however the permissions below are required:

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### Notes on Backwards Compatibility and Issues

There is a compatibility issue with the latest Chrome-based WebView on Android 4.4 (kitkat) and above. It is recommended to set your **targetSdkVersion** to **18** or below when using the Lenddo SDK.

The SDK has been tested on Android ICS 4.0 and above. Compatibility is not guaranteed on lower versions.

## Integration

### Adding the Lenddo workflow to your app

Edit your apps' AndroidManifest.xml (located in your src/main folder for gradle projects), and then add the following meta tag under the application tag:

```
<meta-data android:name="partnerScriptId" android:value="partner_script_id" />
```

where **partner_script_id** is the partner script id provided to you by Lenddo.

### Add the Lenddo Button to your form

The Lenddo button greatly simplifies integrating the Lenddo workflow to your app.

1.  Create your form (if you don't have an existing one already)

    The Lenddo verification process requires at the minimum, the following fields:

    *   Primary Address
    *   Email
    *   Last Name
    *   Middle Name
    *   First Name
    *   Date of Birth
    *   Home Phone Number
    *   Mobile Phone Number
    *   University
    *   Employer

    However the exact fields that is required for your App may be different depending on your requirements or use cases, please talk to your Lenddo Representative regarding this.

2.  Open up your Forms' layout xml and add the following to include the Lenddo Button onto your Layout:

    ```
    <com.lenddo.sdk.widget.LenddoButton
       android:id="@+id/verifyButton"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:layout_gravity="center_horizontal"
       android:gravity="center" />
    ```

    Note that you can also use your own custom button. (See section on customization for more information)

3.  Create an Instance of the UIHelper class inside the onCreate block of your activity. Note that the class constructor requires a **LenddoEventListener**. For the sample app, it implements the current activity as a **LenddoEventListener**.

    ```
    private UIHelper helper;

    protected void onCreate(Bundle savedInstanceState) {
        ....
        helper = new UIHelper(this, this);
    }
    ```

4.  Setup your activity to implement the **LenddoEventListener** in your class or you can define your own class:

    ```
    public class SampleActivity extends Activity implements LenddoEventListener {

        ....

        private UIHelper helper;

        protected void onCreate(Bundle savedInstanceState) {
            ....
            helper = new UIHelper(this, this);

        }

        @Override
        public boolean onButtonClicked(FormDataCollector collector) {
            return true;
        }

        @Override
        public void onAuthorizeComplete(FormDataCollector collector) {
        }

        @Override
        public void onAuthorizeCanceled(FormDataCollector collector) {
        }

        ....
    }
    ```

    Note: These methods allow you to hook into the Lenddo Authorize process.

5.  Still on the onCreate method, Link the button to the UIHelper:

    ```
    LenddoButton button = (LenddoButton) findViewById(R.id.verifyButton);
      button.setUiHelper(helper);
    ```

6.  Pass the content of the form using the form collector. On the onButtonClicked method, you can set the required information using the formData object passed to you. You can also send additional custom fields (To be discussed with your Lenddo representative)

    ```
    @Override
    public boolean onButtonClicked(FormDataCollector formData) {

        //place partner defined user identifier
        formData.setUserId("123456789");
        formData.setLastName(lastName.getText().toString());
        formData.setFirstName(firstName.getText().toString());
        formData.setEmail(email.getText().toString());
        formData.setDateOfBirth(dateOfBirth);

        //send custom fields
        formData.putField("Loan_Amount", loanAmmount.getText().toString());

        formData.validate();
        return true;
    }
    ```

    **Important Note:** It is important here that you must pass a unique identifier to formData.setUserId, this will be used if you want to match your transaction records later on.

7.  Clicking on the Lenddo Button should trigger the Lenddo Authorization/Verification process and your app will be notified via onAuthorizeComplete when the user id done.
8.  Depending on your requirements a score may be available, in this case this is available through our REST APIs. (_Please check here for details https://www.lenddo.com/documentation/rest_api_)

### Customizing the Lenddo Button

You may customize the Look and Feel of the Lenddo Button in a couple of ways:

1.  Style are available at the Lenddo SDK res/drawables where you can change various button attributes.
2.  You may create or use any of your existing Button. However you need to manually handle the onClick event with **UIHelper.showAuthorize** like this:

    ```
    helper = new UIHelper(this, this);

    Button sampleButton = (Button) findViewById(R.id.sample_button);

    sampleButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           UIHelper.showAuthorize(SampleActivity.this, helper);
       }
    });
    ```

### Passing a Facebook Token (Optional)

The lenddo SDK allows you to reuse the existing facebook token that you have in your app that
was obtained using the Facebook SDK. This will allow the Lenddo onboarding process to skip
its own facebook login and use your token instead. Do note that certain facebook permissions
must be approved in your Facebook API account in order for the Lenddo verification process to
work properly, below is the list:

1. email
2. public_profile
3. user_birthday
4. user_work_history
5. user_education_history
6. user_friends
7. user_likes

The above facebook permissions must be requested by your app when the user logs in.

Some of these permissions would require the you go through a verification process that is done
by facebook. Make sure that you already have these permissions when you proceed. Any
missing permission would result to problems or limitations in process.

Note that this is optional, if no facebook token is passed to the SDK, the facebook login will be
shown during the onboarding process.

#### Setting up your App

Additional settings will need to be added to your app namely the **api secret**. This will be
provided to you by your Lenddo representative.

1. Add the api secret. In your AndroidManifest.xml, make sure to add the following meta:

  ```
  <application ….>
    <meta­data ​android​:​name​=​"partnerApiSecret" ​android​:value=​"api_secret_here" ​/>
  </application>
  ```
2. Pass the actual facebook token during the onboarding process using the
`setFacebookToken​method`, the actual token and expiration date are passed (see below for
an example):
  
  ```
  @Override
  public boolean ​onButtonClicked(FormDataCollector formData) {
    //auto­collect (optional)
    formData .collect(SampleActivity.this​, R.id.formContainer​);
    
    AccessToken accessToken = AccessToken.​getCurrentAccessToken​();
    formData.setFacebookToken(accessToken.getToken().toString(),
    accessToken.getExpires().getTime());
    
    Address primaryAddress = new ​Address();
    
    primaryAddress.setHouseNumber(houseNumber​.getText().toString());
    primaryAddress.setStreet(street​.getText().toString());
    primaryAddress.setBarangay(barangay​.getText().toString());
    primaryAddress.setProvince(province​.getText().toString());
    primaryAddress.setCity(city​.getText().toString());
    primaryAddress.setPostalCode(postalCode​.getText().toString());
  ```
  
### Directly Passing Data for Verification (Requires Facebook token)

If you have a facebook token and only verification is needed by your app, you may skip the
authorize flow web process and submit your application directly to the Lenddo backend for
processing. No popup will be shown and the whole process is done by code.

In order to do this the following are needed:

1. A valid facebook access token. Please refer to the section [Passing a Facebook Token (Optional)](#passing-a-facebook-token-optional) on the required permissions.

2. **api secret** - This is provided by your Lenddo contact or obtained through your
Dashboard. If you have done this before in [Setting up your app](#setting-up-your-app) then this is already
setup. Otherwise perform step 1 of the process in the section [Setting up your app](#setting-up-your-app).

Passing the data is similar to how it is done when using the Lenddo Button:

1. Create an instance of the `UIHelper`

  ```
  helper ​= new ​UIHelper(activity​, lenddoEventListener​);
  ```
  
2. Implement the LenddoEventListener. Passing the required data is done on the
onButtonClicked() method.

  ```
  @Override
  public boolean ​onButtonClicked(FormDataCollector formData) {
    formData.setClientId(customerId​.getText().toString());
    formData.setLastName(lastName​.getText().toString());
    formData.setMiddleName(middleName​.getText().toString());
    formData.setHomePhone(homePhone​.getText().toString());
    formData.setFirstName(firstName​.getText().toString());
    formData.setEmail(email​.getText().toString());
    formData.setEmployerName(nameOfEmployer​.getText().toString());
    formData.setMobilePhone(mobilePhone​.getText().toString());
    formData.validate();
    return true​;
  }
  ```
  
Note that you have to pass the fields that you want to verify here as well as the client id. For the
client ID, if you intend to do the complete onboarding flow later, you will need to provide a
different one for this.

3. Call the `UIHelper.startVerificationUsingFacebookToken()` to start the verificaton process
passing along the UIHelper and facebook token details (See below for an example):

```
AccessToken accessToken = AccessToken.getCurrentAccessToken();
UIHelper.startVerificationUsingFacebookToken(activity,
accessToken.getToken().toString(), accessToken.getExpires().getTime(),
uiHelper);
```

The `onAuthorizeComplete()` callback method will be called once the process is complete.
Lenddo’s servers will begin to verify the passed information asynchronously. To obtain the
verification results, please refer to the REST api documentation.

### Using the auto-collector (Optional)

This is optional. If you don’t what to use `.getText()` on settings the field values, the auto-collector allows you to send tagged fields in your layout by simply using `formData.collect()`. however, you do need to tell the auto-collector which fields need to be sent. This is done by adding a tag field to your layout xml like below:

```
<LinearLayout style="@style/fieldContainer">
    <TextView
        style="@style/formLabel"
        android:text="@string/house_number"/>
    <EditText
        android:tag="house_number"
        android:id="@+id/editTextHouseNumber"
        android:hint="ex. 123"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</LinearLayout>
<LinearLayout style="@style/fieldContainer">
    <TextView
        style="@style/formLabel"
        android:text="@string/street_name"/>

    <EditText
        android:tag="street_name"
        android:id="@+id/editTextStreetName"
        android:layout_width="match_parent"
        android:hint="ex. makati ave"
        android:layout_height="wrap_content" />
</LinearLayout>
```

The **android:tag** name will be used by the auto-collector in determining which fields need to be sent and what the attribute name to use.
