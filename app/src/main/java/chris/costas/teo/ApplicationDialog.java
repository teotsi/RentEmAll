package chris.costas.teo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class ApplicationDialog extends DialogFragment {
    public static final String TAG = "application_dialog";
    private static boolean accepted;

    private Toolbar toolbar;
    Button accept;
    Button decline;

    public static ApplicationDialog display(FragmentManager fragmentManager) {
        ApplicationDialog exampleDialog = new ApplicationDialog();
        exampleDialog.show(fragmentManager, TAG);
        return exampleDialog;
    }

    public static boolean Accepted(){
        return accepted;
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
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accepted=true;
                dismiss();
            }
        });
        decline=(Button) view.findViewById(R.id.AcceptButton);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accepted=false;
                dismiss();
            }
        });

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

}
