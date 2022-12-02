package Use_Cases;

import Controllers_Presenters.DataFetchControl;

/**
 * check if
 * - user's information which is required for register is missing
 * - user’s email, age and postal code are valid
 * - email already exists
 */
public class RegChecker {
    public boolean pass;
    public String diagnose = "";

    /**
     * First, check any user's inputs is missing or an image has been uploaded into picUploader.
     * If this passed, continue to check if user's input for email, age, postal code are valid.
     * @param platform_info user's input, for social media information
     * @param email a user's input, for email
     * @param password a user's input, for password
     * @param name a user's input, for name
     * @param age a user's input, for age
     * @param gender a user's input, for gender
     * @param postcode a user's input, for postal code
     * @param picUploader user's image upload platform
     */
    public RegChecker(String platform_info, String email, String password, String name, String age, String gender, String postcode, PicUploader picUploader){
        checkIfMissing(platform_info, email, password, name, age, gender, postcode, picUploader);
        if (this.pass){
            checkValidate(email, age, postcode);
            checkDuplicate(email);
        }
    }

    /**
     * pass will be false and diagnose will be updated if
     * - any of user's inputs is empty, or
     * - picUploader did not receive an image in picUploader
     * @param soc_med a user's input, for social media information
     * @param email a user's input, for email
     * @param pw a user's input, for password
     * @param name a user's input, for name
     * @param age a user's input, for age
     * @param gender a user's input, for gender
     * @param postcode a user's input, for postal code
     * @param picUploader user's image upload platform
     */
    private void checkIfMissing(String soc_med, String email, String pw, String name, String age, String gender, String postcode, PicUploader picUploader){
        if (email.isEmpty() | pw.isEmpty() | name.isEmpty() | age.isEmpty()|
                gender.isEmpty() | postcode.isEmpty() | soc_med.isEmpty()){
            this.pass = false;
            this.diagnose = "missing input(s), \n";
        } else if(!picUploader.received){ // if inputs are not empty, continues check if an image received in picUploader.
            this.pass = false;
            this.diagnose = "you did not select any image to upload, \n";
        }
        else {this.pass = true;}
    }

    /**
     * pass will be false and diagnose will be updated if email, age or postcode is not valid.
     * - email is valid when it has an @ inside.
     * - age is valid when it is a number with range [0,150].
     * - postcode is valid when it is a Canadian postal code with a whitespace.
     * @param email a user's input, for email
     * @param age a user's input, for age
     * @param postcode a user's input, for postal code
     */
    private void checkValidate(String email, String age, String postcode){
        // check if email is valid
        if (!email.contains("@")){
            this.pass = false;
            this.diagnose += "email is not valid; \n";
        }

        // check if postcode is valid
        // Canadian postal code consist of 6 letters with a format CNC NCN, where C is a letter and N is a digit.
        // There is a space separates the third and fourth characters
        // it does not include the letters D, F, I, O, Q or U.
        // Also, the first position does not make use of the letters W or Z.
        // referenced https://www.canadapost-postescanada.ca/cpc/en/support/articles/addressing-guidelines/postal-codes.page
        // https://howtodoinjava.com/java/regex/canada-postal-code-validation/
        if (postcode.length() != 7){
            this.pass = false;
            this.diagnose += "postal code is not valid, remember to enter it with a whitespace like the example provided;\n";
        } else if (!postcode.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$")){
            this.pass = false;
            this.diagnose += "postal code is not a valid Canadian postal code;\n";
        }

        // check if age is valid
        try{
            int age_int = Integer.parseInt(age);
            if (!(age_int >= 0 && age_int <= 150)){
                this.pass = false;
                this.diagnose += "age is not in a valid range; \n";
            }
        } catch (NumberFormatException error){ // if user did not enter a number as age..
            this.pass = false;
            this.diagnose += "age is not a valid input; \n";
        }

//        if (!(Authenticator.isValidEmail(email))){
//            this.pass = false;
//            this.diagnose += "email is not valid; \n";
//        }
//        if (pos.length() != 7){
//            this.pass = false;
//            this.diagnose += "postal code is not valid (remember to enter it with a whitespace like the example provided);\n";
//        }
//        if (!(Authenticator.isValidAge(age_int))){
//            this.pass = false;
//            this.diagnose += "age is not valid; \n";
//        }
    }

    private void checkDuplicate(String email){
        // TODO: check if this violate CleanAr
        if (new DataFetchControl().fetch_emails().contains(email)){
            this.pass = false;
            this.diagnose += "email is existed; \n";
        }
    }
}
