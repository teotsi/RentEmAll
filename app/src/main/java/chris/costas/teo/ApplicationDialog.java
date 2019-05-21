package chris.costas.teo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.services.AccountService;

public class ApplicationDialog extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "application_dialog";
    private Toolbar toolbar;
    Button accept;
    Button decline;
    private static int position;
    TextView Info;

    public static ApplicationDialog display(FragmentManager fragmentManager, int pos) {
        position=pos;
        ApplicationDialog exampleDialog = new ApplicationDialog();
        exampleDialog.show(fragmentManager, TAG);
        return exampleDialog;
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
        accept=(Button) view.findViewById(R.id.AcceptButton);
        decline=(Button) view.findViewById(R.id.DeclineButton);
        Info=(TextView) view.findViewById(R.id.ApplicationText);
        Info.setText(AccountService.getApplications().get(position).toString());

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
    public void onClick(View v) {
        LocalDate localDate = LocalDate.now();
        switch (v.getId()){
            case R.id.AcceptButton:
                AccountService.getApplications().get(position).setAccepted(true);
                AccountService.getApplications().get(position).setPending(false);
                AccountService.getApplications().get(position).setReplyDate(localDate);
                dismiss();
                break;
            case R.id.DeclineButton:
                AccountService.getApplications().get(position).setAccepted(false);
                AccountService.getApplications().get(position).setPending(false);
                AccountService.getApplications().get(position).setReplyDate(localDate);
                dismiss();
                break;
        }
    }
}
