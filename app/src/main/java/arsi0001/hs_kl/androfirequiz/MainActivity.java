package arsi0001.hs_kl.androfirequiz;

import android.content.DialogInterface;
import android.os.UserHandle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import arsi0001.hs_kl.androfirequiz.Model.Nutzer;

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

        //Firebase
        _database = FirebaseDatabase.getInstance();
        _alleNutzer = _database.getReference("Nutzer");

        //Textfelder
        _edTNutzer = (MaterialEditText) findViewById(R.id.edTNutzer);
        _edTPasswort = (MaterialEditText) findViewById(R.id.edTPasswort);

        //Buttons
        _btnAnmelden = (Button) findViewById(R.id.btn_anmelden);
        _btnRegistrieren = (Button) findViewById(R.id.btn_registrieren);

        //Listener für Buttons
        _btnRegistrieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registiereNutzer();
            }
        });
        _btnAnmelden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nutzerAnmelden(_edTNutzer.getText().toString(), _edTPasswort.getText().toString());
            }
        });


    }

    //Anmeldung des Nutzers
    private void nutzerAnmelden(final String nutzer, final String passwort) {
        _alleNutzer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Pruefe ob Nutzer vorhanden -> Pruefe Passwort
                if(dataSnapshot.child(nutzer).exists()){
                    if(!nutzer.isEmpty()){
                        Nutzer login = dataSnapshot.child(nutzer).getValue(Nutzer.class);
                        if(login.get_passwort().equals(passwort))
                            Toast.makeText(MainActivity.this, "Login erfolgreich!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Falsches Passwort!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Nutzername fehlt!", Toast.LENGTH_SHORT).show();
                    }
                }
                //Pruefe ob Nutzer vorhanden -> Account nicht vorhanden
                else
                    Toast.makeText(MainActivity.this, "Nutzer existiert nicht!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //Registration eines neuen Nutzers
    private void registiereNutzer() {
        //Ueberschrift bei Registration
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Registration");
        alertDialog.setMessage("Bitte vollständige Angaben machen!");

        LayoutInflater inflater = this.getLayoutInflater();
        View registierungs_layout = inflater.inflate(R.layout.registration_layout, null);

        _edTNeuerNutzer = (MaterialEditText) registierungs_layout.findViewById(R.id.edTNeuerNutzer);
        _edTNeueEmail = (MaterialEditText) registierungs_layout.findViewById(R.id.edTNeueEmail);
        _edTNeuesPasswort = (MaterialEditText) registierungs_layout.findViewById(R.id.edTNeuesPasswort);

        alertDialog.setView(registierungs_layout);
        alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);

        //Abbruch der Registration
        alertDialog.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //Bestaetigung der Registration
        alertDialog.setPositiveButton("Registrieren", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final Nutzer nutzer = new Nutzer(_edTNeuerNutzer.getText().toString(),
                        _edTNeuesPasswort.getText().toString(),
                        _edTNeueEmail.getText().toString());

                 _alleNutzer.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         if(dataSnapshot.child(nutzer.get_nutzername()).exists())
                             Toast.makeText(MainActivity.this, "Nutzer existiert bereits!", Toast.LENGTH_SHORT).show();
                         else
                         {
                             _alleNutzer.child(nutzer.get_nutzername()).setValue(nutzer);
                             Toast.makeText(MainActivity.this, "Registration erfolgreich!", Toast.LENGTH_SHORT).show();
                         }
                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });
                 dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}