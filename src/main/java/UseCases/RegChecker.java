package UseCases;

public class RegChecker {
    public boolean pass;

    public RegChecker(){}

    public void check_validate(String email, int age, String pos){
        if (age <= 18 | pos.length() != 3 | !email.contains("@")){
            this.pass = false;
        }
    }

    public void check_duplicate(String email){
        // TODO: Fill in this method once DataFetchSend done
    }
}
