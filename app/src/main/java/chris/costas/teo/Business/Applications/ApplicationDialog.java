package chris.costas.teo.Business.Applications;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;

import chris.costas.teo.R;
import model.services.AccountService;

/**
 * Creates a dialog to display the application info and from the user to accept or reject it.
 */
public class ApplicationDialog extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "application_dialog";
    private Toolbar toolbar;
    Button accept;
    Button decline;
    private static int position;
    TextView Info;
    private String m_Text = "";
    private boolean remove=false;

    public static ApplicationDialog display(FragmentManager fragmentManager, int pos) {
        position=pos;
        ApplicationDialog exampleDialog = new ApplicationDialog();
        exampleDialog.show(fragmentManager, TAG);
        return exampleDialog;
    }

    public int getPosition(){
        return position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.application_dialog, container, false);
        toolbar = view.findViewById(R.id.Application_Toolbar);
        accept= view.findViewById(R.id.AcceptButton);
        decline= view.findViewById(R.id.DeclineButton);
        Info= view.findViewById(R.id.ApplicationText);
        Info.setText(AccountService.getPendingApplications().get(position).toString());

        accept.setOnClickListener(this);
        decline.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Application Info");
        toolbar.inflateMenu(R.menu.application_dialog_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }

    public boolean getRemove(){
        return remove;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Override
    public void onDismiss(final DialogInterface dialog){
        super.onDismiss(dialog);
        Activity activity=getActivity();
        if(activity instanceof DialogInterface.OnDismissListener){
            ((DialogInterface.OnDismissListener)activity).onDismiss(dialog);
        }
    }

    @Override
    public void onClick(View v) { // TODO: 21/5/2019 get only the pending applications, when they get accepted or rejected, don't show them here
        LocalDate localDate = LocalDate.now();
        remove=false;
        switch (v.getId()){
            case R.id.AcceptButton:
                AccountService.acceptApplication(ApplicationsActivity.getApplications().get(position).getId());
                remove=true;
                dismiss();
                break;
            case R.id.DeclineButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Reason of rejecting");

// Set up the input
                final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                AccountService.rejectApplication(ApplicationsActivity.getApplications().get(position).getId(),m_Text);
                remove=true;
                dismiss();
                break;
        }
    }
}
