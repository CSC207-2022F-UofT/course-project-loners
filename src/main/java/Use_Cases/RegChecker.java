package Use_Cases;

import Controllers_Presenters.DataFetchControl;

public class RegChecker {
    public boolean pass;
    public String diagnose = "";

    public RegChecker(String pf_info, String email, String password, String name, String age, String gender, String postcode, PictureHolder picHolder){
        check_ifmissing(pf_info, email, password, name, age, gender, postcode, picHolder);
        if (this.pass){
            int age_int = Integer.parseInt(age);
            check_validate(email, age_int, postcode);
            check_duplicate(email);
        }
    }
    public void check_ifmissing(String soc_med, String email, String pw, String name, String age, String gender, String code, PictureHolder picHolder){
        if (email.isEmpty() | pw.isEmpty() | name.isEmpty() | age.isEmpty()|
                gender.isEmpty() | code.isEmpty() | soc_med.isEmpty()){
            this.pass = false;
            this.diagnose = "missing input(s), \n";
        } else if(!picHolder.saved){
            this.pass = false;
            this.diagnose = "you did not select any image to upload, \n";
        }
        else {this.pass = true;}
    }

    public void check_validate(String email, int age, String pos){
        if (!(Authenticator.is_valid_email(email))){
            this.pass = false;
            this.diagnose += "email is not valid; \n";
        }
        if (pos.length() != 7){
            this.pass = false;
            this.diagnose += "postal code is not valid (remember to enter it with a whitespace like the example provided);\n";
        }
        if (!(Authenticator.is_valid_age(age))){
            this.pass = false;
            this.diagnose += "age is not valid; \n";
        }
    }

    public void check_duplicate(String email){
        if (new DataFetchControl().fetch_emails().contains(email)){
            this.pass = false;
            this.diagnose += "email is existed; \n";
        }
    }

    public static void main(String[] args) {
    }
}
