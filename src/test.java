public class test {
    public static void main(String[] args) {
        System.out.println(AccountService.passwordIsValid("Qwerty!2"));
        System.out.println(AccountService.emailIsValid("kati@gmail.com"));
        AccountService.register("","",(float) 0.0,"kati@aueb.com","Qwerty!2");
        System.out.println(AccountService.login("kati@aueb.com","Qwerty!2"));
        AccountService.save();

    }
}
