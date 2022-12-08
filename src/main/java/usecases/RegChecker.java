package usecases;

import dataaccess.FetchData; // implements a Use Case interface

/**
 * check if
 * - information of user which is required for register is missing
 * - email, age and postal code of user are valid
 * - email already exists
 */
public class RegChecker {

    /**
     * True if this checker is passed
     */
    public boolean pass;
    /**
     * A diagnosis summary for this checker, showing which part did not pass.
     */
    public String diagnose = "";

    /**
     * First, check any user inputs or image is missing.
     * If this passed, continue to check if user input for email, age, postal code are valid.
     *
     * @param platformInfo an input of user, for social media information
     * @param email an input of user, for email
     * @param password an input of user, for password
     * @param name an input of user, for name
     * @param age an input of user, for age
     * @param postcode an input of user, for postal code
     * @param picLoaded true if user has selected an image to upload
     */
    public RegChecker(String platformInfo, String email, String password, String name, String age, String postcode, boolean picLoaded){
        checkIfMissing(platformInfo, email, password, name, age, postcode, picLoaded);
        if (this.pass){
            checkDuplicate(email);
            checkValidate(email, age, postcode);
        }
    }

    /**
     * pass will be false and diagnose will be updated if
     * - any of user inputs is empty, or
     * - picUploader did not receive an image in picUploader
     *
     * @param socMed an input of user, for social media information
     * @param email an input of user, for email
     * @param pw an input of user, for password
     * @param name an input of user, for name
     * @param age an input of user, for age
     * @param postcode an input of user, for postal code
     * @param picLoaded true if user has selected an image to upload
     */
    private void checkIfMissing(String socMed, String email, String pw, String name, String age, String postcode, boolean picLoaded){
        if (email.isEmpty() | pw.isEmpty() | name.isEmpty() | age.isEmpty()| postcode.isEmpty() | socMed.isEmpty()){
            this.pass = false;
            this.diagnose = "Missing input(s). \n";
        } else if(!picLoaded){ // if inputs are not empty, continues check if an image received in picUploader.
            this.pass = false;
            this.diagnose = "You did not select an image to upload. \n";
        }
        else {this.pass = true;}
    }

    /**
     * pass will be false and diagnose will be updated if email, age or postcode is not valid.
     * - email is valid when it has an @ inside.
     * - age is valid when it is a number with range [0,150].
     * - postcode is valid when it is a Canadian postal code with a whitespace.
     *
     * @param email an input of user, for email
     * @param age an input of user, for age
     * @param postcode an input of user, for postal code
     */
    private void checkValidate(String email, String age, String postcode) {
        // check if email is valid
        if (!email.contains("@")) {
            this.pass = false;
            this.diagnose += "- Email is not valid. \n";
        }

        // check if age is valid
        try {
            int age_int = Integer.parseInt(age);
            if (!(age_int >= 0 && age_int <= 150)) {
                this.pass = false;
                this.diagnose += "- Age is not in a valid range \n";
            }
        } catch (NumberFormatException error) { // if user did not enter a number for age
            this.pass = false;
            this.diagnose += "- Age is not a number \n";
        }

        // check if postcode is valid
        // Canadian postal code consist of 6 letters with a format CNC NCN, where C is a letter and N is a digit.
        // There is a space separates the third and fourth characters
        // it does not include the letters D, F, I, O, Q or U.
        // Also, the first position does not make use of the letters W or Z.
        // referenced https://www.canadapost-postescanada.ca/cpc/en/support/articles/addressing-guidelines/postal-codes.page
        // https://howtodoinjava.com/java/regex/canada-postal-code-validation/
        if (postcode.length() != 7) {
            this.pass = false;
            this.diagnose += "- Postal code is not valid (remember to enter it with a whitespace like the example provided)\n";
        } else if (!postcode.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$")) {
            this.pass = false;
            this.diagnose += "- Postal code is not a Canadian postal code\n";
        }

    }

    /**
     * pass will be false and diagnose will be updated if email is existing in database.
     *
     * @param email an input of user, for email
     */
    private void checkDuplicate(String email){
        // TODO: fix violation??
        if (FetchData.fetchEmails().contains(email)){
            this.pass = false;
            this.diagnose += "- This email has registered \n";
        }
    }
}
