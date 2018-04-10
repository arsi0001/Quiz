package arsi0001.hs_kl.androfirequiz.Model;

public class Nutzer {

    private String _nutzername;
    private String _passwort;
    private String _email;

    public Nutzer() {
    }


    public Nutzer(String _nutzername, String _passwort, String _email) {
        this._nutzername = _nutzername;
        this._passwort = _passwort;
        this._email = _email;
    }

    public String get_nutzername() {
        return _nutzername;
    }

    public void set_nutzername(String _nutzername) {
        this._nutzername = _nutzername;
    }

    public String get_passwort() {
        return _passwort;
    }

    public void set_passwort(String _passwort) {
        this._passwort = _passwort;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
