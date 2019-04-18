public class AccountService extends Service {

    public static boolean emailIsAvailable(String email) {
        for (CompanyAccount companyAccount : companies) {
            if (companyAccount.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public static boolean login(String email, String password) {
        for (CompanyAccount companyAccount : companies) {
            if (companyAccount.getEmail().equals(email)) { //checking email first
                return companyAccount.authorizeLogin(password);//authorizeLogin compares a hash to another hash, a
                //waste of time if the emails don't match
            } else {
                continue;
            }
        }
        return false;
    }

    public static boolean passwordIsValid(String password) {
        //password must contain: a digit, a lower case character, an upper case character,
        //a special character, no whitespace, and its length must be between 8 and 32 chars.
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,32}";
        return password.matches(pattern);
    }

    public static void register(String id, String policy, String description, String logs, float range, String email, String password) {
        companies.add(new CompanyAccount(id, policy, description, logs, range, email, password));
    }

}
