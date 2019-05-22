package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import model.classes.CompanyAccount;

public class EditAccount extends AppCompatActivity {

    CompanyAccount currentUser;
    EditText newName_Text = findViewById(R.id.NewNameTextField);
    EditText newRentalRange_Text = findViewById(R.id.NewRentalRangeTextField);
    EditText newAddress_Text = findViewById(R.id.NewAddressTextField);
    EditText newPolicy_Text = findViewById(R.id.NewPolicyTextField);
    EditText newDescription_Text = findViewById(R.id.NewDescriptionTextField);
    Button saveChangesBtn;
    String newName, newAddress, newPolicy, newDescription;
    float newRentalRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        newName_Text.setText(currentUser.getCompanyName());
        newRentalRange_Text.setText(String.valueOf(currentUser.getRange()));
        //newAddress.setText(currentUser.getAddress());
        newPolicy_Text.setText(currentUser.getPolicy());
        newDescription_Text.setText(currentUser.getDescription());


        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName = findViewById(R.id.NewNameTextField).toString();
                newRentalRange = (float)(R.id.NewRentalRangeTextField);
                //newAddress = findViewById(R.id.NewAddressTextField);
                newPolicy = findViewById(R.id.NewPolicyTextField).toString();
                newDescription = findViewById(R.id.NewDescriptionTextField).toString();
                UpdateAccountData(currentUser, newName, newRentalRange, newPolicy, newDescription);
            }
        });
    }

    //TODO Add address functionality
    void UpdateAccountData(CompanyAccount currentUser, String name, float rentalRange, String policy, String description){
        currentUser.setCompanyName(name);
        currentUser.setRange(rentalRange);
        currentUser.setPolicy(policy);
        currentUser.setDescription(description);
    }
}
