Lenddo SDK for Android
======================

## Table of Contents

1.  [Introduction](#user-content-introduction)
2.  [An Overview of the Lenddo Process](#user-content-an-overview-of-the-lenddo-process)
3.  [Getting Started](#user-content-getting-started)
    1.  [Requirements](#user-content-requirements)
    2.  [Installation update with screenshot](#user-content-installation-update-with-screenshot)
4.  [Setting up the sample loan app](#user-content-setting-up-the-sample-loan-app)
5.  [Adding the Lenddo library to your existing project](#user-content-adding-the-lenddo-library-to-your-existing-project)
    1.  [Permissions](#user-content-permissions)
    2.  [Notes on Backwards Compatibility and Issues](#user-content-notes-on-backwards-compatibility-and-issues)
6.  [Integration](#user-content-integration)
    1.  [Adding the Lenddo workflow to your app](#user-content-adding-the-lenddo-workflow-to-your-app)
    2.  [Add the Lenddo Button to your form](#user-content-add-the-lenddo-button-to-your-form)
    3.  [Customizing the Lenddo Button](#user-content-customizing-the-lenddo-button)


## Introduction

This is the Lenddo SDK for Android based devices, if you are developing for other platforms like IOS and web, please refer to the [online documentation] (http://www.lenddo.com/documentation/lenddo_button.html)

The Lenddo SDK for Android allows you to integrate the Lenddo Verification and/or Scoring workflow seamlessly into your Android app.

## An Overview of the Lenddo Process

1.  User fills up a form on your app.
2.  User clicks on the **Lenddo Button** provided by the Lenddo SDK.
3.  A popup webview showing the Lenddo Authorize site is shown.
4.  User completes the Lenddo Authorize process.
5.  A callback to your app is initiated and your app will consume the results.
6.  User is sent to the next phase in your app.

## Getting Started

1.  First make sure you have all the requirements. See the [Requirements](#user-content-requirements) section below.
2.  Successfully run the SDK in a simple loan app. See the [Installation update with screenshot](#user-content-installation-update-with-screenshot) section below.
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

![](https://cloud.githubusercontent.com/assets/481942/13133878/ec7f8ae8-d63a-11e5-9ede-ae5140099fc5.png)

The **LenddoSDK** folder contains the actual Lenddo SDK library project that you can include in your app. The **simple_loan** folder contains the sample app called **Simple Loan** which illustrates how to integrate the **Lenddo Button** into your existing app.

## Setting up the Sample Loan App

1.  Using Android Studio, click on **Select File -> Open** and **choose** the folder LenddoSDK-android which was created when you extracted the Lenddo SDK.zip. Android Studio will automatically set up the project for you.
2.  The sample app is already configured to use the LenddoSDK, all you need to do is to fill in your **partner_script_id**. Edit the **AndroidManifest.xml** file and replace the **partner_script_id** value with the key provided to you. (See below)
```
<meta-data android:name="partnerScriptId" android:value="partner_script_id" />
```

3.  Now, build and run the sample app (_make sure you have your emulator running or you have an Android device connected and configured for development. If you need more information on how to do this, please refer to the Android Studio documentation to learn more_).
4.  When the sample app successfully launches, you will see a sample form with a Lenddo Button at the bottom and a application ID field above it. This Application ID field corresponds to a user id or client id that is created by your app (for testing purposes you can enter a sample ID). An application id can only be used once.
5.  Click on the **Submit and Get Verified** button to complete the authorize process.

If you would like more information on how this works you can view The file **SampleActivity.java** in the simple_loan/src/main/java/lenddo.com.lenddoconnect folder

## Adding the Lenddo library to your existing project

Inside the extracted directory, copy the **LenddoSDK** subfolder and place it inside the root of your Application’s Android Studio project folder. If you encounter an error copy the **LenddoSDK** subfolder in the Folder with the name of your Application in your computer.

Edit the **settings.gradle** file and add the following:

```java
include ':LenddoSDK'
```

Open and edit the **build.gradle** file of your app (not the one in the project root but in the app folder), you should see a section for dependencies below is an example on how it looks:

```java
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
}
```

Add "compile project(':LenddoSDK')" so that it looks like the following:

```java
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile project(':LenddoSDK')
}
```

Android Studio should tell you to resync, the SDK classes should now be available after that.

### Permissions

The required permissions are already defined in the Lenddo SDK and should automatically be incorporated to your app, however the permissions below are required:

```java
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```


## Integration

### Adding the Lenddo workflow to your app

Edit your apps' AndroidManifest.xml (located in your src/main folder for gradle projects), and then add the following meta tag under the application tag:

```java
<meta-data android:name="partnerScriptId" android:value="partner_script_id" />
```

where **partner_script_id** is the partner script id provided to you by Lenddo.

### Configuring the Partner Script Id dynamically

Normally, an application will only need a single partner script id. The Onboarding SDK allows changing of partner script id dynamically by setting it using the FormDataCollector object. Simply call the setPartnerScriptId() method before calling the UIHelper.showAuthorize() method or before clicking the Lenddo button.


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

    ```java
    <com.lenddo.sdk.widget.LenddoButton
       android:id="@+id/verifyButton"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:layout_gravity="center_horizontal"
       android:gravity="center" />
    ```

    Note that you can also use your own custom button. (See section on customization for more information)

3.  Create an Instance of the UIHelper class inside the onCreate block of your activity. Note that the class constructor requires a **LenddoEventListener**. For the sample app, it implements the current activity as a **LenddoEventListener**.

    ```java
    private UIHelper helper;

    protected void onCreate(Bundle savedInstanceState) {
        ....
        helper = new UIHelper(this, this);
    }
    ```

4.  Setup your activity to implement the **LenddoEventListener** in your class or you can define your own class:

    ```java
    public class SampleActivity extends Activity implements LenddoEventListener {

        ....

        private UIHelper helper;

        protected void onCreate(Bundle savedInstanceState) {
            ....
            helper = new UIHelper(this, this);
            String applicationId = "your application id";
            LenddoCoreInfo.setCoreInfo(getApplicationContext(), LenddoCoreInfo.COREINFO_APPLICATION_ID, applicationId);
            LenddoCoreInfo.initCoreInfo(getApplicationContext());
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

    ```java
    LenddoButton button = (LenddoButton) findViewById(R.id.verifyButton);
      button.setUiHelper(helper);
    ```

6.  Pass the content of the form using the form collector. On the onButtonClicked method, you can set the required information using the formData object passed to you. You can also send additional custom fields (To be discussed with your Lenddo representative)

    ```java
    @Override
    public boolean onButtonClicked(FormDataCollector formData) {

        //place partner defined application id if not yet defined
        formData.setApplicationId("123456789");
        formData.setLastName(lastName.getText().toString());
        formData.setFirstName(firstName.getText().toString());
        formData.setEmail(email.getText().toString());
        formData.setDateOfBirth(dateOfBirth);
        
        // Configure the partner script dynamically if needed
        String partnerscript_id = "YOUR NEW PARTNER SCRIPT ID";
        formData.setPartnerScriptId(partnerscript_id);

        //send custom fields
        formData.putField("Loan_Amount", loanAmmount.getText().toString());

        formData.validate();
        return true;
    }
    ```

    **Important Note:** It is important here that you must pass a unique identifier to formData.setApplicationId, this will be used if you want to match your transaction records later on.

7.  Clicking on the Lenddo Button should trigger the Lenddo Authorization/Verification process and your app will be notified via onAuthorizeComplete when the process is done.
8.  Depending on your requirements a score may be available, in this case this is available through our REST APIs. (_Please check here for details http://www.lenddo.com/documentation/rest_api.html_)

### Customizing the Lenddo Button

You may customize the Look and Feel of the Lenddo Button in a couple of ways:

1.  Style are available at the Lenddo SDK res/drawables where you can change various button attributes.
2.  You may create or use any of your existing Button. However you need to manually handle the onClick event with **UIHelper.showAuthorize** like this:

    ```java
    helper = new UIHelper(this, this);

    Button sampleButton = (Button) findViewById(R.id.sample_button);

    sampleButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           UIHelper.showAuthorize(SampleActivity.this, helper);
       }
    });
    ```

