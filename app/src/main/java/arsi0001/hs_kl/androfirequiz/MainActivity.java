package arsi0001.hs_kl.androfirequiz;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    //Registration
    private MaterialEditText _edTNeuerNutzer, _edTNeuesPasswort, _edTNeueEmail;

    //Anmeldung
    private MaterialEditText _edTNutzer, _edTPasswort;

    Button _btnAnmelden, _btnRegistrieren;

    FirebaseDatabase _database;
    DatabaseReference _alleNutzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _database = FirebaseDatabase.getInstance();
        _alleNutzer = _database.getReference("Nutzer");

        _edTNutzer = (MaterialEditText) findViewById(R.id.edTNutzer);
        _edTPasswort = (MaterialEditText) findViewById(R.id.edTPasswort);

        _btnAnmelden = (Button) findViewById(R.id.btn_anmelden);
        _btnRegistrieren = (Button) findViewById(R.id.btn_registrieren);

        _btnRegistrieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeigeRegistrationsDialog();
            }
        });
    }

    private void zeigeRegistrationsDialog() {
        //Feedback
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Registriert");
        alertDialog.setMessage("Bitte vollständige Angaben machen!");

        LayoutInflater inflater = this.getLayoutInflater();
        View registierungs_layout = inflater.inflate(R.layout.registration_layout, null);

        _edTNeuerNutzer = (MaterialEditText) registierungs_layout.findViewById(R.id.edTNeuerNutzer);
        _edTNeueEmail = (MaterialEditText) registierungs_layout.findViewById(R.id.edTNeueEmail);
        _edTNeuesPasswort = (MaterialEditText) registierungs_layout.findViewById(R.id.edTNeuesPasswort);

    }
}