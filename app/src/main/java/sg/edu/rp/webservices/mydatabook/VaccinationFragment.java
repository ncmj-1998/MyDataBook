package sg.edu.rp.webservices.mydatabook;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class VaccinationFragment extends Fragment {

TextView tvDetails;
    public VaccinationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String name = prefs.getString("vacDetail","");
        tvDetails.setText(name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_bio, container, false);
        tvDetails = rootview.findViewById(R.id.tvDetails);

        Button btnEdit = rootview.findViewById(R.id.edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout passPhrase =
                        (LinearLayout) inflater.inflate(R.layout.dialog, null);
                final EditText etDetails = (EditText) passPhrase
                        .findViewById(R.id.etDetails);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Vaccination")
                        .setView(passPhrase)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                tvDetails.setText(etDetails.getText().toString());
                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                                SharedPreferences.Editor prefEdit = prefs.edit();
                                prefEdit.putString("vacDetail",etDetails.getText().toString());
                                prefEdit.commit();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "You have cancelled editting", Toast.LENGTH_SHORT).show();
                            }
                        })
                ;
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        return rootview;
    }
}
