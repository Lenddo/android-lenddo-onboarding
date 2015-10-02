package lenddo.com.lenddoconnect;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lenddo.sdk.core.LenddoEventListener;
import com.lenddo.sdk.models.Address;
import com.lenddo.sdk.models.AuthorizationStatus;
import com.lenddo.sdk.models.FormDataCollector;
import com.lenddo.sdk.utils.UIHelper;
import com.lenddo.sdk.widget.LenddoButton;

import java.util.Calendar;


public class SampleActivity extends Activity implements LenddoEventListener {

    private static final String TAG = SampleActivity.class.getName();
    private LenddoButton button;
    private EditText lastName;
    private EditText firstName;
    private EditText email;
    private TextView dateOfBirth;
    private EditText loanAmmount;
    private Spinner gender;
    private Spinner sourceOfFunds;
    private UIHelper helper;
    private TextView customerId;
    private TextView nameOfEmployer;
    private TextView mobilePhone;
    private Button dobButton;
    private EditText middleName;
    private TextView homePhone;
    private EditText motherLastName;
    private EditText motherFirstName;
    private EditText motherMiddleName;
    private Button employmentStartDateButton;
    private Button employmentEndDateButton;
    private TextView editTextEmploymentStart;
    private TextView editTextEmploymentEnd;
    private EditText university;
    private EditText houseNumber;
    private EditText street;
    private EditText barangay;
    private EditText province;
    private EditText city;

    private EditText postalCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_form);

        button = (LenddoButton) findViewById(R.id.verifyButton);



        lastName = (EditText) findViewById(R.id.editTextLastName);
        middleName = (EditText) findViewById(R.id.editTextMiddleName);
        firstName = (EditText) findViewById(R.id.editTextFirstName);
        university = (EditText) findViewById(R.id.editTextUniversity);

        houseNumber = (EditText) findViewById(R.id.editTextHouseNumber);
        street = (EditText) findViewById(R.id.editTextStreetName);
        barangay = (EditText) findViewById(R.id.editTextBarangay);
        city = (EditText) findViewById(R.id.editTextMunicipality);
        province = (EditText) findViewById(R.id.editTextProvince);
        postalCode = (EditText)findViewById(R.id.editTextPostalCode);

        motherLastName = (EditText) findViewById(R.id.editTextMotherLastName);
        motherFirstName = (EditText) findViewById(R.id.editTextMotherFirstName);
        motherMiddleName = (EditText) findViewById(R.id.editTextMotherMiddleName);

        email = (EditText) findViewById(R.id.editTextEmail);
        dateOfBirth = (TextView) findViewById(R.id.editTextDateOfBirth);
        dobButton = (Button) findViewById(R.id.dobButton);

        editTextEmploymentStart = (TextView)findViewById(R.id.editTextEmploymentStartDate);
        editTextEmploymentEnd = (TextView)findViewById(R.id.editTextEmploymentEndDate);
        employmentStartDateButton = (Button)findViewById(R.id.employmentStartButton);
        employmentEndDateButton = (Button)findViewById(R.id.employmentEndButton);

        loanAmmount = (EditText) findViewById(R.id.editTextLoanAmount);
        gender = (Spinner) findViewById(R.id.spinnerGender);
        sourceOfFunds = (Spinner) findViewById(R.id.spinnerSourceOfFunds);
        nameOfEmployer = (TextView) findViewById(R.id.editTextNameOfEmployer);
        homePhone = (TextView)findViewById(R.id.editTextPrimaryNumber);
        mobilePhone = (TextView) findViewById(R.id.editTextMobileNumber);
        customerId = (TextView) findViewById(R.id.editTextCustomerId);
        customerId.setText("12345678");


        dobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                // Create a new instance of DatePickerDialog and return it
                final DatePickerDialog datePicker = new DatePickerDialog(SampleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        dateOfBirth.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        employmentStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                // Create a new instance of DatePickerDialog and return it
                final DatePickerDialog datePicker = new DatePickerDialog(SampleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        editTextEmploymentStart.setText(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH));
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        employmentEndDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                // Create a new instance of DatePickerDialog and return it
                final DatePickerDialog datePicker = new DatePickerDialog(SampleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        editTextEmploymentEnd.setText(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH));
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        helper = new UIHelper(this, this);

        String genderChoices[] = {"Male","Female"};
        gender.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, genderChoices));

        String sourceOfFundsChoices[] = {"Please Select", "Salary", "Commission", "Business",
                "Pension", "Remittance", "Allowance", "Self-Employed"};
        sourceOfFunds.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sourceOfFundsChoices));
        //setup the Lenddo Button
        button.setUiHelper(helper);

    }

    @Override
    public boolean onButtonClicked(FormDataCollector formData) {
        //auto-collect (optional)
        formData .collect(SampleActivity.this, R.id.formContainer);

        //If facebook is available

        //AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //formData.setFacebookToken(accessToken.getToken().toString(), accessToken.getExpires().getTime());

        Address primaryAddress = new Address();

        primaryAddress.setHouseNumber(houseNumber.getText().toString());
        primaryAddress.setStreet(street.getText().toString());
        primaryAddress.setBarangay(barangay.getText().toString());
        primaryAddress.setProvince(province.getText().toString());
        primaryAddress.setCity(city.getText().toString());
        primaryAddress.setPostalCode(postalCode.getText().toString());
        primaryAddress.setCountryCode("PH");

        //place partner defined user identifier
        formData.setClientId(customerId.getText().toString());


        formData.setLastName(lastName.getText().toString());
        formData.setMiddleName(middleName.getText().toString());
        formData.setHomePhone(homePhone.getText().toString());
        formData.setFirstName(firstName.getText().toString());
        formData.setEmail(email.getText().toString());
        formData.setEmployerName(nameOfEmployer.getText().toString());
        formData.setMobilePhone(mobilePhone.getText().toString());
        formData.setDateOfBirth(dateOfBirth.getText().toString());
        formData.setStartEmploymentDate(editTextEmploymentStart.getText().toString());
        formData.setEndEmploymentDate(editTextEmploymentEnd.getText().toString());
        formData.setMotherFirstName(motherFirstName.getText().toString());
        formData.setMotherLastName(motherLastName.getText().toString());
        formData.setMotherMiddleName(motherMiddleName.getText().toString());
        formData.setUniversityName(university.getText().toString());
        formData.setAddress(primaryAddress);

        //send custom fields
        formData.putField("Loan_Amount", loanAmmount.getText().toString());

        formData.validate();


        return true;
    }

    @Override
    public void onAuthorizeComplete(FormDataCollector collector) {
        //Called when the process is complete

        Intent finishIntent = new Intent(SampleActivity.this, CompleteActivity.class);
        AuthorizationStatus status = collector.getAuthorizationStatus();
        finishIntent.putExtra("verification", status.getVerification());
        finishIntent.putExtra("status", status.getStatus());
        finishIntent.putExtra("userId", status.getUserId());
        finishIntent.putExtra("transId", status.getTransId());

        startActivity(finishIntent);
        finish();
    }

    @Override
    public void onAuthorizeCanceled(FormDataCollector collector) {
        //Called when canceled

        Toast.makeText(SampleActivity.this, "canceled!", Toast.LENGTH_LONG).show();
        Intent finishIntent = new Intent(SampleActivity.this, CanceledActivity.class);
        startActivity(finishIntent);
        finish();
    }

    @Override
    public void onAuthorizeError(int statusCode, String response) {
        //Called when unexpected errors
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (helper.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult called " + requestCode);
        helper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
